package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {
	private List<MenuItem> menuItemList;

	
	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		// TODO Auto-generated method stub
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> m = new ArrayList<MenuItem>();
//		Date currentdate = Calendar.getInstance().getTime();  
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
//      String strDate = dateFormat.format(currentdate); 
//		LocalDate currentdate=LocalDate.now();
//		Date current=new DateUtil().convertToDate(currentdate);
		Date strDate = new DateUtil().convertToDate("23/10/2020");
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getDateOfLaunch().before(strDate) || menuItem.getDateOfLaunch().equals(strDate)) {
				m.add(menuItem);
			}
		}
		return m;
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
			}
			
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		for (MenuItem menuItem : menuItemList) {
			if(menuItem.getId()==menuItemId)
				return menuItem;
		}
		return null;
	}

}