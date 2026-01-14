
package Classes;


public class DocCategoria {
    int categ_id;
    String nome;
    String descricao;

    public DocCategoria() {
    }

    public DocCategoria(int categ_id, String nome, String descricao) {
        this.categ_id = categ_id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public DocCategoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
      
    public int getCateg_id() {
        return categ_id;
    }

    public void setCateg_id(int categ_id) {
        this.categ_id = categ_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
