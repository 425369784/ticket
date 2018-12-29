package service;

import pojo.User;

public interface IUserService {
	User login(String username,String password);
}
