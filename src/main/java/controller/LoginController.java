package controller;

import dao.UserDao;
import dao.AccDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.Acc;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private final UserDao userDao = new UserDao();
    private final AccDao accDao = new AccDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean loginSuccessful = userDao.login(email, password);

        if (loginSuccessful) {
            User user = userDao.getByEmail(email);

            if (user != null) {
                Acc acc = accDao.getByUserId(user.getUserId());

                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getUserId());

                if (acc != null) {
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    response.sendRedirect(request.getContextPath() + "/acc");
                }
            } else {
                request.setAttribute("errorMessage", "Erro ao carregar os dados do usuário.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Email ou senha inválidos.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
