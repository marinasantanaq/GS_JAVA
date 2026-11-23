package model;

public class Restaurante {
    private int id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;

    public Restaurante(int id, String nome, String cnpj, String endereco, String telefone) {
        setId(id);
        setNome(nome);
        setCnpj(cnpj);
        setEndereco(endereco);
        setTelefone(telefone);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCnpj() { return cnpj; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("O ID deve ser positivo.");
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("O nome é obrigatório.");
        this.nome = nome.trim();
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.isBlank()) throw new IllegalArgumentException("O CNPJ é obrigatório.");
        this.cnpj = cnpj.trim();
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.isBlank()) throw new IllegalArgumentException("O endereço é obrigatório.");
        this.endereco = endereco.trim();
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) throw new IllegalArgumentException("O telefone é obrigatório.");
        this.telefone = telefone.trim();
    }

    @Override
    public String toString() {
        return nome + " | " + endereco;
    }
}
