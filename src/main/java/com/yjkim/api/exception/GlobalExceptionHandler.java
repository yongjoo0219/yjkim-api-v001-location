package com.yjkim.api.exception;

import com.yjkim.api.model.ApiResponse;
import com.yjkim.api.model.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 전역적으로 예외를 처리하기 위한 핸들러.
 *
 * @author werbwerb@naver.com
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Spring MVC에서 발생되는 예외들을 처리.
     *
     * @param req HttpServletRequest 객체
     * @param e   Exception 객체
     * @return ApiResponse
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class, MissingServletRequestParameterException.class,
            ServletRequestBindingException.class, ConversionNotSupportedException.class,
            HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class, BindException.class, NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleRequestException(HttpServletRequest req, Exception e) {
        LOGGER.warn("SPRING_MVC_EXCEPTION >> {} > {}", req.getRequestURI(), e.getMessage());
        return new ApiResponse(ResponseCode.INVALID_REQ.getValue(), e.getMessage());
    }

    /**
     * InvalidRequestException 처리.
     *
     * @param req HttpServletRequest 객체
     * @param e   Exception 객체
     * @return ApiResponse
     */
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleInvalidRequestException(HttpServletRequest req, InvalidRequestException e) {
        LOGGER.warn("INVALID_REQUEST_EXCEPTION >> {} > {}", req.getRequestURI(), e.getMessage());
        return new ApiResponse(e.getInvalidRequestErrorCode(), e.getInvalidRequestErrorMessage());
    }

    /**
     * EmptyResultException 처리.
     *
     * @param req HttpServletRequest 객체
     * @param e   Exception 객체
     * @return ApiResponse
     */
    @ExceptionHandler(EmptyResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleEmptyResultException(HttpServletRequest req, EmptyResultException e) {
        LOGGER.warn("EMPTY_RESULT_EXCEPTION >> {} > {}", req.getRequestURI(), e.getMessage());
        return new ApiResponse(e.getEmptyErrorCode(), e.getEmptyErrorMessage());
    }

    /**
     * ExternalApiException 처리.
     *
     * @param req HttpServletRequest 객체
     * @param e   Exception 객체
     * @return ApiResponse
     */
    @ExceptionHandler(ExternalApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleExternalApiException(HttpServletRequest req, ExternalApiException e) {
        LOGGER.error("EXTERNAL_API_EXCEPTION >> {} > {}", req.getRequestURI(), e.getMessage());
        return new ApiResponse(e.getExternalErrorCode(), e.getExternalErrorMessage());
    }

    /**
     * Exception 처리.
     *
     * @param req HttpServletRequest 객체
     * @param e   Exception 객체
     * @return ApiResponse
     */
    @ExceptionHandler({Exception.class, CommonException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleException(HttpServletRequest req, Exception e) {
        LOGGER.error("EXECPTION >> {} > {} > {}", req.getRequestURI(), e.getMessage(), e);
        return new ApiResponse(ResponseCode.SERVER_ERROR.getValue(), e.getMessage());
    }
}
