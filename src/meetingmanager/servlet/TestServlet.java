package meetingmanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
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
		
		Map<String, String> params = new HashMap<String, String>();
		
		Enumeration<String> paramEnumeration = request.getParameterNames();
		while (paramEnumeration.hasMoreElements()) {
			String key = (String) paramEnumeration.nextElement();
			String[] values = request.getParameterValues(key);
			if(values!=null){
				ArrayList<String> arrayList = new ArrayList<String>();
				for(int i=0;i<values.length;i++){
					arrayList.add(values[i]);
				}
				params.put(key, arrayList.toString());	
			}else{
				String value = request.getParameter(key);
				params.put(key, value);	
			}
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(params);
		System.out.println(params);
	}

}
