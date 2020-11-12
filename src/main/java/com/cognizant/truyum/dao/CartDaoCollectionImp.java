package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.*;
import com.cognizant.truyum.service.MenuItemService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

//@Component
//@ImportResource("classpath:spring-config.xml")
public class CartDaoCollectionImp implements CartDao {
//	@Autowired
//	@Qualifier("cartDaoMap")
	private Map<Long, Cart> userCarts;

	public Map<Long, Cart> getUserCarts() {
		return userCarts;
	}

	public void setUserCarts(Map<Long, Cart> userCarts) {
		this.userCarts = userCarts;
	}

	public CartDaoCollectionImp(Map<Long, Cart> userCarts) {
		super();
		this.userCarts = userCarts;
	}

	public CartDaoCollectionImp() {
		super();
	}

	@Override
	public void addCartItem(long userId, long menuItemId) throws ParseException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		MenuItemService menutItemService = (MenuItemService) ctx.getBean("menuItemService");
		MenuItem menuItem = menutItemService.getMenuItem(menuItemId);

		if (userCarts.containsKey(userId)) {
			List<MenuItem> menui = userCarts.get(userId).getMenuItemList();
			menui.add(menuItem);
			userCarts.get(userId).setMenuItemList(menui);
		} else {
			List<MenuItem> newUserMenuList = new ArrayList<>();
			newUserMenuList.add(menuItem);
			Cart cart = new Cart(newUserMenuList);
			userCarts.put(userId, cart);
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
		if (menuItemList == null || menuItemList.isEmpty()) {
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