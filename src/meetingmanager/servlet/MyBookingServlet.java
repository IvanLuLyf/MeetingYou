package meetingmanager.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.MeetingDAO;
import meetingmanager.dao.MeetingRoomDAO;
import meetingmanager.dao.UserDAO;
import meetingmanager.vo.Meeting;
import meetingmanager.vo.MeetingRoom;
import meetingmanager.vo.User;

/**
 * Servlet implementation class MyBookingServlet
 */
@WebServlet("/MyBookingServlet")
public class MyBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyBookingServlet() {
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
		Integer uid = (Integer) request.getSession().getAttribute("uid");
		if (uid != null) {
			MeetingDAO meetingDAO = new MeetingDAO();
			List<Meeting> meetings = meetingDAO.getMeetingByReserverUid(uid);
			request.setAttribute("meetings", meetings);

			MeetingRoomDAO meetingRoomDAO = new MeetingRoomDAO();
			Map<Integer, MeetingRoom> meetingrooms = meetingRoomDAO
					.selectAllMap();
			request.setAttribute("meetingrooms", meetingrooms);

			UserDAO userDAO = new UserDAO();
			Map<Integer, User> users = userDAO.selectAllMap();
			request.setAttribute("users", users);

			request.getRequestDispatcher("mybookings.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}
	}

}
