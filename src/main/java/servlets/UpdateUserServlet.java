package servlets;

import jdbc.DBService;

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
        DBService dbService = null;
        try {
            dbService = DBService.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("user", dbService.getUser(id));
        request.getRequestDispatcher("/WEB-INF/update.jsp")
                .forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        final String id = request.getParameter("id");
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        DBService dbService = null;
        try {
            dbService = DBService.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbService.updateUser(id, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}