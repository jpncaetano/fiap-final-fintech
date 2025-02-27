package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Exceptions.IdAlreadyExistsException;
import factories.ConnectionFactory;
import model.User;

public class UserDao {

    public void insert(User user) throws IdAlreadyExistsException {
        if (IdExists(user.getUserId())) {
            throw new IdAlreadyExistsException();
        }

        String sql = "INSERT INTO users (user_id, first_name, last_name, email, password, created_at, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setTimestamp(6, Timestamp.valueOf(user.getCreatedAt()));
            stmt.setString(7, user.getPhone());
            stmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage(), e);
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                user.setPhone(rs.getString("phone"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar usuários: " + e.getMessage());
        }
        return users;
    }

    public boolean IdExists(String userId) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do usuário: " + e.getMessage());
        }
        return false;
    }

    public User getById(String userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getString("user_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    user.setPhone(rs.getString("phone"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    public void update(User user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, phone = ? WHERE user_id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getUserId());
            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void delete(String userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
            System.out.println("Usuário deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public boolean login(String email, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fazer login: " + e.getMessage());
        }
        return false;
    }

    public User getByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getString("user_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    user.setPhone(rs.getString("phone"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    public void signup(User user) throws IdAlreadyExistsException {
        insert(user);
    }
}
