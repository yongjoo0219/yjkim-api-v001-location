package com.yjkim.api.service;

import com.google.gson.Gson;
import com.yjkim.api.model.LocationSearch;
import com.yjkim.api.util.ObjectUtils;
import com.yjkim.api.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalApiServiceImpl implements ExternalApiService {
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
    private String KAKAO_URL;
    @Value("${config.kakao.api-key}")
    private String KAKAO_API_KEY;
    /**
     * 네이버 REST 정보.
     */
    @Value("${config.naver.url}")
    private String NAVER_URL;
    @Value("${config.naver.client-id}")
    private String NAVER_CLIENT_ID;
    @Value("${config.naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    /**
     * 카카오 장소 검색 결과 호출.
     *
     * @param keyword 키워드.
     * @return 장소 검색 공통 규격.
     */
    @Override
    public List<LocationSearch.commonResponse> getKaKaoLocationSearch(String keyword) {
        String path = "/v2/local/search/keyword.json";
        String url = KAKAO_URL + path + "?query=" + keyword;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", KAKAO_API_KEY);

        //카카오 장소 검색.
        LocationSearch.KaKaoLocationResponse kakaoResponse = restTemplateUtil.restTemplate(url, HttpMethod.GET, headers, null,
                "[KAKAO] > GET_LOCATION_SEARCH", LocationSearch.KaKaoLocationResponse.class);
        List<LocationSearch.Documents> documents = kakaoResponse.getDocuments();

        //최대 5건만 출력 (5건 이하시 전부 포함).
        List<LocationSearch.commonResponse> commonList;
        if (documents.size() >= 5) {
            commonList = objectUtils.getListMapper(new Gson().toJson(documents.subList(0, 5)), LocationSearch.commonResponse.class);
        } else {
            commonList = objectUtils.getListMapper(new Gson().toJson(documents.subList(0, documents.size())), LocationSearch.commonResponse.class);
        }
        return commonList;
    }

    /**
     * 네이버 장소 검색 결과 호출.
     *
     * @param keyword 키워드.
     * @return 장소 검색 공통 규격.
     */
    @Override
    public List<LocationSearch.commonResponse> getNaverLocationSearch(String keyword) {
        String path = "/v1/search/local.json";
        String url = NAVER_URL + path + "?query=" + keyword + "&display=" + 5;
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", NAVER_CLIENT_ID);
        headers.add("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);

        //네이버 장소 검색.
        LocationSearch.NaverLocationResponse naverResponse = restTemplateUtil.restTemplate(url, HttpMethod.GET, headers, null,
                "[NAVER] > GET_LOCATION_SEARCH", LocationSearch.NaverLocationResponse.class);
        List<LocationSearch.Items> items = naverResponse.getItems();

        //최대 5건.
        List<LocationSearch.commonResponse> commonList;
        commonList = objectUtils.getListMapper(new Gson().toJson(items), LocationSearch.commonResponse.class);
        return commonList;
    }

    @Override
    public List<LocationSearch.commonResponse> modifyKatecToWGS84(LocationSearch.commonResponse commonResponse) {
        String path = "/v2/local/geo/transcoord.json";
        double x = Double.parseDouble(commonResponse.getX());
        double y = Double.parseDouble(commonResponse.getX());
        String url = KAKAO_URL + path + "?x=" + x + "&y=" + y + "&input_coord=KTM&output_coord=WGS84";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", KAKAO_API_KEY);

        //카카오 장소 검색.
        LocationSearch.KaKaoLocationResponse kakaoResponse = restTemplateUtil.restTemplate(url, HttpMethod.GET, headers, null,
                "[KAKAO] > GET_LOCATION_SEARCH", LocationSearch.KaKaoLocationResponse.class);
        List<LocationSearch.Documents> documents = kakaoResponse.getDocuments();
        List<LocationSearch.commonResponse> commonList;
        return objectUtils.getListMapper(new Gson().toJson(documents), LocationSearch.commonResponse.class);
    }
}
