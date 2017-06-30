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
 * Servlet implementation class ViewUserByDeptidServlet
 */
@WebServlet("/ViewUserByDeptidServlet")
public class ViewUserByDeptidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUserByDeptidServlet() {
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
		String strDeptid = request.getParameter("deptid");
		
		if(strDeptid!=null){
			UserDAO dao = new UserDAO();
			Integer deptid = Integer.parseInt(strDeptid);
			List<User> users = dao.selectUserByDeptid(deptid);
			
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/xml;charset=utf-8");
			
			PrintWriter out = response.getWriter();
			String strResult = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<xml>";
			
			for(int i=0;i<users.size();i++){
				strResult+="<option>\n<text>" + users.get(i).getName() +"</text>\n<value>" + users.get(i).getUid() + "</value>\n</option>\n";
			}
			strResult+= "\n</xml>";
			out.print(strResult); 
		}
	}

}
