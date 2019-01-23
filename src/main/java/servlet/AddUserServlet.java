package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String name = req.getParameter("name");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        UserService userService = UserService.getInstance();
        userService.insertUser(name, login, password);
        resp.sendRedirect("/");
    }
}