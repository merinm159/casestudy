package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.MenuItem;

@Component("menuItemDao")
@ImportResource("classpath:spring-config.xml")
public class MenuItemDaoCollectionImpl implements MenuItemDao {
	@Autowired
	private List<MenuItem> menuItemList;

	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(final List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> customermenu = new ArrayList<MenuItem>();
		Date current = new Date();
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getDateOfLaunch().getTime() <= current.getTime() && menuItem.isActive()) {
				customermenu.add(menuItem);
			}
		}

		return customermenu;

	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {

		for (MenuItem m : menuItemList) {
			if (menuItem.equals(m)) {
				m.setId(menuItem.getId());
				m.setName(menuItem.getName());
				m.setPrice(menuItem.getPrice());
				m.setActive(menuItem.isActive());
				m.setDateOfLaunch(menuItem.getDateOfLaunch());
				m.setCategory(menuItem.getCategory());
				m.setFreeDelivery(menuItem.isFreeDelivery());
				  return;
			}

		}
		 menuItemList.add(menuItem);
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getId() == menuItemId)
				return menuItem;
		}
		return null;
	}

}