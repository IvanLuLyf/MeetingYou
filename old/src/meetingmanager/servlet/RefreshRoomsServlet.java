package meetingmanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingmanager.dao.*;
import meetingmanager.vo.*;

/**
 * Servlet implementation class RefreshRoomsServlet
 */
@WebServlet("/RefreshRoomsServlet")
public class RefreshRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshRoomsServlet() {
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
		String strBeginTime = request.getParameter("begintime");
		String strEndTime = request.getParameter("endtime");
		
		if(strBeginTime!=null &&strEndTime!=null){
			String beginTime = strBeginTime.replace('T', ' ');
			String endTime = strEndTime.replace('T', ' ');
			
			System.out.println(beginTime);
			
			MeetingRoomDAO dao = new MeetingRoomDAO();
			List<MeetingRoom> rooms = dao.selectBetween(beginTime, endTime);
			
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/xml;charset=utf-8");
			
			PrintWriter out = response.getWriter();
			String strResult = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<xml>";
			
			for(int i=0;i<rooms.size();i++){
				strResult+="<option>\n<text>" + rooms.get(i).getName() +"</text>\n<value>" + rooms.get(i).getRoomid() + "</value>\n</option>\n";
			}
			strResult+= "\n</xml>";
			out.print(strResult); 
		}
	}

}
