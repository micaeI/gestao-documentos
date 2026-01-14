package DAO;

import Util.Conexao;
import Classes.*;
import java.util.ArrayList;
import java.sql.*;

public class DocCategDAO {

    private Connection conn;

    public DocCategDAO() {
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
            String SQL = "SELECT * FROM doc_categoria ORDER BY categ_id";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int categ_id = rs.getInt("categ_id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");

                Gestor.add(new DocCategoria(categ_id, nome, descricao));

            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            Conexao.close(connL, ps);
        }

        return Gestor;
    } //listar

    public void inserir(DocCategoria doc) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (doc == null) {
            System.out.println("O objeto DocCategoria não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO doc_categoria (nome, descricao) "
                    + "values (?, ?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, doc.getNome());
            ps.setString(2, doc.getDescricao());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir uma nova Categoria" + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    } // inserir

    public DocCategoria procurar(int codigo) {
        String SQL = "SELECT nome, descricao FROM doc_categoria WHERE categ_id = ?";
        DocCategoria doc = null;

        try (Connection connL = this.conn; // Use the shared connection
                 PreparedStatement ps = connL.prepareStatement(SQL)) {

            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // `if` because `categ_id` is expected to be unique
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");

                    doc = new DocCategoria(codigo, nome, descricao);
                }
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao procurar categoria: " + sqle.getMessage());
        }

        return doc;
    }   // procurar

    public void excluir(DocCategoria doc) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (doc == null) {
            System.out.println("O objeto DocCategoria não pode ser nulo.");
        }

        try {
            String SQL = "DELETE FROM doc_categoria WHERE categ_id=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, doc.getCateg_id());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir Categoria: " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    } // excluir

    public void atualizar(DocCategoria doc) {
        if (doc == null) {
            System.out.println("O objeto DocCategoria não pode ser nulo.");
            return;
        }

        String SQL = "UPDATE doc_categoria SET nome = ?, descricao = ? WHERE categ_id = ?";

        try (Connection connL = this.conn; PreparedStatement ps = connL.prepareStatement(SQL)) {

            ps.setString(1, doc.getNome());
            ps.setString(2, doc.getDescricao());
            ps.setInt(3, doc.getCateg_id());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao editar Categoria: " + sqle.getMessage());
        }
    }//atualizar

}
