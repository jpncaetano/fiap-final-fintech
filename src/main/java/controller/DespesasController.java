package controller;

import dao.AccDao;
import dao.TransactionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Acc;
import model.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/despesas")
public class DespesasController extends HttpServlet {

    private final TransactionDao transactionDao = new TransactionDao();
    private final AccDao accDao = new AccDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            Acc account = accDao.getByUserId(userId);
            if (account != null) {
                if (session.getAttribute("accId") == null) {
                    session.setAttribute("accId", account.getAccId());
                }

                try {
                    List<Transaction> allTransactions = transactionDao.getAllByAccount(account.getAccId());
                    List<Transaction> despesas = allTransactions.stream()
                            .filter(transaction -> "D".equals(transaction.getType()))
                            .collect(Collectors.toList());

                    request.setAttribute("transactions", despesas);
                    request.getRequestDispatcher("/despesas/despesas.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("errorMessage", "Erro ao carregar as transações.");
                    request.getRequestDispatcher("/home/home.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String accId = (String) session.getAttribute("accId");

        if (userId != null && accId != null) {
            String descricao = request.getParameter("descricao");
            String amountStr = request.getParameter("amount");
            String dateStr = request.getParameter("date");
            String type = request.getParameter("type");

            BigDecimal amount = new BigDecimal(amountStr);
            LocalDate date = LocalDate.parse(dateStr);

            Transaction transaction = new Transaction();
            transaction.setTransactionId("TRANS_" + System.currentTimeMillis());
            transaction.setAmount(amount);
            transaction.setDate(date.atStartOfDay());
            transaction.setType(type.substring(0, 1));
            transaction.setStatus("OK");
            transaction.setAccId(accId);
            transaction.setDescription(descricao);

            try {
                transactionDao.insert(transaction);
                response.sendRedirect(request.getContextPath() + "/despesas");
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Erro ao salvar a transação.");
                request.getRequestDispatcher("/despesas/despesas.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
