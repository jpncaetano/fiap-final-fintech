package controller;

import dao.AccDao;
import dao.TransactionDao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Acc;
import model.Transaction;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private final AccDao accDao = new AccDao();
    private final TransactionDao transactionDao = new TransactionDao();
    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            User user = userDao.getById(userId);

            if (user != null) {
                Acc account = accDao.getByUserId(userId);

                if (account != null) {
                    request.setAttribute("balance", account.getBalance());
                    request.setAttribute("user", user);
                    List<Transaction> transacoes = transactionDao.getAllByAccount(account.getAccId());
                    request.setAttribute("transacoes", transacoes);
                    request.getRequestDispatcher("/home/home.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Conta não encontrada.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Erro ao carregar os dados do usuário.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }
}
