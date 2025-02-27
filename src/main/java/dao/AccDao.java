package dao;

import java.math.BigDecimal;
import java.sql.*;
import factories.ConnectionFactory;
import model.Acc;

public class AccDao {

    public Acc getByUserId(String userId) {
        String sql = "SELECT * FROM acc WHERE user_user_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, userId.trim());
            System.out.println("Executando consulta para userId: " + userId.trim());  // SOUT para ver o valor do userId
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Acc acc = new Acc();
                acc.setAccId(rs.getString("acc_id"));
                acc.setAgency(rs.getString("agency"));
                acc.setAccNum(rs.getString("acc_num"));
                acc.setBalance(rs.getBigDecimal("balance"));
                acc.setUserId(rs.getString("user_user_id"));
                acc.setBankId(rs.getString("bank_bk_id"));

                // Log para verificar os dados que foram retornados
                System.out.println("Conta encontrada: ");
                System.out.println("acc_id: " + acc.getAccId());
                System.out.println("agency: " + acc.getAgency());
                System.out.println("acc_num: " + acc.getAccNum());
                System.out.println("balance: " + acc.getBalance());
                System.out.println("user_user_id: " + acc.getUserId());
                System.out.println("bank_bk_id: " + acc.getBankId());

                return acc;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar conta bancária para userId: " + userId);
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Acc acc) {
        String sql = "INSERT INTO acc (acc_id, agency, acc_num, balance, user_user_id, bank_bk_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Log para verificar os dados que estão sendo inseridos
            System.out.println("Inserindo conta bancária: ");
            System.out.println("acc_id: " + acc.getAccId());
            System.out.println("agency: " + acc.getAgency());
            System.out.println("acc_num: " + acc.getAccNum());
            System.out.println("balance: " + acc.getBalance());
            System.out.println("user_user_id: " + acc.getUserId());
            System.out.println("bank_bk_id: " + acc.getBankId());

            stmt.setString(1, acc.getAccId());
            stmt.setString(2, acc.getAgency());
            stmt.setString(3, acc.getAccNum());
            stmt.setBigDecimal(4, acc.getBalance());
            stmt.setString(5, acc.getUserId());
            stmt.setString(6, acc.getBankId());
            stmt.executeUpdate();
            System.out.println("Conta bancária inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir conta bancária: " + e.getMessage());
        }
    }

    public void update(Acc acc) {
        String sql = "UPDATE acc SET agency = ?, acc_num = ?, balance = ?, bank_id = ? WHERE acc_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Log para verificar os dados que estão sendo atualizados
            System.out.println("Atualizando conta bancária: ");
            System.out.println("agency: " + acc.getAgency());
            System.out.println("acc_num: " + acc.getAccNum());
            System.out.println("balance: " + acc.getBalance());
            System.out.println("bank_id: " + acc.getBankId());
            System.out.println("acc_id: " + acc.getAccId());

            stmt.setString(1, acc.getAgency());
            stmt.setString(2, acc.getAccNum());
            stmt.setBigDecimal(3, acc.getBalance());
            stmt.setString(4, acc.getBankId());
            stmt.setString(5, acc.getAccId());
            stmt.executeUpdate();
            System.out.println("Conta bancária atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar conta bancária: " + e.getMessage());
        }
    }

    public void delete(String accId) {
        String sql = "DELETE FROM acc WHERE acc_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            System.out.println("Deletando conta bancária com acc_id: " + accId);  // Log para verificar o accId sendo deletado

            stmt.setString(1, accId);
            stmt.executeUpdate();
            System.out.println("Conta bancária deletada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar conta bancária: " + e.getMessage());
        }
    }

    public boolean existsById(String accId) {
        String sql = "SELECT COUNT(*) FROM acc WHERE acc_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, accId);
            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next() && rs.getInt(1) > 0;

            System.out.println("Conta bancária existe com acc_id: " + accId + "? " + exists);  // Log para verificar se a conta existe

            return exists;

        } catch (SQLException e) {
            System.out.println("Erro ao verificar existência de conta: " + e.getMessage());
        }
        return false;
    }

    public BigDecimal getBalanceById(String accId) {
        String sql = "SELECT balance FROM acc WHERE acc_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, accId);
            ResultSet rs = stmt.executeQuery();
            BigDecimal balance = rs.next() ? rs.getBigDecimal("balance") : null;

            System.out.println("Saldo da conta com acc_id " + accId + ": " + balance);  // Log para verificar o saldo retornado

            return balance;

        } catch (SQLException e) {
            System.out.println("Erro ao buscar saldo: " + e.getMessage());
        }
        return null;
    }
}
