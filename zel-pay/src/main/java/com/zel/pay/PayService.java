package com.zel.pay;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首先支付系统我们可以理解成是一个适配器。他需要把很多第三方的接口进行统一的整合封装后，对内部提供统一的接口，减少内部接入的成本
 * 给内部调用
 *
 * 发起支付，我们取名：/gopay
 * 发起退款，我们取名：/refund
 * 接口异步通知，我们取名：/notify/支付渠道/商户交易号
 * 接口同步通知，我们取名：/return/支付渠道/商户交易号
 * 交易查询，我们取名：/query/trade
 * 退款查询，我们取名：/query/refund
 * 账单获取，我们取名：/query/bill
 * 结算明细，我们取名：/query/settle
 */
public class PayService {


    /**
     * 收藏文章
     *
     * @param id 文章id
     * @return
     */
    public String collect() {
        return "Response.ok(id)";
    }
}
