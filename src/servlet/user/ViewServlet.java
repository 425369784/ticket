package servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pojo.View;
import service.IViewService;
import service.impl.ViewServiceImpl;
import util.BaseServlet;
import util.Page;
import dao.impl.*;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet(name = "user/viewServlet", urlPatterns = { "/user/viewServlet" })
public class ViewServlet extends BaseServlet {
	private IViewService iViewService = new ViewServiceImpl();
	private AdminDaoImpl admindao = new AdminDaoImpl();
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		
		if (key ==null){
			key = "";
		}
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null){
			pageNum = "1";
		}
		
		Page page = new Page();
		page.setPageNum(Integer.parseInt(pageNum));
		
		Page resPage = iViewService.select(key, page);
		
		/*request.setAttribute("splist", list);
		request.getRequestDispatcher("specialty.jsp").forward(request, response);*/
		
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
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("vname");
		String infor = request.getParameter("vinfo");
		View view=new View();
		view.setInfor(infor);
		view.setName(name);
		System.out.println("addview");
		int res = admindao.add(view);
		
		if (res > 0){
			response.getWriter().print("{\"res\" : 1}");
		}
		else {
			response.getWriter().print("{\"res\" : 0}");
		}		
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String vid = request.getParameter("vid");
		
		int res = admindao.delete(Integer.parseInt(vid));
		
		if (res > 0){
			response.getWriter().print("{\"res\" : 1}");
		}
		else {
			response.getWriter().print("{\"res\" : 0}");
		}	
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String strvid=request.getParameter("vid");
		int vid = Integer.parseInt(strvid);
		String vname=request.getParameter("vname");
		String vinfo=request.getParameter("vinfo");
		int res=admindao.update(vid, vname,vinfo);
		System.out.println("updata"+vid+vname+vinfo);
		if (res > 0){
			response.getWriter().print("{\"res\" : 1}");
		}
		else {
			response.getWriter().print("{\"res\" : 0}");
		}	
    }
}
