package model;

public class condumidor {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Integer pontos;
    private CarteiraPontos carteira;


    public condumidor(int id, String nome, String cpf, String email, String telefone, Integer pontos, CarteiraPontos carteira) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.pontos = pontos;
        this.carteira = carteira;
        
    }   
}
