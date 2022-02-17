package com.yjkim.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * LocationSearch 모델.
 *
 * @author werbwerb@naver.com
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationSearch {
    @Data
    public static class commonResponse {
        private String placeName; /* 장소 이름 */
        private String placeUrl; /* 장소 링크 */
        private String category; /* 카테고리 */
        private String address; /* 기본 주소 */
        private String roadAddress; /* 도로명 주소 */
        private String phone; /* 장소 번호 */
        private String x; /* X 좌표 */
        private String y; /* Y 좌표 */
    }

    @Data
    public static class KaKaoLocationResponse {
        private List<Documents> documents; /* 조회 값 리스트 */
    }

    @Data
    public static class Documents {
        @JsonProperty(value = "place_name")
        private String placeName; /* 장소 이름 */
        @JsonProperty(value = "place_url")
        private String placeUrl; /* 장소 링크 */
        @JsonProperty(value = "category_name")
        private String category; /* 카테고리 */
        @JsonProperty(value = "address_name")
        private String address; /* 기본 주소 */
        @JsonProperty(value = "road_address_name")
        private String roadAddress; /* 도로명 주소 */
        private String phone; /* 장소 번호 */
        private String x; /* X 좌표 */
        private String y; /* Y 좌표 */
    }

    @Data
    public static class NaverLocationResponse {
        private List<Items> items; /* 조회 값 리스트 */
    }

    @Data
    public static class Items {
        @JsonProperty(value = "title")
        private String placeName; /* 장소 이름 */
        @JsonProperty(value = "link")
        private String placeUrl; /* 장소 링크 */
        private String category; /* 카테고리 */
        private String address; /* 기본 주소 */
        private String roadAddress; /* 도로명 주소 */
        @JsonProperty(value = "telephone")
        private String phone; /* 장소 번호 */
        @JsonProperty(value = "mapx")
        private int x; /* X 좌표 */
        @JsonProperty(value = "mapy")
        private int y; /* Y 좌표 */
    }
}
