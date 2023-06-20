package com.marketplace.facilit.cart;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.LockedCartException;
import com.marketplace.facilit.models.cart.CartImpl;
import com.marketplace.facilit.models.coupon.CouponImpl;
import com.marketplace.facilit.models.item.CartItemImpl;
import com.marketplace.facilit.models.product.ProductImpl;

@SpringBootTest
public class CartTests {

    CartImpl sampleCart;
    @Before
    public void generateCart() {
        sampleCart = new CartImpl();
    }

    @Test
    public void testCreateCart() {

        CartImpl cart = new CartImpl();
        List<CartItemImpl> items = cart.getItems();
        Assert.assertArrayEquals(items.toArray(), new Object[] {} );
        Assert.assertTrue(!cart.isClosed());
        Assert.assertTrue(cart.calculateFinalPrice() == 0);
        Assert.assertTrue(cart.calculateTotalPrice() == 0);
    }

    @Test
    public void calculateTotalPriceWithEmptyCart() {

        Assert.assertTrue(sampleCart.calculateFinalPrice() == 0f);
        Assert.assertTrue(sampleCart.calculateTotalPrice() == 0f);
    }

    @Test
    public void addItem() throws EmptyFieldException, LockedCartException {
        CartItemImpl item = new CartItemImpl();
        item.setProduct(getNotebookProduct());
        item.setAmount(2);
        sampleCart.addCartItem(item);

        Assert.assertTrue(sampleCart.getItems().size() == 1);
        Assert.assertTrue(sampleCart.getItems().get(0).getProduct().equals(getNotebookProduct()));
    }

    @Test
    public void addItemWithAmountZero() throws EmptyFieldException, LockedCartException {
        CartItemImpl item = new CartItemImpl();
        item.setProduct(getNotebookProduct());
        item.setAmount(0);
        sampleCart.addCartItem(item);
        Assert.assertTrue(sampleCart.getItems().isEmpty());
    }

    @Test
    public void addMultiplesItems() throws EmptyFieldException, LockedCartException {
        int TOTAL_ITEMS =15;

        CartItemImpl item = new CartItemImpl();
        item.setProduct(getNotebookProduct());
        item.setAmount(2);
        sampleCart.addCartItem(item);

        CartItemImpl item2 = new CartItemImpl();
        item2.setProduct(getDesktopProduct());
        item2.setAmount(5);
        sampleCart.addCartItem(item2);

        CartItemImpl item3 = new CartItemImpl();
        item3.setProduct(getPencilProduct());
        item3.setAmount(8);
        sampleCart.addCartItem(item3);

        int count = 0;

        for (CartItemImpl currentItem :sampleCart.getItems()) {
            count += currentItem.getAmount();
        }

        Assert.assertTrue(sampleCart.getItems().size() == 3);
        Assert.assertTrue(count == TOTAL_ITEMS);
    }

    @Test
    public void calculateTotalPrice() throws EmptyFieldException, LockedCartException {

        CartItemImpl item = new CartItemImpl();
        item.setProduct(getNotebookProduct());
        item.setAmount(2);
        sampleCart.addCartItem(item);

        CartItemImpl item2 = new CartItemImpl();
        item2.setProduct(getDesktopProduct());
        item2.setAmount(5);
        sampleCart.addCartItem(item2);

        CartItemImpl item3 = new CartItemImpl();
        item3.setProduct(getPencilProduct());
        item3.setAmount(8);
        sampleCart.addCartItem(item3);

        float totalPrice = 0f;
        for (CartItemImpl currentItem : sampleCart.getItems()) {
            totalPrice += currentItem.getProduct().getPrice() * currentItem.getAmount();
        }

        Assert.assertTrue(totalPrice == (306.42f + 6266.05f + 28));
        Assert.assertTrue(totalPrice == sampleCart.calculateTotalPrice());
        Assert.assertTrue(totalPrice == sampleCart.calculateFinalPrice());
    }

    @Test
    public void testCoupon() throws EmptyFieldException, LockedCartException {

        CartItemImpl item = new CartItemImpl();
        item.setProduct(getNotebookProduct());
        item.setAmount(2);
        sampleCart.addCartItem(item);

        CouponImpl coupon = new CouponImpl(15.75f, "Desconto para testes");
        sampleCart.setCoupon(coupon);

        float itemPrice = sampleCart.getItems().get(0).calculateItemPrice();

        Assert.assertTrue(itemPrice == sampleCart.calculateTotalPrice());
        Assert.assertTrue((itemPrice-coupon.getPrice()) == sampleCart.calculateFinalPrice());
    }

