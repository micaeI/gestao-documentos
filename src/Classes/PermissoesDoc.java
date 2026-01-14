
package Classes;


import java.sql.Timestamp;

public class PermissoesDoc {
    int perm_id;
    int doc_id;
    int user_id;
    String permission_type;
    Timestamp data_criacao_perm;

    public PermissoesDoc(int perm_id, int doc_id, int user_id, String permission_type, Timestamp data_criacao_perm) {
        this.perm_id = perm_id;
        this.doc_id = doc_id;
        this.user_id = user_id;
        this.permission_type = permission_type;
        this.data_criacao_perm = data_criacao_perm;
    }

    public PermissoesDoc(int doc_id, int user_id, String permission_type, Timestamp data_criacao_perm) {
        this.doc_id = doc_id;
        this.user_id = user_id;
        this.permission_type = permission_type;
        this.data_criacao_perm = data_criacao_perm;
    }

    public PermissoesDoc() {
    }
    
    public int getPerm_id() {
        return perm_id;
    }

    public void setPerm_id(int perm_id) {
        this.perm_id = perm_id;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(String permission_type) {
        this.permission_type = permission_type;
    }

    public Timestamp getData_criacao_perm() {
        return data_criacao_perm;
    }

    public void setData_criacao_perm(Timestamp data_criacao_perm) {
        this.data_criacao_perm = data_criacao_perm;
    }

    
}
