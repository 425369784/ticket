package service;

import pojo.Admin;

public interface IAdminService {
	Admin login(String username,String password);
}
