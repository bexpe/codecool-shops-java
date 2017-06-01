package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by beata on 29.05.17.
 */
class BasketTest {
    private Basket basket;
    private BasketItem basketItem;
    private Product product;
    private List<BasketItem> basketItemList;

    @BeforeEach
    void setUp() {
        basket = new Basket();
        basketItem = mock(BasketItem.class);
        product = mock(Product.class);
    }
}