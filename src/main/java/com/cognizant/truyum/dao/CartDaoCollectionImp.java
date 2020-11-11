package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoCollectionImp implements CartDao {
	private static HashMap<Long, Cart> userCarts;

	public CartDaoCollectionImp() {
		super();
		if (userCarts == null) {
			userCarts = new HashMap<Long, Cart>();
		}
	}

	@Override
	public void addCartItem(long userId, long menuItemId) throws ParseException {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem item = menuItemDao.getMenuItem(menuItemId);
		if (userCarts.containsKey(userId)) {
			List<MenuItem> menuItem = userCarts.get(userId).getMenuItemList();
			menuItem.add(item);
			userCarts.get(userId).setMenuItemList(menuItem);
		} else {
			List<MenuItem> newUserMenuList = new ArrayList<>();
			newUserMenuList.add(item);
			Cart cart = new Cart(newUserMenuList);
			userCarts.put(userId, cart);
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
		if (menuItemList == null) {
			throw new CartEmptyException();
		} else {
			double totprice = 0;
			for (MenuItem menuItem : menuItemList) {
				totprice += menuItem.getPrice();
			}
			userCarts.get(userId).setTotal(totprice);
		}
		return menuItemList;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		List<MenuItem> menuItem = userCarts.get(userId).getMenuItemList();
		for (MenuItem menuItem2 : menuItem) {
			if (menuItem2.getId() == menuItemId) {
				menuItem.remove(menuItem2);
			}
		}
	}
}