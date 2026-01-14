package DAO;

import Util.Conexao;
import Classes.*;
import java.util.ArrayList;
import java.sql.*;

public class DocDAO {

    private Connection conn;

    public DocDAO() {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());;
        }
    } // DocDAO

    public ArrayList listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList Gestor = new ArrayList();

        try {
            String SQL = "SELECT * FROM documentos ORDER BY doc_id";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int doc_id = rs.getInt("doc_id");
                int user_id = rs.getInt("user_id");
                int categ_id = rs.getInt("categ_id");
                String titulo = rs.getString("titulo");
                String file_path = rs.getString("file_path");
                Timestamp data_criacao_doc = rs.getTimestamp("data_criacao_doc");
                Timestamp data_atualizacao_doc = rs.getTimestamp("data_atualizacao_doc");
                String status = rs.getString("status");

                Gestor.add(new Documentos(doc_id, user_id, categ_id, titulo, file_path, data_criacao_doc, data_atualizacao_doc, status));

            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            Conexao.close(connL, ps);
        }

        return Gestor;
    } //listar

    public void inserir(Documentos doc) {
        if (doc == null) {
            System.out.println("O objeto Documentos não pode ser nulo.");
            return;
        }

        String SQL = "INSERT INTO documentos (user_id, categ_id, file_path, data_criacao_doc, data_atualizacao_doc, status, titulo) "
                + "VALUES (?,?,?,?,?,?,?)";

        try (Connection connL = this.conn; PreparedStatement ps = connL.prepareStatement(SQL)) {

            if (connL == null || connL.isClosed()) {
                throw new SQLException("A conexão com o banco de dados não está disponível.");
            }

            ps.setInt(1, doc.getUser_id());
            ps.setInt(2, doc.getCateg_id());
            ps.setString(3, doc.getFile_path());
            ps.setTimestamp(4, doc.getData_criacao_doc());
            ps.setTimestamp(5, doc.getData_atualizacao_doc());
            ps.setString(6, doc.getStatus());
            ps.setString(7, doc.getTitulo());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir um novo Documento: " + sqle.getMessage());
        }
    }//inserir

    public Documentos procurar(int codigo) {
        String SQL = "SELECT user_id, categ_id, file_path, data_criacao_doc, data_atualizacao_doc, status, titulo FROM documentos WHERE doc_id = ?";
        Documentos doc = null;

        try (Connection connL = this.conn; PreparedStatement ps = connL.prepareStatement(SQL)) {
            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    int categ_id = rs.getInt("categ_id");
                    String titulo = rs.getString("titulo");
                    String file_path = rs.getString("file_path");
                    Timestamp data_criacao_doc = rs.getTimestamp("data_criacao_doc");
                    Timestamp data_atualizacao_doc = rs.getTimestamp("data_atualizacao_doc");
                    String status = rs.getString("status");

                    doc = new Documentos(codigo, user_id, categ_id, titulo, file_path, data_criacao_doc, data_atualizacao_doc, status);
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Erro ao procurar documento: " + sqle.getMessage());
        }
        return doc;

    }   // procurar

    public void excluir(Documentos doc) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (doc == null) {
            System.out.println("O objeto documento não pode ser nulo.");
        }

        try {
            String SQL = "DELETE FROM documentos WHERE doc_id=?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, doc.getDoc_id());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir documento: " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    } // excluir

    public void atualizar(Documentos doc) {
        if (doc == null) {
            System.out.println("O objeto Documentos não pode ser nulo.");
            return;
        }

        String SQL = "UPDATE documentos "
                + "SET user_id = ?, categ_id = ?, file_path = ?, data_criacao_doc = ?, "
                + "data_atualizacao_doc = ?, status = ?, titulo = ? "
                + "WHERE doc_id = ?";

        try (Connection connL = this.conn; PreparedStatement ps = connL.prepareStatement(SQL)) {

            ps.setInt(1, doc.getUser_id());
            ps.setInt(2, doc.getCateg_id());
            ps.setString(3, doc.getFile_path());
            ps.setTimestamp(4, doc.getData_criacao_doc());
            ps.setTimestamp(5, doc.getData_atualizacao_doc());
            ps.setString(6, doc.getStatus());
            ps.setString(7, doc.getTitulo());
            ps.setInt(8, doc.getDoc_id());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao editar documento: " + sqle.getMessage());
        }
    }// atualziar

}
