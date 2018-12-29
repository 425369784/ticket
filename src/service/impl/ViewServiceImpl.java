package service.impl;

import pojo.View;
import service.IViewService;
import util.DBUtil;
import util.Page;

public class ViewServiceImpl implements IViewService {

	@Override
	public Page select(String key, Page page) {
		System.out.println("key = "+key);
		String sql = "select * from viewpoint where name like ?";  
		Object[] params = {"%"+key+"%"};
		
		Page resPage = DBUtil.select(sql, params, page, View.class);
		return resPage;
	}

}
