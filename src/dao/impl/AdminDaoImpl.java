package dao.impl;

import java.util.List;

import dao.IAdminDao;
import pojo.Admin;
import pojo.Ticket;
import pojo.User;
import pojo.Uticket;
import pojo.View;
import util.DBUtil;
import util.Page;

public class AdminDaoImpl implements IAdminDao {

	@Override
	public Admin select(String username, String password) {
		String sql = "select * from admin where id = ? and pwd = ?";
		Object[] params = {username, password};
		return DBUtil.getObject(sql, params, Admin.class);
	}
	public int add(View view) {
		String sql = "insert into viewpoint (name, infor) values(?,?)";
		Object[] params = {view.getName(),view.getInfor()};
		return DBUtil.update(sql, params);
	}
	public int delete(int vid) {
		String sql = "delete from viewpoint where id = ?";
		Object[] params = {vid};
		return DBUtil.update(sql, params);
	}
	public int update(int vid, String vname,String vinfo) {
		String sql="update viewpoint set name = ? , infor = ? where id = ?";
		Object[] params={vname,vinfo,vid};
		
		return DBUtil.update(sql, params);
	}
	public Page select(int id, Page page) {
		// TODO Auto-generated method stub
		String sql = "select * from ticket where vpid = ?";
		Object[] params = {id};
		Page resPage = DBUtil.select(sql, params, page, Ticket.class);
		return resPage;
	}
	public List<View> select() {
		// TODO Auto-generated method stub
		String sql = "select * from viewpoint ";
		List<View> resList = DBUtil.select(sql, null, View.class);

		return resList;
	}
	public int add(Ticket t) {
		// TODO Auto-generated method stub
		String sql = "insert into ticket (name, infor, vpid, price) values(?,?,?,?)";
		Object[] params = {t.getName(),t.getInfor(),t.getVpid(),t.getPrice()};
		return DBUtil.update(sql, params);
	}
	public int deleteT(int tid) {
		// TODO Auto-generated method stub
		String sql = "delete from ticket where id = ?";
		Object[] params = {tid};
		return DBUtil.update(sql, params);
	}
	public int updateT(int tid, String tname, String tinfo,String sprice) {
		// TODO Auto-generated method stub
		int price = Integer.parseInt(sprice);
		String sql="update ticket set name = ? , infor = ? , price = ?  where id = ?";
		Object[] params={tname,tinfo,price,tid};
		
		return DBUtil.update(sql, params);
	}
	public int buy(Uticket t) {
		// TODO Auto-generated method stub
		String sql = "insert into uticket (name, vpid, price) values(?,?,?)";
		Object[] params = {t.getName(),t.getVpid(),t.getPrice()};
		return DBUtil.update(sql, params);
	}
	public Page selectT(int id, Page page) {
		// TODO Auto-generated method stub
		String sql = "select * from uticket where vpid = ?";
		Object[] params = {id};
		Page resPage = DBUtil.select(sql, params, page, Uticket.class);
		return resPage;
	}
	public int delT(int tid) {
		// TODO Auto-generated method stub
		String sql = "delete from uticket where id = ?";
		Object[] params = {tid};
		return DBUtil.update(sql, params);
	}
}
