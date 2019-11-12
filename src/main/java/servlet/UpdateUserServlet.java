package servlet;

import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/update")
public class UpdateUserServlet extends HttpServlet {
    private UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        final String id = request.getParameter("id");
        request.setAttribute("user", service.getUser(id));
        request.getRequestDispatcher("/WEB-INF/update.jsp")
                .forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        final String id = request.getParameter("id");
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final String role = request.getParameter("role");

        service.updateUser(id, login, password, role);
        response.sendRedirect("/admin/users");
    }
}