package meetingmanager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.*;
import meetingmanager.vo.*;

/**
 * Servlet implementation class ViewAllRoomsServlet
 */
@WebServlet("/ViewAllRoomsServlet")
public class ViewAllRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllRoomsServlet() {
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
		
		MeetingRoomDAO dao = new MeetingRoomDAO();
		
		List<MeetingRoom> meetingRooms = dao.selectAll();
		request.setAttribute("roomsList", meetingRooms);
		System.out.println(meetingRooms);
		String code = request.getParameter("code");

		if (code != null && code.equals("viewall")) {
			request.getRequestDispatcher("meetingrooms.jsp").forward(request,
					response);
		}
	}

}
