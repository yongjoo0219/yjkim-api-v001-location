package com.yjkim.api.controller;

import com.yjkim.api.model.ApiResponse;
import com.yjkim.api.service.KeywordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * KeywordController.
 * API ID: yjkim-api-002 - 검색 키워드 목록.
 *
 * @author werbwerb@naver.com
 */
@RestController
@RequestMapping(headers = "Accept=application/json", value = "/yjkim/api/v1")
public class KeywordController {
    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordController.class);
    /**
     * KeywordService.
     */
    @Autowired
    KeywordService keywordService;

    /**
     * [YJKIM-API-002] 키워드 Top10 조회.
     *
     * @return 조회된 키워드 리스트
     */
    @GetMapping(value = "/keyword/rank")
    public ApiResponse getKeywordRank() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResultData(keywordService.getKeywordRank());
        return apiResponse;
    }
}
