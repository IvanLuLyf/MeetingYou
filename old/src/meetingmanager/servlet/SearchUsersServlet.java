package meetingmanager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.service.UserService;
import meetingmanager.vo.User;

/**
 * Servlet implementation class SearchEmployeesServlet
 */
@WebServlet("/SearchUsersServlet")
public class SearchUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchUsersServlet() {
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
		UserService service = new UserService();

		String name = request.getParameter("name");
		String username = request.getParameter("username");

		String strVerified = request.getParameter("verified");
		int verified;

		if (strVerified == null || strVerified.equals("")) {
			verified = -1;
		} else {
			verified = Integer.parseInt(request.getParameter("verified"));
		}

		// 当前页码
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 0;
		if (pageNumStr == null || pageNumStr.equals("")) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(pageNumStr);
		}

		// 每页的记录数量
		int pageSize = service.getPageSize();
		// 起始记录索引
		int start = (pageNum - 1) * pageSize;
		// 查询的数量
		int count = pageSize;
		// 获得所有记录数量，先调用DAO中的search方法
		service.searchUsers(name, username, verified);
		int countOfUsers = service.getCountOfUsers();
		// 页数
		int countOfPages = service.getCountOfPages();

		List<User> list = service.searchUsersOfOnePage(name, username,
				verified, start, count);
		request.setAttribute("usersList", list);

		// 使用search标记调用了SearchEmployeesServlet,即显示结果表格
		request.setAttribute("search", "1");
		// 存储页数、所有记录的数量、当前页码
		request.setAttribute("countOfPages", countOfPages);
		request.setAttribute("countOfUsers", countOfUsers);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("searchusers.jsp").forward(request,
				response);
	}

}
