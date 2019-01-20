package servlets;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getParameter("id");
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");
             Statement statement = con.createStatement();
             ResultSet userResultSet = statement.executeQuery("SELECT * FROM users WHERE id = '" + Integer.valueOf(id) + "'")) {
            User user = null;
            while (userResultSet.next()) {
                user = new User(userResultSet.getInt("id"),
                        userResultSet.getString("user_name"),
                        userResultSet.getString("user_login"),
                        userResultSet.getString("user_password"));
            }
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/update.jsp")
                    .forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        final String id = req.getParameter("id");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");
             Statement statement = con.createStatement()) {
            statement.execute("UPDATE users SET user_login = '" + login + "',user_password = '" + password + "' WHERE id = '" + Integer.valueOf(id) + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}