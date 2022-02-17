package com.yjkim.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

/**
 * LocationSearch 모델.
 *
 * @author werbwerb@naver.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationSearch {
    /* 검색한 키워드 */
    private String keyword;

    /* 장소 이름 */
    @JsonAlias({"place_name", "title"})
    private String placeName;

    /* 카테고리 */
    @JsonAlias({"category_name", "category"})
    private String categoryName;

    /* 장소 링크 */
    @JsonAlias({"place_url", "link"})
    private String placeUrl;

    /* 주소 */
    @JsonAlias({"address_name", "address"})
    private String address;

    /* 도로명 주소 */
    @JsonAlias({"road_address_name", "roadAddress"})
    private String roadAddress;

    /* 장소 번호 */
    @JsonAlias({"phone", "telephone"})
    private String phone;

    /* X좌표 */
    private String x;

    /* Y좌표 */
    private String y;

    /* 카텍 X좌표 */
    @JsonAlias({"mapx"})
    private int katechX;

    /* 카텍 Y좌표 */
    @JsonAlias({"mapy"})
    private int katechY;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(String roadAddress) {
        this.roadAddress = roadAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public int getKatechX() {
        return katechX;
    }

    public void setKatechX(int katechX) {
        this.katechX = katechX;
    }

    public int getKatechY() {
        return katechY;
    }

    public void setKatechY(int katechY) {
        this.katechY = katechY;
    }

    @Override
    public String toString() {
        return "LocationSearch{" +
                "keyword='" + keyword + '\'' +
                ", placeName='" + placeName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", placeUrl='" + placeUrl + '\'' +
                ", address='" + address + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", katechX=" + katechX +
                ", katechY=" + katechY +
                '}';
    }
}
