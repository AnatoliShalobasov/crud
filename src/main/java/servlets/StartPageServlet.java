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
        DBService dbService = null;
        try {
            dbService = DBService.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            dbService.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listUsers = dbService.getAllUsers();
        req.setAttribute("users", listUsers);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}