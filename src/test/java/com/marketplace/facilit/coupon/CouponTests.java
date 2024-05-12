package com.marketplace.facilit.coupon;

import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.models.coupon.CouponImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CouponTests {

    @Test
    public void testCreatingCouponFromCouponForm() {

        CouponForm couponForm = new CouponForm("couponName", 13.21f);

        CouponImpl coupon = new CouponImpl(couponForm);

        Assert.assertEquals(coupon.getLabel(), couponForm.getLabel());
        Assert.assertTrue(coupon.getPrice() == couponForm.getPrice());

    }
}
