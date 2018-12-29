package servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Admin;
import service.IAdminService;
import service.impl.AdminServiceImpl;
import util.BaseServlet;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(name = "admin/adminServlet", urlPatterns = { "/admin/adminServlet" })
public class AdminServlet extends BaseServlet {
	private IAdminService iAdminService = new AdminServiceImpl();
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Admin admin = iAdminService.login(username, password);
		
		if (admin != null){
			
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			//
			response.sendRedirect("ticket.jsp");
			
		}
		else {
			request.setAttribute("error", "’À∫≈ªÚ√‹¬Î¥ÌŒÛ!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		
		response.sendRedirect("login.jsp");
	}


	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
