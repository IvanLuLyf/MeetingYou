package meetingmanager.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.*;
import meetingmanager.vo.*;

/**
 * Servlet implementation class ViewAllUsersServlet
 */
@WebServlet("/ViewAllUsersServlet")
public class ViewAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		UserDAO dao = new UserDAO();
		List<User> employeesList = dao.selectUnverifiedUser();
		request.setAttribute("usersList", employeesList);
		if (code != null & code.equals("approve")) {
			request.getRequestDispatcher("approveaccount.jsp").forward(request, response);
		}
	}

}
