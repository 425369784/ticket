package dao.impl;


import dao.IUserDao;
import pojo.User;
import pojo.View;
import util.DBUtil;

public class UserDaoImpl implements IUserDao {

	@Override
	public User select(String username, String password) {
		String sql = "select * from user where account = ? and password = ?";
		Object[] params = {username, password};
		return DBUtil.getObject(sql, params, User.class);
	}
	
	public int add(View view) {
		String sql = "insert into viewpoint (name, infor) values(?,?)";
		Object[] params = {view.getName(),view.getInfor()};
		
		return DBUtil.update(sql, params);
	}
	
}