    @Test
    public void testCouponWithZeroDiscount() throws EmptyFieldException, LockedCartException {

        CartItemImpl item = new CartItemImpl();
        item.setProduct(getNotebookProduct());
        item.setAmount(2);
        sampleCart.addCartItem(item);

        CouponImpl coupon = new CouponImpl(0f, "Desconto para testes");
        sampleCart.setCoupon(coupon);

        float itemPrice = sampleCart.getItems().get(0).calculateItemPrice();

        Assert.assertTrue(itemPrice == sampleCart.calculateTotalPrice());
        Assert.assertTrue((itemPrice-coupon.getPrice()) == sampleCart.calculateFinalPrice());
    }

    @Test
    public void throwErrorIfTryToAddItemOnClosedCart() throws EmptyFieldException, LockedCartException {
        sampleCart.setClosed(true);
        Assert.assertThrows(LockedCartException.class, () -> {
            CartItemImpl item = new CartItemImpl();
            item.setProduct(getNotebookProduct());
            item.setAmount(2);
            sampleCart.addCartItem(item);
        });
    }

    @Test
    public void throwErrorIfTryToRemoveItemOnClosedCart() throws EmptyFieldException, LockedCartException {
        CartItemImpl item = new CartItemImpl();
        item.setProduct(getNotebookProduct());
        item.setAmount(2);
        Long itemId = Long.valueOf("32");
        item.setId(itemId);
        sampleCart.addCartItem(item);

        sampleCart.setClosed(true);
        Assert.assertThrows(LockedCartException.class, () -> {
            sampleCart.deleteItem(itemId);
        });
    }

    @Test
    public void updatingCoupon() throws EmptyFieldException, LockedCartException {
        CouponImpl coupon = new CouponImpl(15.75f, "Desconto para testes");
        sampleCart.updateCoupon(coupon);

        CouponImpl coupon2 = new CouponImpl(15.76f, "Desconto para testes 2");
        sampleCart.updateCoupon(coupon2);

        Assert.assertTrue(sampleCart.getCoupon().getPrice() == 15.76f);
        Assert.assertTrue(sampleCart.getCoupon().getLabel().equals("Desconto para testes 2"));
    }

    @Test
    public void updatingCouponWithClosedCart() throws EmptyFieldException, LockedCartException {

        sampleCart.setClosed(true);
        Assert.assertThrows(LockedCartException.class, () -> {
            CouponImpl coupon = new CouponImpl(15.75f, "Desconto para testes");
            sampleCart.updateCoupon(coupon);

            CouponImpl coupon2 = new CouponImpl(15.76f, "Desconto para testes 2");
            sampleCart.updateCoupon(coupon2);
        });
    }

    @Test
    public void removeAllItems() throws EmptyFieldException, LockedCartException {

        CartItemImpl item = new CartItemImpl();
        item.setProduct(getNotebookProduct());
        item.setAmount(2);
        sampleCart.addCartItem(item);

        CartItemImpl item2 = new CartItemImpl();
        item2.setProduct(getDesktopProduct());
        item2.setAmount(5);
        sampleCart.addCartItem(item2);

        CartItemImpl item3 = new CartItemImpl();
        item3.setProduct(getPencilProduct());
        item3.setAmount(8);
        sampleCart.addCartItem(item3);

        sampleCart.deleteAllItems();

        Assert.assertTrue(sampleCart.calculateFinalPrice() == 0);
        Assert.assertTrue(sampleCart.calculateTotalPrice() == 0);
        Assert.assertTrue(sampleCart.getItems().isEmpty());

    }


    @Test
    public void a() {

        String group = Pattern.compile("^(?:https?://)(?:[^@/\\n]+@)?(?:www.)?([^:/?\\n]+)")
                              .matcher("https://dashboard.plataformatarget.com.br/")
                              .group(1);

        Assert.assertEquals(group, "dashboard.plataformatarget.com.br");
    }


    public ProductImpl getNotebookProduct() throws EmptyFieldException {
        return new ProductImpl("Notebook", 153.21f);
    }

    public ProductImpl getDesktopProduct() throws EmptyFieldException {
        return new ProductImpl("Desktop", 1253.21f);
    }

    public ProductImpl getPencilProduct() throws EmptyFieldException {
        return new ProductImpl("Pencil", 3.5f);
    }


}
