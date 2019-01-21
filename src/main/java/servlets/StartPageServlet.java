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
import java.util.List;

@WebServlet("/")
public class StartPageServlet extends HttpServlet {
    List<User> listUsers;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBService dbService = DBService.getInstance();
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");
        ) {
            dbService.setConnection(connection);
            dbService.createTable();
            listUsers = dbService.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", listUsers);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}