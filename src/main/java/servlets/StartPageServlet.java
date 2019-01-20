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
    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("STARTPAGE***Where is your MySQL JDBC Driver?***STARTPAGE");
            e.printStackTrace();
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")
        ) {
            listUsers = new CopyOnWriteArrayList<>();
            while (resultSet.next()) {
                listUsers.add(new User(resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("user_login"),
                        resultSet.getString("user_password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", listUsers);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}