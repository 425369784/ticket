package dao;

import pojo.User;

public interface IUserDao {
	User select(String username,String password);
}
