package com.zel.market.exception;


import com.zel.market.common.Response;
import com.zel.market.common.enumcom.EResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 开启了全局异常的捕获
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 处理自定义的业务异常
     *
     * @param req HttpServletRequest
     * @param e 异常
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
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        return Response.error("发生空指针异常！原因是:" + e);
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        return Response.error("未知异常！原因是:" + e);
    }
}
