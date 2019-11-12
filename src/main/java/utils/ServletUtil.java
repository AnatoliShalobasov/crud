package utils;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

public class ServletUtil {

    public static User getPersonFromSession(ServletRequest request) {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpSession session = req.getSession();
        UserServiceImpl service = UserServiceImpl.getInstance();
        final String login = (String) session.getAttribute("login");
        final String password = (String) session.getAttribute("password");

        return service.getUserByLoginAndPassword(login, password);
    }

    public static void insertUser(ServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        final String name = req.getParameter("name");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String role = req.getParameter("role");
        UserServiceImpl service = UserServiceImpl.getInstance();
        service.insertUser(name, login, password, role);
    }
}