package service.impl;

import service.ITicketService;
import sun.security.krb5.internal.Ticket;
import util.DBUtil;
import util.Page;

public class TicketServiceImpl implements ITicketService {

	@Override
	public Page select(String key, Page page) {
		String sql = "select * from ticket where name like ?";  
		Object[] params = {"%"+key+"%"};
		
		Page resPage = DBUtil.select(sql, params, page, Ticket.class);
		return resPage;
	}

}
