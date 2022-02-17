package com.yjkim.api.model;

/**
 * 응답 코드.
 *
 * @author werbwerb@naver.com
 */
public enum ResponseCode {
    /**
     * 성공.
     */
    OK("OK"),
    /**
     * 요청이 유효하지 않음.
     */
    INVALID_REQ("INVALID_REQUEST"),
    /**
     * 요청 파라미터가 유효하지 않음.
     */
    INVALID_PARAM("INVALID_PARAMETER"),
    /**
     * 접근 권한이 없음.
     */
    ACCESS_DENIED("ACCESS_DENIED"),
    /**
     * 내부 오류 발생함.
     */
    SERVER_ERROR("INTERNAL_ERROR"),
    /**
     * 해당하는 데이터가 없음.
     */
    EMPTY_RESULT("EMPTY_RESULT"),
    /**
     * 외부 API 통신 오류.
     */
    EXTERNAL_API_ERROR("EXTERNAL_API_ERROR");

    /**
     * 값.
     */
    private final String value;

    /**
     * 응답 코드.
     *
     * @param value 값
     */
    ResponseCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}