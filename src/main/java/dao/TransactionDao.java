package dao;

import model.Transaction;
import factories.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    public void insert(Transaction transaction) {
        String sql = "INSERT INTO transactions (transaction_id, \"date\", amount, type, status, acc_acc_id, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, transaction.getTransactionId());
            stmt.setDate(2, Date.valueOf(transaction.getDate().toLocalDate()));
            stmt.setBigDecimal(3, transaction.getAmount());
            stmt.setString(4, transaction.getType());
            stmt.setString(5, transaction.getStatus());
            stmt.setString(6, transaction.getAccId());
            stmt.setString(7, transaction.getDescription());

            stmt.executeUpdate();
            System.out.println("Transação inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir transação: " + e.getMessage());
        }
    }

    public List<Transaction> getAllByAccount(String accId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE acc_acc_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, accId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getString("transaction_id"));
                transaction.setDate(rs.getDate("date").toLocalDate().atStartOfDay());
                transaction.setAmount(rs.getBigDecimal("amount"));
                transaction.setType(rs.getString("type"));
                transaction.setStatus(rs.getString("status"));
                transaction.setAccId(rs.getString("acc_acc_id"));
                transaction.setDescription(rs.getString("description"));
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao recuperar transações da conta: " + e.getMessage());
        }

        return transactions;
    }
}
