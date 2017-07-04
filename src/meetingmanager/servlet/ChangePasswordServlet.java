package meetingmanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.*;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
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
		String originPassword = request.getParameter("porigin");
		String newPassword = request.getParameter("pnew");
		Integer uid = (Integer) request.getSession().getAttribute("uid");

		if(uid!=null){
			UserDAO dao = new UserDAO();
			int flag = dao.updatePassword(uid, originPassword, newPassword);
			if(flag==0){
				request.setAttribute("err_msg", "Ô­ÃÜÂë´íÎó");
				request.getRequestDispatcher("changepassword.jsp").forward(request,
						response);
			}else if(flag == 1){
				request.setAttribute("ok_msg", "ÃÜÂëÐÞ¸Ä³É¹¦");
				request.getRequestDispatcher("changepassword.jsp").forward(request,
						response);
			}else{
				request.setAttribute("err_msg", "Î´Öª´íÎó");
				request.getRequestDispatcher("changepassword.jsp").forward(request,
						response);			
			}
		}else{
			request.setAttribute("err_msg", "Î´Öª´íÎó");
			request.getRequestDispatcher("changepassword.jsp").forward(request,
					response);		
		}
	}

}
