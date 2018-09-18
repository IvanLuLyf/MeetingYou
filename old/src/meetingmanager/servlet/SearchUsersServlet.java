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

		// ��ǰҳ��
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 0;
		if (pageNumStr == null || pageNumStr.equals("")) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(pageNumStr);
		}

		// ÿҳ�ļ�¼����
		int pageSize = service.getPageSize();
		// ��ʼ��¼����
		int start = (pageNum - 1) * pageSize;
		// ��ѯ������
		int count = pageSize;
		// ������м�¼�������ȵ���DAO�е�search����
		service.searchUsers(name, username, verified);
		int countOfUsers = service.getCountOfUsers();
		// ҳ��
		int countOfPages = service.getCountOfPages();

		List<User> list = service.searchUsersOfOnePage(name, username,
				verified, start, count);
		request.setAttribute("usersList", list);

		// ʹ��search��ǵ�����SearchEmployeesServlet,����ʾ������
		request.setAttribute("search", "1");
		// �洢ҳ�������м�¼����������ǰҳ��
		request.setAttribute("countOfPages", countOfPages);
		request.setAttribute("countOfUsers", countOfUsers);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("searchusers.jsp").forward(request,
				response);
	}

}
