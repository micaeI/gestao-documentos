package DAO;

import Util.Conexao;
import Classes.*;
import java.util.ArrayList;
import java.sql.*;

public class UsersDAO {

    private Connection conn;

    public UsersDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());;
        }
    }

    public ArrayList listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList Gestor = new ArrayList();

        try {
            String SQL = "SELECT * FROM users ORDER BY user_id";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String cargo = rs.getString("cargo");
                Timestamp data_criacao = rs.getTimestamp("data_criacao");

                Gestor.add(new Users(user_id, nome, email, senha, cargo, data_criacao));

            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            Conexao.close(connL, ps);
        }

        return Gestor;
    } //listar

    public void inserir(Users doc) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (doc == null) {
            System.out.println("O objeto Users não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO users (nome, email, senha, cargo) "
                    + "values (?,?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, doc.getNome());
            ps.setString(2, doc.getEmail());
            ps.setString(3, doc.getSenha());
            ps.setString(4, doc.getCargo());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir um novo usuario: " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    } // inserir

    public Users procurar(int codigo) {
        String SQL = "SELECT nome, email, senha, cargo, data_criacao FROM users WHERE user_id = ?";
        Users doc = null;

        try (Connection connL = this.conn; PreparedStatement ps = connL.prepareStatement(SQL)) {
            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String senha = rs.getString("senha");
                    String cargo = rs.getString("cargo");
                    Timestamp data_criacao = rs.getTimestamp("data_criacao");

                    doc = new Users(codigo, nome, email, senha, cargo, data_criacao);
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Erro ao procurar usuarios: " + sqle.getMessage());
        }
        return doc;

    }   // procurar

    public void excluir(Users doc) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (doc == null) {
            System.out.println("O objeto Users não pode ser nulo.");
        }

        try {
            String SQL = "DELETE FROM users WHERE user_id=?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, doc.getUser_id());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir usuarios: " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    } // excluir

    public void atualizar(Users doc) {
        if (doc == null) {
            System.out.println("O objeto Users não pode ser nulo.");
            return;
        }

        String SQL = "UPDATE users "
                + "SET nome = ?, email = ?, senha = ?, cargo = ?, data_criacao = ? "
                + "WHERE user_id = ?";

        try (Connection connL = this.conn; PreparedStatement ps = connL.prepareStatement(SQL)) {

            ps.setString(1, doc.getNome());
            ps.setString(2, doc.getEmail());
            ps.setString(3, doc.getSenha());
            ps.setString(4, doc.getCargo());
            ps.setTimestamp(5, doc.getData_criacao());
            ps.setInt(6, doc.getUser_id());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao editar usuarios: " + sqle.getMessage());
        }
    }//atualizar

}
