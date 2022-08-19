package pres.hw.ems.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import com.hw.domain.StoreEm;
import pres.hw.ems.domain.User;

public class QueryUtil {

	/**
	 * 查找符合名字的设备
	 * 
	 * @param name
	 * @param storeEms
	 * @return
	 */
//	public static List<StoreEm> searchInName(String name, List<StoreEm> storeEms) {
//		Iterator<StoreEm> its = storeEms.iterator();
//		String emName;
//		StoreEm storeEm;
//		List<StoreEm> queryEms = new ArrayList<StoreEm>();
//		while (its.hasNext()) {
//			storeEm = its.next();
//			emName = storeEm.emName.toLowerCase();
//			if (emName.indexOf(name) != -1) {
//				queryEms.add(storeEm);
//			}
//		}
//		return queryEms;
//	}

	/**
	 * 从数据库中查找系统级用户
	 * 
	 * @param name
	 * @param users
	 * @return
	 */
	
	public static Boolean searchUserId(int id, List<User> users) {
		for (User user : users) {
			if (id==user.getUserid()) {
				return true;
			}
		}
		return false;

	}
	
	public static Boolean searchUserName(String name, List<User> users) {
		for (User user : users) {
			if (name.equals(user.getUserName())) {
				return true;
			}
		}
		return false;

	}

	public static boolean searchPassword(String password, List<User> users) {
		for(User user:users) {
			if (password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public static Boolean searchBoth(String name,String password,List<User> users) {
		for(User user : users) {
			if (name.equals(user.getUserName()) && password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
		
	}
	
	public static Boolean searchIdName(int id,String name,List<User> users) {
		for(User user : users) {
			if (name.equals(user.getUserName()) && id == user.getUserid()) {
				return true;
			}
		}
		return false;
		
	}
	
	public static Boolean searchAll(String name,String password,List<User> users, String type) {
		for(User user : users) {
			if (name.equals(user.getUserName()) && password.equals(user.getPassword())
					&& type.equals(user.getType())) {
				return true;
			}
		}
		return false;
		
	}
}
