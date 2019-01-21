package servlets;

import jdbc.DBService;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        final String id = request.getParameter("id");
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root")) {
            DBService dbService = DBService.getInstance(connection);
            request.setAttribute("user",dbService.getUser(id));
            request.getRequestDispatcher("/WEB-INF/update.jsp")
                    .forward(request, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        final String id = request.getParameter("id");
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root")) {
            DBService dbService = DBService.getInstance(connection);
            dbService.updateUser(id,login,password);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/");
    }
}