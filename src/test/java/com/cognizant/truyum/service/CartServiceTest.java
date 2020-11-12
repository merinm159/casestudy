package com.cognizant.truyum.service;

import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartServiceTest {
	CartService cartService;

	@Before
	public void initializeService() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.cognizant.truyum");
		context.refresh();
		cartService = (CartService) context.getBean("cartService");
	}

	@Test(expected = CartEmptyException.class)
	public void testGetAllCartItemThrowsExceptionEmptyCart() throws CartEmptyException {
		cartService.getAllCartItems(1);
	}

	@Test(expected = CartEmptyException.class)
	public void testAddCartItemCheck() throws CartEmptyException, ParseException {
		boolean hasSandwich = false;
		for (MenuItem item : cartService.getAllCartItems(1)) {
			if (item.getName().equalsIgnoreCase("Sandwich")) {
				hasSandwich = true;
			}
		}
		assertFalse(hasSandwich);
		hasSandwich = false;
		cartService.addCartItem(1, 1);
		for (MenuItem item : cartService.getAllCartItems(1)) {
			if (item.getName().equalsIgnoreCase("Sandwich")) {
				hasSandwich = true;
			}
		}
		assertTrue(hasSandwich);
	}

	@Test
	public void testRemoveCartitem() throws CartEmptyException {
		boolean hasSandwich = false;
		for (MenuItem item : cartService.getAllCartItems(2)) {
			if (item.getName().equalsIgnoreCase("Sandwich")) {
				hasSandwich = true;
			}
		}
		assertTrue(hasSandwich);

		cartService.removeCartItem(2, 1);
		hasSandwich = false;
		for (MenuItem item : cartService.getAllCartItems(2)) {
			if (item.getName().equalsIgnoreCase("Sandwich")) {
				hasSandwich = true;
			}
		}
		assertFalse(hasSandwich);
	}
}
