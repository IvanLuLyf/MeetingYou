package meetingmanager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.vo.*;
import meetingmanager.dao.*;
import meetingmanager.service.*;

/**
 * Servlet implementation class AddMeetingRoomServlet
 */
@WebServlet("/AddMeetingRoomServlet")
public class AddMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMeetingRoomServlet() {
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
		Integer number = Integer.parseInt(request.getParameter("roomnumber"));
		String name = request.getParameter("roomname");
		Integer capacity = Integer.parseInt(request.getParameter("roomcapacity"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		String description = request.getParameter("description");
		
		MeetingRoom meetingRoom = new MeetingRoom(number,name,capacity,status,description);
		MeetingRoomService service = new MeetingRoomService();
		
		int flag = service.addRoom(meetingRoom);
		
		if (flag == 1) {
			MeetingRoomDAO dao = new MeetingRoomDAO();
			List<MeetingRoom> meetingRooms = dao.selectAll();
			request.setAttribute("roomsList", meetingRooms);
			
			request.getRequestDispatcher("meetingrooms.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("msg", "门牌号已存在，请重新填写。");
			request.getRequestDispatcher("addmeetingroom.jsp").forward(request,
					response);
		}
	}

}
