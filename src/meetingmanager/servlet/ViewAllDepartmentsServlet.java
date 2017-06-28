package meetingmanager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.DepartmentDAO;
import meetingmanager.vo.Department;

/**
 * Servlet implementation class ViewAllDepartmentsServlet
 */
@WebServlet("/ViewAllDepartmentsServlet")
public class ViewAllDepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewAllDepartmentsServlet() {
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
		DepartmentDAO dao = new DepartmentDAO();

		List<Department> departmentsList = dao.selectAll();
		request.setAttribute("departmentsList", departmentsList);
		System.out.println(departmentsList);
		String code = request.getParameter("code");

		if (code != null && code.equals("register")) {
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
		} else if (code != null && code.equals("viewalldepartments")) {
			request.getRequestDispatcher("departments.jsp").forward(request,
					response);
		}
	}

}
