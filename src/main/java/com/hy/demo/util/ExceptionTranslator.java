package com.hy.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xlqi
 * 异常翻译类 - 用于向 web service 请求方返回统一格式的错误信息。
 */
@ControllerAdvice
public class ExceptionTranslator {

    private static final Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);

    /**
     * 统一处理 Controller 层抛出的参数错误异常 - 用户提供参数格式错误。
     * @param exception 参数错误异常
     * @return 报错信息
     */
    @ExceptionHandler(value = {
        MethodArgumentTypeMismatchException.class,
        MissingServletRequestParameterException.class})
    public ResponseEntity<ResultDto<Void>> processMethodArgumentException(Exception exception) {
        log.debug("用户提供参数格式错误", exception);
        return ResponseEntity.ok(ResultUtil.error(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage()));
    }

    /**
     * 统一处理 Controller 层抛出的参数错误异常 - 用户提供参数格式错误。
     * @param exception 参数错误异常
     * @return 报错信息
     */
    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,})
    public ResponseEntity<ResultDto<Void>> processMethodArgumentNotValidException(
        MethodArgumentNotValidException exception) {
        log.debug("用户提供参数格式错误", exception);
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String msg = fieldError == null ? exception.getMessage() : fieldError.getDefaultMessage();
        return ResponseEntity.ok(ResultUtil.error(String.valueOf(HttpStatus.BAD_REQUEST.value()), msg));
    }

    /**
     * 统一处理 Controller 层抛出的 RequestBody 反序列化错误异常 - 用户提供参数格式错误。
     * @param exception RequestBody 参数错误异常
     * @return 报错信息
     */
    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<ResultDto<Void>> processHttpMessageNotReadableException(
        HttpMessageNotReadableException exception) {
        log.debug("RequestBody 反序列化错误", exception);
        //截取错误信息，保证信息简洁明晰
        String errorMsg = exception.getMessage();
        String pattern = "(?<=JSON parse error:)[^\\;]+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(errorMsg);
        if (m.find()) {
            errorMsg = m.group(0);
        }
        return ResponseEntity.ok(ResultUtil.error(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorMsg));
    }

    /**
     * 统一处理 Service 层抛出的参数错误异常 - 用户提供参数与业务逻辑相悖。
     * @param exception 参数错误异常
     * @return 报错信息
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResultDto<Void>> processIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.ok(ResultUtil.error(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage()));
    }

    /**
     * 统一处理后端程序 404 异常。
     * @param exception 404 异常
     * @return 报错信息
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResultDto<Void>> handle404Error(Exception exception)   {
        return ResponseEntity.ok(ResultUtil.error(String.valueOf(HttpStatus.NOT_FOUND.value()),
               exception.getMessage()));
    }

    /**
     * 统一处理后端程序运行时异常。
     * @param exception 运行时异常
     * @return 报错信息
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResultDto<Void>> processRuntimeException(RuntimeException exception) {
        UUID uuid = UUID.randomUUID();
        log.error("Runtime error " + uuid, exception);
        String msg = "对不起，系统发生内部错误，请联系开发运维团队。错误编号：" + uuid;

        return ResponseEntity.ok(ResultUtil.error(CONST.RUNTIME_EXCEPTION, msg));
    }

    /**
     * 统一处理算法的参数校验异常
     * @param exception 参数校验异常
     * @return 报错信息
     */
    @ExceptionHandler(ParamChekErrorException.class)
    public ResponseEntity<ResultDto<Void>> processComputeParamChek(RuntimeException exception) {
        return ResponseEntity.ok(ResultUtil.error(CONST.RUNTIME_EXCEPTION, exception.getMessage()));
    }
}
