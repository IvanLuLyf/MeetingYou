package meetingmanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.*;

/**
 * Servlet implementation class ApproveServlet
 */
@WebServlet("/ApproveServlet")
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApproveServlet() {
		super();
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
		int uid = 0;

		String strUid = request.getParameter("uid");

		if (strUid != null) {
			uid = Integer.parseInt(strUid);
		}
		
		String oper = request.getParameter("oper");
		String code = request.getParameter("code");
		String action = request.getParameter("action");

		UserDAO dao = new UserDAO();
		if (oper != null && oper.equals("yes")) {
			dao.updateStatus(uid, 1);
			request.getRequestDispatcher("ViewAllUsersServlet?code=approve")
					.forward(request, response);
		} else if (oper != null && oper.equals("no")) {
			dao.updateStatus(uid, 2);
			request.getRequestDispatcher("ViewAllUsersServlet?code=approve")
					.forward(request, response);
		} else if (oper != null && oper.equals("close")) {
			dao.updateStatus(uid, 2);
			request.getRequestDispatcher("SearchUsersServlet").forward(request,
					response);
		}

		if (code != null && action != null) {
			if (code.equals("verify")) {
				PrintWriter pw = response.getWriter();
				if (action.equals("yes")) {
					dao.updateStatus(uid, 1);
					pw.print("ok");
				} else if (action.equals("no") || action.equals("close")) {
					dao.updateStatus(uid, 2);
					pw.print("ok");
				} else {
					pw.print("error");
				}
			}
		}
	}

}
