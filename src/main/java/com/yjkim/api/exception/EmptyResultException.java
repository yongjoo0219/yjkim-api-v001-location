package com.yjkim.api.exception;

import com.yjkim.api.model.ResponseCode;

/**
 * 반드시 있어야 할 결과가 없을 때 발생되는 예외.
 *
 * @author werbwerb@naver.com
 */
public class EmptyResultException extends CommonException {

    private static final long serialVersionUID = -7456074249580626963L;

    /**
     * 에러 코드.
     */
    private final String emptyErrorCode;

    /**
     * 에러 메시지.
     */
    private final String emptyErrorMessage;

    /**
     * 기본 생성자.
     */
    public EmptyResultException() {
        super("No Result Data!");
        this.emptyErrorCode = ResponseCode.EMPTY_RESULT.getValue();
        this.emptyErrorMessage = this.getMessage();
    }

    /**
     * 예외 문자열을 받는 생성자.
     *
     * @param message 예외 문자열
     */
    public EmptyResultException(String message) {
        super(message);
        this.emptyErrorCode = ResponseCode.EMPTY_RESULT.getValue();
        this.emptyErrorMessage = message;
    }

    public String getEmptyErrorCode() {
        return emptyErrorCode;
    }

    public String getEmptyErrorMessage() {
        return emptyErrorMessage;
    }
}
