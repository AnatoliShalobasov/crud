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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/")
public class StartPageServlet extends HttpServlet {
    List<User> listUsers;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");
        ) {
            DBService dbService = DBService.getInstance(connection);
            dbService.createTable();
            listUsers = dbService.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", listUsers);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}