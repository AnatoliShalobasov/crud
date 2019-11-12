package filter;

import model.User;
import utils.ServletUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/user/*")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final User user = ServletUtil.getPersonFromSession(servletRequest);
        if (user == null) {
            servletRequest.getRequestDispatcher("/WEB-INF/error_session.jsp").forward(servletRequest, servletResponse);
        }
        if (user.getRole().equals("user")) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            servletRequest.getRequestDispatcher(request.getRequestURI()).forward(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("/WEB-INF/error.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
