package dao;

import pojo.Admin;

public interface IAdminDao {
	Admin select(String username,String password);
}
