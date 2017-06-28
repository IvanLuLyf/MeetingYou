package meetingmanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.service.MeetingRoomService;
import meetingmanager.vo.MeetingRoom;

/**
 * Servlet implementation class ViewOneMeetingRoomServlet
 */
@WebServlet("/ViewOneMeetingRoomServlet")
public class ViewOneMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOneMeetingRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String roomidString = request.getParameter("roomid");
		if(roomidString!=null){
			Integer roomid = Integer.parseInt(roomidString);
			MeetingRoomService service = new MeetingRoomService();
			MeetingRoom room = service.getRoom(roomid);
			request.setAttribute("room", room);
			request.getRequestDispatcher("roomdetails.jsp").forward(request,
					response);
		}
	}

}
