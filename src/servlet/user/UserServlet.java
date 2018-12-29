package servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.User;
import service.IUserService;
import service.impl.UserServiceImpl;
import util.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "user/userServlet", urlPatterns = { "/user/userServlet" })
public class UserServlet extends BaseServlet {
	
	private IUserService iUserService = new UserServiceImpl();
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = iUserService.login(username, password);
		
		if (user != null){
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
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
		session.removeAttribute("user");
		
		response.sendRedirect("login.jsp");
	}


	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
