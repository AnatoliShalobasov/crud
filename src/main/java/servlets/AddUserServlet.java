package servlets;

import jdbc.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String name = req.getParameter("name");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        DBService dbService = DBService.getInstance();
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");
        ) {
            dbService.setConnection(con);
            dbService.insertUser(name, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}