package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void testAddGetItemsToBasketList() {
        when(product.getId()).thenReturn(1);
        basket.add(product,3);

        Product product1 = mock(Product.class);
        when(product.getId()).thenReturn(2);
        basket.add(product1,1);

        assertEquals(2, basket.getItems().size() );
    }

    @Test
    void testAddItemsToBasketListWithSameId() {
        basket.add(product,3);
        Product product1 = mock(Product.class);
        basket.add(product1,1);

        assertEquals(1, basket.getItems().size() );
        assertEquals(4, basket.getItems().get(0).getQuantity().intValue());
    }

    @Test
    void testSetItems() {
        basketItemList = new ArrayList<>();
        basketItemList.add(basketItem);
        basket.setItems(basketItemList);
        assertEquals(1, basket.getItems().size());
    }
}