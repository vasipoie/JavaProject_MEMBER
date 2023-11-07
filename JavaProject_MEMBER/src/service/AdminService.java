package service;

import java.util.List;

import controller.Controller;
import dao.AdminDao;
import vo.Admin;

public class AdminService {
	private static AdminService singleTon = null;

	private AdminService() {
	};

	public static AdminService getInstance() {
		if (singleTon == null) {
			singleTon = new AdminService();
		}
		return singleTon;
	}

	AdminDao adDao = AdminDao.getInstance();
	
	public boolean login(List<Object> param) {
		
		Admin ad = adDao.login(param);
		if(ad != null) {
			Controller.sessionStorage.put("admin",ad);
			return true;
		}
		return false;
	}
	
}
