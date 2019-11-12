package servlet;

import model.User;
import service.UserServiceImpl;
import utils.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/hello")
public class UserHelloServlet extends HttpServlet {
    private UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", ServletUtil.getPersonFromSession(req));
        req.getRequestDispatcher("/WEB-INF/user_menu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        User u = service.getUserByLoginAndPassword(login, password);
        req.setAttribute("user", u);
        req.getRequestDispatcher("/WEB-INF/user_menu.jsp").forward(req, resp);
    }
}