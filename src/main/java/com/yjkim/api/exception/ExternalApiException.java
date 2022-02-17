package com.yjkim.api.exception;


import com.yjkim.api.model.ResponseCode;

/**
 * 외부 API 통신시 발생한 예외.
 *
 * @author werbwerb@naver.com
 */
public class ExternalApiException extends CommonException {

    private static final long serialVersionUID = 9096390499082704598L;

    /**
     * 에러 코드.
     */
    private final String externalErrorCode;

    /**
     * 에러 메시지.
     */
    private final String externalErrorMessage;

    /**
     * 기본 생성자.
     */
    public ExternalApiException() {
        super("External API Error!");
        this.externalErrorCode = ResponseCode.EXTERNAL_API_ERROR.getValue();
        this.externalErrorMessage =  this.getMessage();
    }

    /**
     * 예외 문자열을 받는 생성자.
     *
     * @param message 예외 문자열
     */
    public ExternalApiException(String message) {
        super(message);
        this.externalErrorCode = ResponseCode.EXTERNAL_API_ERROR.getValue();
        this.externalErrorMessage = message;
    }

    /**
     * 예외 문자열과 코드를 받는 생성자.
     *
     * @param message 예외 문자열
     */
    public ExternalApiException(String code, String message) {
        super(message);
        this.externalErrorCode = code;
        this.externalErrorMessage = message;
    }

    public String getExternalErrorCode() {
        return externalErrorCode;
    }

    public String getExternalErrorMessage() {
        return externalErrorMessage;
    }
}
