package model;

import java.time.LocalDateTime;

public final class Doacao {
    private int id;
    private Restaurante restaurante;
    private Instituicao instituicao;
    private Alimento alimento;
    private int quantidade;
    private String status;
    private LocalDateTime dataHora;

    public Doacao(int id, Restaurante restaurante, Instituicao instituicao,
                  Alimento alimento, int quantidade) {
        setId(id);
        setRestaurante(restaurante);
        setInstituicao(instituicao);
        setAlimento(alimento);
        setQuantidade(quantidade);
        this.status = "CONFIRMADA";
        this.dataHora = LocalDateTime.now();
    }

    public int getId() { return id; }
    public Restaurante getRestaurante() { return restaurante; }
    public Instituicao getInstituicao() { return instituicao; }
    public Alimento getAlimento() { return alimento; }
    public int getQuantidade() { return quantidade; }
    public String getStatus() { return status; }
    public LocalDateTime getDataHora() { return dataHora; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("O ID deve ser positivo.");
        this.id = id;
    }

    public void setRestaurante(Restaurante restaurante) {
        if (restaurante == null) throw new IllegalArgumentException("O restaurante é obrigatório.");
        this.restaurante = restaurante;
    }

    public void setInstituicao(Instituicao instituicao) {
        if (instituicao == null) throw new IllegalArgumentException("A instituição é obrigatória.");
        this.instituicao = instituicao;
    }

    public void setAlimento(Alimento alimento) {
        if (alimento == null) throw new IllegalArgumentException("O alimento é obrigatório.");
        this.alimento = alimento;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        this.quantidade = quantidade;
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) throw new IllegalArgumentException("O status é obrigatório.");
        this.status = status.trim().toUpperCase();
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null) throw new IllegalArgumentException("A data e hora são obrigatórias.");
        this.dataHora = dataHora;
    }
}
