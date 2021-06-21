package com.zel.market.app.service.coupon;

import com.zel.pojo.entity.Coupon;

import java.util.List;
import com.zel.market.common.Response;
/**
 * 优惠券
 */
public interface CouponService {
    /**
     * 用户可领取的所有优惠券
     *
     * @return
     */
    //Response <Coupon> gotWaitCoupons(String code);


    /**
     * 向用户发放多张优惠券（一键领取）
     *
     * @param couponids
     * @return
     */
    Response sendMultiCoupons(List<Long> couponids);


    /**
     * 向用户发放优惠券（输入优惠券码领取）
     *
     * @param couponCode
     */
    Response sendCoupon(String couponCode);


    /**
     * 订单可用的优惠券表
     *
     * @return
     */
    //ResponseLst<Coupon> orderAvailableCoupons(List<GoodOrderDetail> goodOrderDetails);


    /**
     * 用户的优惠券列表
     *
     * @param status
     * @param offset
     * @param limit
     * @return
     */
    //Response<Coupon> listUserCoupons(Integer status, Integer offset, Integer limit);


    /**
     * 用户的优惠券详情
     *
     * @param userCouponId
     * @return
     */
    //Response<Coupon> userCouponDetail(long userCouponId);


    /**
     * 手动发放优惠券
     *
     * @param code
     * @return
     */
    //Response manualSendCoupon(String code);

}
