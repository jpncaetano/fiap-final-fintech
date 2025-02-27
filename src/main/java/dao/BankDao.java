package dao;

import Exceptions.IdAlreadyExistsException;
import model.Bank;
import factories.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDao {

    public void insert(Bank bank) throws IdAlreadyExistsException {
        if (existsById(bank.getBkId())) {
            throw new IdAlreadyExistsException();
        }

        String sql = "INSERT INTO bank (bk_id, name) VALUES (?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, bank.getBkId());
            stmt.setString(2, bank.getName());
            stmt.executeUpdate();
            System.out.println("Banco inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir banco: " + e.getMessage());
        }
    }

    public boolean existsById(String bankId) {
        String sql = "SELECT COUNT(*) FROM bank WHERE bk_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, bankId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do banco: " + e.getMessage());
        }
        return false;
    }

    // Método para retornar todos os bancos
    public List<Bank> getAll() {
        List<Bank> banks = new ArrayList<>();
        String sql = "SELECT * FROM bank";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bank bank = new Bank();
                bank.setBkId(rs.getString("bk_id"));
                bank.setName(rs.getString("name"));
                banks.add(bank);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao recuperar bancos: " + e.getMessage());
        }
        return banks;
    }
}
