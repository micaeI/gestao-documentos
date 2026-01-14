
package Classes;

import java.sql.Timestamp;

public class Users {
    int user_id;
    String nome;
    String email;
    String senha;
    String cargo;
    Timestamp data_criacao;

    public Users(int user_id, String nome, String email, String senha, String cargo, Timestamp data_criacao) {
        this.user_id = user_id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.data_criacao = data_criacao;
    }

    public Users(String nome, String email, String senha, String cargo, Timestamp data_criacao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.data_criacao = data_criacao;
    }

    
    public Users() {
    }
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Timestamp getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Timestamp data_criacao) {
        this.data_criacao = data_criacao;
    }
    
}
