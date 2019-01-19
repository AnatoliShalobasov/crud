package servlets;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/")
public class StartPageServlet extends HttpServlet {
    List<User> listUsers;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("STARTPAGE***Where is your MySQL JDBC Driver?***STARTPAGE");
            e.printStackTrace();
            return;
        }
        Connection connection;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");

        } catch (SQLException e) {
            System.out.println("STARTPAGE***Connection Failed! Check output console***STARTPAGE");
            e.printStackTrace();
            return;
        }

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listUsers = new CopyOnWriteArrayList<>();
        try {
            ResultSet set = stmt.executeQuery("SELECT * FROM users");
            while (set.next()) {
                listUsers.add(new User(set.getInt("id"),
                        set.getString("user_name"),
                        set.getString("user_login"),
                        set.getString("user_password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", listUsers);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}