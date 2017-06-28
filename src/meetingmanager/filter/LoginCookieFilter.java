package meetingmanager.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoginCookieFilter
 */
@WebFilter("/LoginCookieFilter")
public class LoginCookieFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCookieFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		String username = null;
		String password = null;
		
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					username = cookie.getValue();
				}

				if (cookie.getName().equals("password")) {
					password = cookie.getValue();
				}
			}
		}
		System.out.println(this.getClass().getName() + ":RUN");
		if (username == null || password == null) {
			chain.doFilter(request, response);
		} else {
			System.out.println(this.getClass().getName() + ":user=" + username );
			httpServletRequest.getRequestDispatcher("LoginServlet?username=" + username + "&password=" + password).forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
