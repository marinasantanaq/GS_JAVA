package model;

public class Alimento {
    // Atributos
    private int id;
    private String nome;
    private String descricao;
    private double precoOriginal;
    private double precoDesconto;
    private int quantidade;
    private String validade;
    private Restaurante restaurante;

    // Construtor
    public Alimento(int id, String nome, String descricao,
                    double precoOriginal, double precoDesconto,
                    int quantidade, String validade,
                    Restaurante restaurante) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoOriginal = precoOriginal;
        this.precoDesconto = precoDesconto;
        this.quantidade = quantidade;
        this.validade = validade;
        this.restaurante = restaurante;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoOriginal() {
        return precoOriginal;
    }

    public double getPrecoDesconto() {
        return precoDesconto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getValidade() {
        return validade;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }
}