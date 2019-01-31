package servlet;

import service.UserService;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class AdminServlet extends HttpServlet {
    List<User> listUsers;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        listUsers = userService.getAllUsers();
        req.setAttribute("users", listUsers);
        req.getRequestDispatcher("/WEB-INF/admin_menu.jsp").forward(req, resp);
    }
}