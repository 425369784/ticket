package service.impl;


import dao.IUserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.IUserService;

public class UserServiceImpl implements IUserService {

	private IUserDao iUserDao = new UserDaoImpl();
	
	@Override
	public User login(String username, String password) {
		return iUserDao.select(username, password);
	}

}
