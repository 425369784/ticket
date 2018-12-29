package service.impl;

import dao.IAdminDao;
import dao.impl.AdminDaoImpl;
import pojo.Admin;
import service.IAdminService;

public class AdminServiceImpl implements IAdminService {
	private IAdminDao iAdminDao = new AdminDaoImpl();
	
	@Override
	public Admin login(String username, String password) {
		return iAdminDao.select(username, password);
	}
}
