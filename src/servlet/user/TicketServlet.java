package servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.impl.AdminDaoImpl;
import pojo.Ticket;
import pojo.Uticket;
import pojo.View;
import service.ITicketService;
import service.impl.TicketServiceImpl;
import util.BaseServlet;
import util.Page;

/**
 * Servlet implementation class TicketServlet
 */
@WebServlet(name = "user/ticketServlet", urlPatterns = { "/user/ticketServlet" })
public class TicketServlet extends BaseServlet {
	private ITicketService iTicketService = new TicketServiceImpl();
	
//	public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	
//		List<Specialty> list = iSpecialtyService.select();
//		
//		Gson gson = new Gson();
//		String strJSON = gson.toJson(list);
//		
//		System.out.println(strJSON);
//		
//		response.getWriter().print(strJSON);
//	}

	private AdminDaoImpl admindao = new AdminDaoImpl();
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Sid = request.getParameter("id");
		System.out.println("seacrchSid="+Sid);
		int id;
		if (Sid == "" ){
			id = 1;
		}
		else {
			id=Integer.parseInt(Sid);
		}
		
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null){
			pageNum = "1";
		}
		
		Page page = new Page();
		page.setPageNum(Integer.parseInt(pageNum));
		
		Page resPage = admindao.select(id, page);
		
		Gson gson = new Gson();
		String strJSON = gson.toJson(resPage);
		
		System.out.println(strJSON);
		
		response.getWriter().print(strJSON);
	}
	
	public void selectT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Sid = request.getParameter("id");
		System.out.println("seacrchSid="+Sid);
		int id;
		if (Sid == "" ){
			id = 1;
		}
		else {
			id=Integer.parseInt(Sid);
		}
		
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null){
			pageNum = "1";
		}
		
		Page page = new Page();
		page.setPageNum(Integer.parseInt(pageNum));
		
		Page resPage = admindao.selectT(id, page);
		
		Gson gson = new Gson();
		String strJSON = gson.toJson(resPage);
		
		System.out.println(strJSON);
		
		response.getWriter().print(strJSON);
	}
	
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	public void getView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		List<View> list = admindao.select();
		
		Gson gson = new Gson();
		String dataJSON = gson.toJson(list);
		
		response.getWriter().print(dataJSON);
	}
	
	public void addTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("addview1");
		String name = request.getParameter("name");
		String infor = request.getParameter("infor");
		String svpid = request.getParameter("vpid");
		int vpid = Integer.parseInt(svpid);
		String sprice = request.getParameter("price");
		int price = Integer.parseInt(sprice);
		Ticket t = new Ticket();
		t.setInfor(infor);
		t.setName(name);
		t.setPrice(price);
		t.setVpid(vpid);
		System.out.println("addview2");
		int res = admindao.add(t);
		
		if (res > 0){
			response.getWriter().print("{\"res\" : 1}");
		}
		else {
			response.getWriter().print("{\"res\" : 0}");
		}		
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tid = request.getParameter("id");
		
		int res = admindao.deleteT(Integer.parseInt(tid));
		
		if (res > 0){
			response.getWriter().print("{\"res\" : 1}");
		}
		else {
			response.getWriter().print("{\"res\" : 0}");
		}	
	}
	
	public void delT(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tid = request.getParameter("id");
		
		int res = admindao.delT(Integer.parseInt(tid));
		
		if (res > 0){
			response.getWriter().print("{\"res\" : 1}");
		}
		else {
			response.getWriter().print("{\"res\" : 0}");
		}	
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String strvid=request.getParameter("tid");
		int tid = Integer.parseInt(strvid);
		String tname=request.getParameter("tname");
		String tinfo=request.getParameter("tinfo");
		String price=request.getParameter("price");
		System.out.println("updata"+tid+tname+tinfo);
		int res=admindao.updateT(tid, tname,tinfo,price);
		
		if (res > 0){
			response.getWriter().print("{\"res\" : 1}");
		}
		else {
			response.getWriter().print("{\"res\" : 0}");
		}	
    }
	
	public void buyTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("addview1");
		String name = request.getParameter("tname");
		String svpid = request.getParameter("vpid");
		int vpid = Integer.parseInt(svpid);
		String sprice = request.getParameter("price");
		int price = Integer.parseInt(sprice);
		Uticket t = new Uticket();
		t.setName(name);
		t.setPrice(price);
		t.setVpid(vpid);
		System.out.println(t);
		//System.out.println("addview2");
		int res = admindao.buy(t);
		
		if (res > 0){
			response.getWriter().print("{\"res\" : 1}");
		}
		else {
			response.getWriter().print("{\"res\" : 0}");
		}		
	}
	
}
