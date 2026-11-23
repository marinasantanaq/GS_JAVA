package model;

public final class Consumidor {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Consumidor(int id, String nome, String cpf, String email, String telefone) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setTelefone(telefone);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("O ID deve ser positivo.");
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("O nome é obrigatório.");
        this.nome = nome.trim();
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("O CPF é obrigatório.");
        this.cpf = cpf.trim();
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }
        this.email = email.trim();
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) throw new IllegalArgumentException("O telefone é obrigatório.");
        this.telefone = telefone.trim();
    }
}
