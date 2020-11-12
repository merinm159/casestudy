package com.cognizant.truyum.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;
@Service("cartService")
public class CartService {
	
	CartDao cartDao;
	
	public CartDao getCartDao() {
		return cartDao;
	}
	@Autowired
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		return cartDao.getAllCartItems(userId);

	}

	public void addCartItem(long userId, long menuItemId) throws ParseException {
		cartDao.addCartItem(userId, menuItemId);
	}

	public void removeCartItem(long userId, long menuItemId) {
		cartDao.removeCartItem(userId, menuItemId);
	}

}
