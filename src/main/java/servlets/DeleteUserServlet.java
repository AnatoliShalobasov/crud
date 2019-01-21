package servlets;

import jdbc.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        DBService dbService = null;
        try {
            dbService = DBService.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final String id = req.getParameter("id");

        try {
            dbService.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}