package meetingmanager.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.*;
import meetingmanager.vo.*;

/**
 * Servlet implementation class MyMeetingServlet
 */
@WebServlet("/MyMeetingServlet")
public class MyMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMeetingServlet() {
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
		
		Integer uid = (Integer)request.getSession().getAttribute("uid");
		if(uid!=null){
			MeetingDAO meetingDAO = new MeetingDAO();
			MeetingRoomDAO meetingRoomDAO = new MeetingRoomDAO();
			UserDAO userDAO = new UserDAO();
			
			String code = request.getParameter("code");
			
			if(code==null || code.equals("")){
				List<Meeting> meetings = meetingDAO.getMeetingByMemberUid(uid);
				request.setAttribute("meetings", meetings);
				
				
				Map<Integer, MeetingRoom> meetingrooms = meetingRoomDAO.selectAllMap();
				request.setAttribute("meetingrooms", meetingrooms);
	
				
				Map<Integer, User> users = userDAO.selectAllMap();
				request.setAttribute("users", users);
				
				request.getRequestDispatcher("mymeetings.jsp").forward(request,
						response);				
			}else if(code.equals("detail")){
				String strMeetingid = request.getParameter("mid");
				if(strMeetingid!=null){
					Integer meetingid = Integer.parseInt(strMeetingid);
					Meeting meeting = meetingDAO.getMeetingById(meetingid);	
					request.setAttribute("meeting", meeting);
					
					Map<Integer, MeetingRoom> meetingrooms = meetingRoomDAO.selectAllMap();
					request.setAttribute("meetingrooms", meetingrooms);
		
					Map<Integer, User> users = userDAO.selectAllMap();
					request.setAttribute("users", users);
					
					String[] strMember = meeting.getMember().replace("[", "").replace("]", "").split(",");
					ArrayList<Integer> members = new ArrayList<Integer>();
					for(int i=0;i<strMember.length;i++){
						members.add(Integer.parseInt(strMember[i].trim()));
					}
					request.setAttribute("members", members);
					
					if(meeting.getReserverid().equals(uid)){
						request.setAttribute("isMine", "1");
					}
					
					request.getRequestDispatcher("meetingdetails.jsp").forward(request,
							response);						
				}
			}
		}else{
			request.getRequestDispatcher("login.jsp").forward(request,
					response);
		}
	}

}
