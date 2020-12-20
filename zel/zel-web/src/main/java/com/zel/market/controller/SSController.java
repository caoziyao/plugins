package com.zel.market.controller;

import com.zel.dbmanager.entity.SSAccount;
import com.zel.market.common.Response;
import com.zel.market.service.ss.SSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    @GetMapping("/account/email")
    public Response index(@RequestParam @NotNull(message = "email 不能为空") String email) {
        List<SSAccount> account = ssService.getAccountWithThreadRunnable(email);
        return Response.ok(account);
    }
}
