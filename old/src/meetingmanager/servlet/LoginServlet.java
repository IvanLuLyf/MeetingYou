package meetingmanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import meetingmanager.service.UserService;
import meetingmanager.util.PasswordEncoder;
import meetingmanager.vo.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		
		String timelength = request.getParameter("timelength");
		
		if(password!=null){
			password = PasswordEncoder.Encode(password);
		}else{
			password = token;
		}
		
		System.out.println("user:" + username);
		System.out.println("pass:" + password);
		
		int days = 0;
		if (timelength != null) {
			days = Integer.parseInt(timelength);
		}

		UserService service = new UserService();
		int flag = service.login(username, password);
		System.out.println(this.getClass().getName() + " flag:" + flag);
		if (flag == 1) {
			HttpSession session = request.getSession();

			User user = service.getLoginedUser();

			if (days != 0) {
				Cookie usernamecookie = new Cookie("username", username);
				Cookie passwordcookie = new Cookie("password", password);
				usernamecookie.setMaxAge(days * 24 * 3600);
				passwordcookie.setMaxAge(days * 24 * 3600);
				response.addCookie(usernamecookie);
				response.addCookie(passwordcookie);
			}
			
			session.setAttribute("uid", user.getUid());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("roleid", user.getRoleId());
			session.setAttribute("name", user.getName());

			session.setAttribute("user", user);
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} else {
			if (flag == 0) {
				request.setAttribute("msg", "正在审核，请耐心等待。");
			} else if (flag == 2) {
				request.setAttribute("msg", "审核未通过，请核实后重新注册。");
			} else if (flag == 3) {
				request.setAttribute("msg", "用户名或密码错误，请重试。");
			}
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}
	}

}
