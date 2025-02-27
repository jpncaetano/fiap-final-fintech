package controller;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import Exceptions.IdAlreadyExistsException;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/signup")
public class SignupController extends HttpServlet {

    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setUserId(java.util.UUID.randomUUID().toString());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());
        user.setPhone(phone);

        try {
            userDao.signup(user);
            request.getSession().setAttribute("userId", user.getUserId());
            response.sendRedirect("acc");
        } catch (IdAlreadyExistsException e) {
            request.setAttribute("errorMessage", "Usuário já existe com esse ID.");
            request.getRequestDispatcher("/signup/signup.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro ao cadastrar usuário: " + e.getMessage());
            request.getRequestDispatcher("/signup/signup.jsp").forward(request, response);
        }
    }
}
