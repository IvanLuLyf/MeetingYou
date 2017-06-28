package meetingmanager.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import meetingmanager.service.UserService;
import meetingmanager.vo.User;

/**
 * Servlet Filter implementation class LoginedFilter
 */
@WebFilter("/LoginedFilter")
public class LoginedFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginedFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//UserService service = new UserService();
		User user = (User)httpServletRequest.getSession().getAttribute("user");
		if (user == null) {
			httpServletRequest.setAttribute("msg", "ÇëÏÈµÇÂ¼¡£");
			httpServletRequest.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}
		System.out.println(getClass().getName() +":" + user);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
