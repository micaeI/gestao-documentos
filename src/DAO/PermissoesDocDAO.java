package DAO;

import Util.Conexao;
import Classes.*;
import java.util.ArrayList;
import java.sql.*;

public class PermissoesDocDAO {

    private Connection conn;

    public PermissoesDocDAO() {
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
            String SQL = "SELECT * FROM permissoes_doc ORDER BY perm_id";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int perm_id = rs.getInt("perm_id");
                int doc_id = rs.getInt("doc_id");
                int user_id = rs.getInt("user_id");
                String permission_type = rs.getString("permission_type");
                Timestamp data_criacao_perm = rs.getTimestamp("data_criacao_perm");

                Gestor.add(new PermissoesDoc(perm_id, doc_id, user_id, permission_type, data_criacao_perm));

            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            Conexao.close(connL, ps);
        }

        return Gestor;
    } //listar

    public void inserir(PermissoesDoc doc) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (doc == null) {
            System.out.println("O objeto PermissoesDoc não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO permissoes_doc (doc_id, user_id, permission_type, data_criacao_perm) "
                    + "values (?,?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, doc.getDoc_id());
            ps.setInt(2, doc.getUser_id());
            ps.setString(3, doc.getPermission_type());
            ps.setTimestamp(4, doc.getData_criacao_perm());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir novas Permissoes : " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    } // inserir

    public PermissoesDoc procurar(int codigo) {
        String SQL = "SELECT doc_id, user_id, permission_type, data_criacao_perm FROM permissoes_doc WHERE perm_id = ?";
        PermissoesDoc doc = null;

        try (Connection connL = this.conn; PreparedStatement ps = connL.prepareStatement(SQL)) {
            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int doc_id = rs.getInt("doc_id");
                    int user_id = rs.getInt("user_id");
                    String permission_type = rs.getString("permission_type");
                    Timestamp data_criacao_perm = rs.getTimestamp("data_criacao_perm");

                    doc = new PermissoesDoc(codigo, doc_id, user_id, permission_type, data_criacao_perm);
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Erro ao procurar permissoes: " + sqle.getMessage());
        }
        return doc;
    }   // procurar

    public void excluir(PermissoesDoc doc) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (doc == null) {
            System.out.println("O objeto PermissoesDoc não pode ser nulo.");
        }

        try {
            String SQL = "DELETE FROM permissoes_doc WHERE perm_id=?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, doc.getPerm_id());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir permissoes: " + sqle);
        } finally {
            Conexao.close(connL, ps);
        }
    } // excluir

    public void atualizar(PermissoesDoc doc) {
        if (doc == null) {
            System.out.println("O objeto PermissoesDoc não pode ser nulo.");
            return;
        }

        String SQL = "UPDATE permissoes_doc "
                + "SET doc_id = ?, user_id = ?, permission_type = ?, data_criacao_perm = ? "
                + "WHERE perm_id = ?";

        try (Connection connL = this.conn; PreparedStatement ps = connL.prepareStatement(SQL)) {

            ps.setInt(1, doc.getDoc_id());
            ps.setInt(2, doc.getUser_id());
            ps.setString(3, doc.getPermission_type());
            ps.setTimestamp(4, doc.getData_criacao_perm());
            ps.setInt(5, doc.getPerm_id());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao editar permissoes: " + sqle.getMessage());
        }
    }//atualizar

}
