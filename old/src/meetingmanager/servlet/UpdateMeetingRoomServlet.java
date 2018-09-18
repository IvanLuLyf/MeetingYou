package meetingmanager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.*;
import meetingmanager.service.MeetingRoomService;
import meetingmanager.vo.MeetingRoom;

/**
 * Servlet implementation class UpdateMeetingRoomServlet
 */
@WebServlet("/UpdateMeetingRoomServlet")
public class UpdateMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMeetingRoomServlet() {
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
		Integer roomid = Integer.parseInt(request.getParameter("roomid"));
		Integer number = Integer.parseInt(request.getParameter("roomnumber"));
		String name = request.getParameter("roomname");
		Integer capacity = Integer.parseInt(request.getParameter("roomcapacity"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		String description = request.getParameter("description");
		MeetingRoomService service = new MeetingRoomService();
		MeetingRoom room = new MeetingRoom(roomid, number, name, capacity, status, description);
		service.updateRoom(room);
		MeetingRoomDAO dao = new MeetingRoomDAO();
		List<MeetingRoom> meetingRooms = dao.selectAll();
		request.setAttribute("roomsList", meetingRooms);
		
		request.getRequestDispatcher("meetingrooms.jsp")
				.forward(request, response);
	}

}
