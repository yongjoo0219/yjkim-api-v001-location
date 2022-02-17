package com.yjkim.api.service;

import com.yjkim.api.model.LocationSearch;
import com.yjkim.api.util.ObjectUtils;
import com.yjkim.api.util.RestTemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    /**
     * RestTemplateUtil.
     */
    @Autowired
    RestTemplateUtil restTemplateUtil;
    /**
     * ObjectUtils.
     */
    @Autowired
    ObjectUtils objectUtils;
    /**
     * 카카오 REST 정보.
     */
    @Value("${config.kakao.url}")
    private String K_URL;
    @Value("${config.kakao.api-key}")
    private String K_API_KEY;

    @Override
    public List<LocationSearch> getLocationSearch(String keyword) {
        //카카오 장소 검색 API 호출.
        getKaKaoLocationSearch(keyword);

        //네이버 장소 검색 API 호출.
        return new ArrayList<>();
    }

    public void getKaKaoLocationSearch(String keyword) {
        String path = "/v2/local/search/keyword.json";
        String url = K_URL + path + "?query=" + keyword;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", K_API_KEY);

        //카카오, 네이버 VO 따로 만들고.

        //공통 VO 하나 만들어서. 매핑해야함. (한쪽은 좌표 변경해서 매핑)
        Map<String, Object> map = restTemplateUtil.restTemplate(url, HttpMethod.GET, headers, null,
                "[KAKAO] > GET_LOCATION_SEARCH", Map.class);
    }
}
