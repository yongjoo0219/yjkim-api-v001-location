package com.yjkim.api.service;

import com.yjkim.api.model.LocationSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LocationSearchServiceImpl.
 *
 * @author werbwerb@naver.com
 */
@Service
public class LocationSearchServiceImpl implements LocationSearchService {
    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationSearchServiceImpl.class);

    @Autowired
    ExternalApiService externalApiService;

    @Override
    public List<LocationSearch.commonResponse> getLocationSearch(String keyword) {
        //카카오 장소 검색 API 호출.
        List<LocationSearch.commonResponse> kakaoLocList = externalApiService.getKaKaoLocationSearch(keyword);
        //네이버 장소 검색 API 호출.
        List<LocationSearch.commonResponse> naverLocList = externalApiService.getNaverLocationSearch(keyword);

        //네이버 -> 카카오 데이터 통일.
        for (LocationSearch.commonResponse one : naverLocList) {
            modifyAddressName(one);
            modifyPlaceName(one);
            List<LocationSearch.commonResponse> wsg = externalApiService.modifyKatecToWGS84(one);
            one.setX(wsg.get(0).getX());
            one.setY(wsg.get(0).getY());
        }

        //중복 값 추출.
        List<LocationSearch.commonResponse> sameResult = naverLocList.stream()
                .filter(oneNaver -> kakaoLocList.stream().anyMatch(oneKaKao -> oneNaver.getPlaceName().contains(oneKaKao.getPlaceName().split(" ")[0])
                        && oneNaver.getAddress().equals(oneKaKao.getAddress())))
                .collect(Collectors.toList());

        //중복 되지 않은 카카오 값 추출.
        List<LocationSearch.commonResponse> kakoResult = kakaoLocList.stream()
                .filter(oneKaKao -> naverLocList.stream().noneMatch(oneNaver -> oneNaver.getPlaceName().contains(oneKaKao.getPlaceName().split(" ")[0])))
                .collect(Collectors.toList());

        //중복 되지 않은 네이버 값 추출.
        List<LocationSearch.commonResponse> naverResult = naverLocList.stream()
                .filter(oneNaver -> kakaoLocList.stream().noneMatch(oneKaKao -> oneNaver.getPlaceName().contains(oneKaKao.getPlaceName().split(" ")[0])))
                .collect(Collectors.toList());

        List<LocationSearch.commonResponse> result = new ArrayList<>();
        result.addAll(sameResult);
        result.addAll(kakoResult);
        result.addAll(naverResult);
        return result;
    }

    public void modifyPlaceName(LocationSearch.commonResponse one) {
        //태그 제거.
        one.setPlaceName(one.getPlaceName().replaceAll("<[^>]*>", ""));
    }

    /**
     * 네이버 주소 변환.
     *
     * @param one 네이버 장소 검색 결과.
     */
    public void modifyAddressName(LocationSearch.commonResponse one) {
        String address = one.getAddress();
        //경기도 등.
        if (address.startsWith("경기도")) one.setAddress("경기" + address.substring(3));
        if (address.startsWith("강원도")) one.setAddress("강원" + address.substring(3));
        if (address.startsWith("충청북도")) one.setAddress("충북" + address.substring(4));
        if (address.startsWith("충청남도")) one.setAddress("충남" + address.substring(4));
        if (address.startsWith("경상북도")) one.setAddress("경북" + address.substring(4));
        if (address.startsWith("경상남도")) one.setAddress("경남" + address.substring(4));
        if (address.startsWith("전라북도")) one.setAddress("전북" + address.substring(4));
        if (address.startsWith("전라남도")) one.setAddress("전남" + address.substring(4));

        //광역시.
        if (address.startsWith("서울특별시")) one.setAddress("서울" + address.substring(5));
        if (address.startsWith("인천광역시")) one.setAddress("인천" + address.substring(5));
        if (address.startsWith("대전광역시")) one.setAddress("대전" + address.substring(5));
        if (address.startsWith("대구광역시")) one.setAddress("대구" + address.substring(5));
        if (address.startsWith("부산광역시")) one.setAddress("부산" + address.substring(5));
        if (address.startsWith("광주광역시")) one.setAddress("광주" + address.substring(5));
        if (address.startsWith("울산광역시")) one.setAddress("울산" + address.substring(5));
    }

}
