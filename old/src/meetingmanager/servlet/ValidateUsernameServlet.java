package meetingmanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.UserDAO;
import meetingmanager.vo.User;

/**
 * Servlet implementation class ValidateUsernameServlet
 */
@WebServlet("/ValidateUsernameServlet")
public class ValidateUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidateUsernameServlet() {
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
		boolean flag = true;
		String message = "";
		UserDAO dao = new UserDAO();
		User e = dao.selectByUsername(request.getParameter("username"));
		if (e == null) {
			message = "用户名可以使用";

		} else {
			flag = false;
			message = "用户名已经存在，请选择使用其他用户名";
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String json = "{\"msg\":\"" + message + "\",\"flag\":" + flag + "}";
		out.print(json);
	}

}
