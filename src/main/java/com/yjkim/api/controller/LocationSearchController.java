package com.yjkim.api.controller;

import com.yjkim.api.model.ApiResponse;
import com.yjkim.api.model.LocationSearch;
import com.yjkim.api.service.LocationSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * LocationSearchController.
 * API ID: yjkim-api-001 - 장소 검색.
 *
 * @author werbwerb@naver.com
 */
@RestController
@RequestMapping(headers = "Accept=application/json", value = "/yjkim/api/v1")
public class LocationSearchController {
    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationSearchController.class);
    /**
     * LocationSearchService.
     */
    @Autowired
    LocationSearchService locationSearchService;

    /**
     * [YJKIM-API-001] 장소 검색.
     *
     * @param keyword 장소 검색 연관 단어.
     * @return 장소 검색 결과
     */
    @GetMapping(value = "/location/search")
    public ApiResponse getLocationSearch(@RequestParam String keyword) {
        List<LocationSearch.commonResponse> list = locationSearchService.getLocationSearch(keyword);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResultData(list);
        return apiResponse;
    }
}
