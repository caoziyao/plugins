package com.zel.market.exception;


import com.zel.market.common.Response;
import com.zel.market.common.enumcom.EResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 开启了全局异常的捕获
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Response handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
        logger.error(e.getMessage());
        Response r = Response.error(EResponseCode.C403);
        r.setMessage(e.getMessage());
        return r;
    }

    /**
     * 处理自定义的业务异常
     *
     * @param req HttpServletRequest
     * @param e 错误异常
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest req, BusinessException e) {
        return Response.error(e.getMessage());
    }


    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e 错误异常
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        Response r = Response.error("发生空指针异常！");
        r.setDebugMessage(e.toString());
        return r;
    }

    /**
     * Validator 校验异常
     * 或者 MethodArgumentNotValidException
     * @param ex 错误异常
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Response handleMethodVoArgumentNotValidException(HttpServletRequest req, BindException ex) {
        FieldError fieldError = ex.getFieldError();

        // field.ErrorgetField() 读取参数字段
        // field.getDefaultMessage() 读取验证注解中的message值
        // String message = "参数{".concat(fieldError.getField()).concat("}").concat(fieldError.getDefaultMessage());
        String message = "参数" + fieldError.getField() + fieldError.getDefaultMessage();
        logger.info("参数校验失败111 {}", message);
        Response r = Response.error(EResponseCode.C400);
        r.setMessage(message);
        return r;
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e 错误异常
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        Response r = Response.error("未知异常！");
        r.setDebugMessage(e.toString());
        return r;
    }
}
