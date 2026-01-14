
package Classes;

import java.sql.Timestamp;

public class Documentos {
    int doc_id;
    int user_id;
    int categ_id;
    String titulo;
    String File_path;
    Timestamp data_criacao_doc;
    Timestamp data_atualizacao_doc;
    String status;

    public Documentos(int doc_id, int user_id, int categ_id, String titulo, String File_path, Timestamp data_criacao_doc, Timestamp data_atualizacao_doc, String status) {
        this.doc_id = doc_id;
        this.user_id = user_id;
        this.categ_id = categ_id;
        this.titulo = titulo;
        this.File_path = File_path;
        this.data_criacao_doc = data_criacao_doc;
        this.data_atualizacao_doc = data_atualizacao_doc;
        this.status = status;
    }

    public Documentos(int user_id, int categ_id, String titulo, String File_path, Timestamp data_criacao_doc, Timestamp data_atualizacao_doc, String status) {
        this.user_id = user_id;
        this.categ_id = categ_id;
        this.titulo = titulo;
        this.File_path = File_path;
        this.data_criacao_doc = data_criacao_doc;
        this.data_atualizacao_doc = data_atualizacao_doc;
        this.status = status;
    }

    public Documentos() {
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

    public int getCateg_id() {
        return categ_id;
    }

    public void setCateg_id(int categ_id) {
        this.categ_id = categ_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFile_path() {
        return File_path;
    }

    public void setFile_path(String File_path) {
        this.File_path = File_path;
    }

    public Timestamp getData_criacao_doc() {
        return data_criacao_doc;
    }

    public void setData_criacao_doc(Timestamp data_criacao_doc) {
        this.data_criacao_doc = data_criacao_doc;
    }

    public Timestamp getData_atualizacao_doc() {
        return data_atualizacao_doc;
    }

    public void setData_atualizacao_doc(Timestamp data_atualizacao_doc) {
        this.data_atualizacao_doc = data_atualizacao_doc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
