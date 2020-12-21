package com.zel.market.controller.ss;

import com.zel.DateUtil;
import com.zel.dbmanager.entity.SSAccount;
import com.zel.market.common.Response;
import com.zel.market.config.Config;
import com.zel.market.service.ss.SSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/3/20
 */
@RestController
@RequestMapping(value = "/ss")
public class SSController {

    @Autowired
    private SSService ssService;

    /**
     * 请求 ss
     * @param email
     * @return
     */
    @GetMapping("/account/email")
    public Response index(@RequestParam @NotNull(message = "email 不能为空") String email) {
        List<SSAccount> account = ssService.getAccountWithThreadRunnable(email);
        return Response.ok(account);
    }

    /**
     * enable ss
     * @return
     */
    @GetMapping("/account/enable")
    public Response enableSS() {
        Map<String, Boolean> map = new HashMap<>();

        Config.ENABLE_SS_ACCOUNT_REQUEST = true;
        map.put("enableSSAccountRequest", Config.ENABLE_SS_ACCOUNT_REQUEST);

        return Response.ok(map);
    }

}
