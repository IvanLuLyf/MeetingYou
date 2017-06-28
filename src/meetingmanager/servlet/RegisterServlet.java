package meetingmanager.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.*;
import meetingmanager.service.*;
import meetingmanager.vo.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 或许注册页面填写的请求参数
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		User user = new User(name, username, password, deptid, email,
				phone, 0, 2);
		UserService service = new UserService();
		int flag = service.regist(user);

		if (flag == 1) {
			request.setAttribute("msg", "注册成功，正在审核。");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("msg", "用户名已存在，请重新注册。");
			DepartmentDAO dao = new DepartmentDAO();
			List<Department> departmentsList = dao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
		}
	}

}
