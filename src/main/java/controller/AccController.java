package controller;

import dao.BankDao;
import dao.AccDao;
import model.Acc;
import model.Bank;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/acc")
public class AccController extends HttpServlet {

    private final BankDao bankDao = new BankDao();
    private final AccDao accDao = new AccDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userId") == null) {
            response.sendRedirect("");
            return;
        }

        // Verificando se a lista de bancos está sendo recuperada corretamente
        List<Bank> banks = bankDao.getAll();
        System.out.println("Banks list: " + banks); // Imprime o conteúdo da lista

        // Se a lista estiver vazia, adicionar um log de erro
        if (banks == null || banks.isEmpty()) {
            System.out.println("Não foram encontrados bancos na base de dados.");
        }

        request.setAttribute("banks", banks);

        request.getRequestDispatcher("/acc/acc.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String agency = request.getParameter("agency");
        String accNum = request.getParameter("accNum");
        String balanceStr = request.getParameter("balance");
        String bankId = request.getParameter("bankId");

        BigDecimal balance = new BigDecimal(balanceStr);

        Acc acc = new Acc();
        acc.setAccId(java.util.UUID.randomUUID().toString());
        acc.setAgency(agency);
        acc.setAccNum(accNum);
        acc.setBalance(balance);
        acc.setUserId(userId);
        acc.setBankId(bankId);

        accDao.insert(acc);

        response.sendRedirect("home");
    }
}
