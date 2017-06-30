package meetingmanager.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.DepartmentDAO;
import meetingmanager.dao.MeetingDAO;
import meetingmanager.dao.MeetingRoomDAO;
import meetingmanager.vo.Department;
import meetingmanager.vo.Meeting;
import meetingmanager.vo.MeetingRoom;

/**
 * Servlet implementation class BookMeetingServlet
 */
@WebServlet("/BookMeetingServlet")
public class BookMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookMeetingServlet() {
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

		String code = request.getParameter("code");

		if (code != null && code.equals("prepare")) {
			DepartmentDAO deptDao = new DepartmentDAO();

			List<Department> departmentsList = deptDao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			System.out.println(departmentsList);

			MeetingRoomDAO roomDao = new MeetingRoomDAO();

			List<MeetingRoom> meetingRooms = roomDao.selectAll();
			request.setAttribute("roomsList", meetingRooms);
			System.out.println(meetingRooms);
			request.getRequestDispatcher("bookmeeting.jsp").forward(request,
					response);
		} else if (code != null && code.equals("add")) {
			Integer uid = (Integer) request.getSession().getAttribute("uid");
			String strRoomid = request.getParameter("roomid");
			String strName = request.getParameter("meetingname");
			String strBeginTime = request.getParameter("begintime");
			String strEndTime = request.getParameter("endtime");
			String strCapacity = request.getParameter("capacity");
			String description = request.getParameter("description");

			String[] strMembers = request
					.getParameterValues("selSelectedUsers");
			ArrayList<String> arrMembers = new ArrayList<String>();
			for (int i = 0; i < strMembers.length; i++) {
				arrMembers.add(strMembers[i]);
			}

			Meeting meeting = new Meeting();

			Integer roomid = Integer.parseInt(strRoomid);

			meeting.setRoomid(roomid);
			meeting.setReserverid(uid);
			meeting.setName(strName);

			Timestamp begintime = Timestamp.valueOf(strBeginTime.replace('T',
					' ') + ":00");
			meeting.setBegintime(begintime);

			Timestamp endtime = Timestamp.valueOf(strEndTime.replace('T', ' ')
					+ ":00");
			meeting.setEndtime(endtime);

			Timestamp ts = new Timestamp(System.currentTimeMillis());
			meeting.setReservetime(ts);
			meeting.setCanceltime(ts);

			Integer capacity = Integer.parseInt(strCapacity);
			meeting.setCapacity(capacity);

			meeting.setMember(arrMembers.toString());

			meeting.setStatus(0);
			meeting.setDescription(description);

			MeetingDAO dao = new MeetingDAO();

			dao.insert(meeting);
			request.getRequestDispatcher("MyBookingServlet").forward(request,
					response);
		}
	}

}
