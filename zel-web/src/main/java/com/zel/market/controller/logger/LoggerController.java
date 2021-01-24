package com.zel.market.controller.logger;

//import org.apache.log4j.*;
import com.zel.market.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RestController
@RequestMapping(value = "/logger")
public class LoggerController {

    /**
     *
     * @param p 需要修改的包名
     * @param l 日志级别
     * @return
     */
    @RequestMapping(value = "/change", method = RequestMethod.GET, produces = "application/json")
    public Response change(String p, String l) {
//        try {
//            Level level = Level.toLevel(l);
//            Logger logger = LogManager.getLogger(p);
//            logger.setLevel(level);
//        } catch (Exception e) {
//            return "failed";
//        }
//        return "success";
        return null;
    }

    /**
     * 修改全局日志级别
     * 可获取到Logger们后循环遍历设置
     * @param l
     * @return
     */
    @RequestMapping(value = "/changeRoot", method = RequestMethod.GET)
    public Response change(String l) {
//        try {
//            Level level = Level.toLevel(l);
//            LogManager.getRootLogger().setLevel(level);
//        } catch (Exception e) {
//            return "failed";
//        }
//        return "success";
        return null;
    }

    /**
     * 查看现在包的日志级别
     * @return
     */
    @RequestMapping(value = "/loggers", method = RequestMethod.GET)
    public Response index(HttpServletRequest request, HttpServletResponse response) {
//        Logger root = LogManager.getRootLogger();
//        Enumeration appenders = root.getAllAppenders();
        return null;
    }
}
