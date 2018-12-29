package servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.ITicketService;
import service.impl.TicketServiceImpl;
import util.BaseServlet;
import util.Page;

/**
 * Servlet implementation class TicketServlet
 */
@WebServlet(name = "admin/ticketServlet", urlPatterns = { "/admin/ticketServlet" })
public class TicketServlet extends BaseServlet {
	private ITicketService iTicketService = new TicketServiceImpl();
	

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
		
		Page resPage = iTicketService.select(key, page);
		
		Gson gson = new Gson();
		String strJSON = gson.toJson(resPage);
		
		System.out.println(strJSON);
		
		response.getWriter().print(strJSON);
	}


//	public void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		String spname = request.getParameter("spname");
//		
//		int res = iSpecialtyService.add(spname);
//		
//		if (res > 0){
//			response.getWriter().print("{\"res\" : 1}");
//		}
//		else {
//			response.getWriter().print("{\"res\" : 0}");
//		}		
//	}
//	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		String strSpid = request.getParameter("spid");
//		String spname = request.getParameter("spname");
//		int spid = Integer.parseInt(strSpid);
//		int res = iSpecialtyService.update(spid,spname);
//		
//		if (res > 0){
//			response.getWriter().print("{\"res\" : 1}");
//		}
//		else {
//			response.getWriter().print("{\"res\" : 0}");
//		}		
//	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println( this.getClass().getName() + "");
		
	}

}
