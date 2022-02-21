# yjkim-api-v001-location
김용주 장소 검색, 키워드 목록 조회 API

1. 제공 API 리스트
[yjkim-api-001] - 장소 검색: 키워드 요청에 관련된 장소 목록 리스트를 제공
[yjkim-api-002] - 검색 키워드 목록 조회: 가장 많이 요청된 키워드와 횟수를 제공

2. API 동작 시나리오

[yjkim-api-001] - 장소 검색 
- 카카오 장소 검색 API 연동
- 네이버 장소 검색 API 연동
- 공통 포맷으로 변환 후 각 데이터 유사도 비교
- 카카오 및 네이버의 장소 이름의 유사도를 검증하고 address(구 주소) 를 비교하여 일치하면 같은 곳이라 판단
- 네이버의 katec 좌표를 카카오 좌표 변환 API를 통해 변환하였지만 약간의 오차가 있는 것을 확인
- 해당 오차에 대해서는 정확하게 이해하지 못해, 비교 대상에서 제외
- 결과: 일치하는 장소 리스트 -> 일치하지 않는 카카오 장소 리스트 -> 일치하지 않는 네이버 장소 리스트.

[yjkim-api-002] - 검색 키워드 목록 조회
- 장소 검색 요청이 들어오면, 해당 키워드의 값이 인메모리 db에 있는지 확인
- 있으면 카운트 가져와서 증가
- 없으면 카운트 1로 세팅

3. 요구사항 정리
1) 외부 라이브러리 사용 정리
- gson: 카카오 API 응답 규격과 네이버 API 응답 규격이 달라서 공통된 규격을 하나 만들고 해당 VO로 매핑시킬 때 사용
- lombok: Getter Setter 등 어노테이션 기반 코드 자동 완성을 위해 사용
- h2database: 키워드 검색 리스트와 조회수를 관리하기 위해 사용
- httpclient: 외부 API 연동을 위해 타임아웃, 커넥션 등을 관리하기 위해 사용

2) 동시성 이슈가 발생할 수 있는 부분을 염두에 둔 설계 및 구현
- 키워드 별 조회수의 경우, 멀티쓰레드 환경에서 왜곡된 데이터를 리턴해줄 수 있음 (동기화 이슈)
- 해당 메소드는 synchronized를 통해 현재 스레드를 제외하고 다른 스레드의 접근을 막아 데이터의 신뢰성을 높임
- 하지만, 성능적인 이슈가 발생할 수 있기 때문에 해당 이슈에 대해 고민을 해봐야 할 것

3) 카카오, 네이버 등 검색 API 제공자의 “다양한” 장애 발생 상황에 대한 고려
- 

4) 구글 장소 검색 등 새로운 검색 API 제공자의 추가 시 변경 영역 최소화에 대한 고려
- 

5) 서비스 오류 및 장애 처리 방법에 대한 고려
- 발생할 수 있는 예외에 대해 커스텀 Exception 클래스를 생성 (InvalidParam, External, InvalidAuth 등..)
- 글로벌 Exception Handler를 통해 해당 예외들에 대해 공통 처리를 지원하며 공통된 규격으로 리턴 값 제시 (ResultCode, ResultMessage)

- 대용량 트래픽 처리를 위한 반응성(Low Latency), 확장성(Scalability), 가용성(Availability)을 높이기 위한 고려
- 지속적 유지 보수 및 확장에 용이한 아키텍처에 대한 설계
- 이 외에도 본인의 기술 역량을 잘 드러 낼 수 있는 부분을 과제 코드내에서 강조

4. 테스트 방법

5. 마무리
