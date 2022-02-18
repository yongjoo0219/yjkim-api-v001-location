package com.yjkim.api.service;

import com.yjkim.api.model.LocationSearchVO;

import java.util.List;

public interface ExternalApiService {
    /**
     * 카카오 장소 검색.
     *
     * @param keyword 장소 키워드.
     * @return 장소 검색 리스트
     */
    List<LocationSearchVO.commonResponse> getKaKaoLocationSearch(String keyword);

    /**
     * 네이버 장소 검색.
     *
     * @param keyword 장소 키워드.
     * @return 장소 검색 리스트
     */
    List<LocationSearchVO.commonResponse> getNaverLocationSearch(String keyword);

    /**
     * 카텍 -> 위경도 좌표 변환.
     *
     * @param commonResponse 네이버 응답 값/
     * @return 변환 좌표
     */
    List<LocationSearchVO.commonResponse> modifyKatecToWGS84(LocationSearchVO.commonResponse commonResponse);
}
