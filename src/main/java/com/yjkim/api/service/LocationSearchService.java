package com.yjkim.api.service;

import com.yjkim.api.model.LocationSearch;

import java.util.List;

/**
 * LocationSearchService.
 *
 * @author werbwerb@naver.com
 */
public interface LocationSearchService {
    /**
     * 장소 검색.
     *
     * @param keyword 장소 키워드.
     * @return 장소 검색 리스트
     */
    List<LocationSearch.commonResponse> getLocationSearch(String keyword);


}
