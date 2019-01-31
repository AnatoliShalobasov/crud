package filter;

import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();

        final String login = (String) session.getAttribute("login");
        final String password = (String) session.getAttribute("password");

        UserService userService = UserService.getInstance();
        final String role = userService.getUserByLoginAndPassword(login, password).getRole();
        if (role.equals("user")) {
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("/admin/users").forward(req, res);
        }
    }
}