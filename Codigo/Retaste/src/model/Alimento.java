package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alimento {
    private int id;
    private String nome;
    private String descricao;
    private double precoNormal;
    private double precoDesconto;
    private LocalDate validade;
    private Restaurante restaurante;

    // Quantidades separadas por finalidade
    private int estoqueNormal;
    private int estoqueDesconto;
    private int quantidadeDoada;
    private int quantidadeVendidaNormal;
    private int quantidadeVendidaDesconto;
    private int quantidadeVencida;
    private boolean triagemAutomaticaRealizada;

    public Alimento(int id, String nome, String descricao,
                    double precoNormal, double precoDesconto,
                    int estoqueInicial, LocalDate validade,
                    Restaurante restaurante) {
        if (id <= 0) throw new IllegalArgumentException("O ID deve ser positivo.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("O nome do alimento é obrigatório.");
        if (estoqueInicial < 0 || precoNormal < 0 || precoDesconto < 0
                || precoDesconto > precoNormal) {
            throw new IllegalArgumentException("Dados de preço ou estoque inválidos.");
        }
        if (validade == null) throw new IllegalArgumentException("A validade é obrigatória.");
        if (restaurante == null) throw new IllegalArgumentException("O restaurante é obrigatório.");
        this.id = id;
        this.nome = nome.trim();
        this.descricao = descricao == null ? "" : descricao.trim();
        this.precoNormal = precoNormal;
        this.precoDesconto = precoDesconto;
        this.estoqueNormal = estoqueInicial;
        this.validade = validade;
        this.restaurante = restaurante;
        this.estoqueDesconto = 0;
        this.quantidadeDoada = 0;
        this.quantidadeVendidaNormal = 0;
        this.quantidadeVendidaDesconto = 0;
        this.quantidadeVencida = 0;
        this.triagemAutomaticaRealizada = false;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPrecoNormal() { return precoNormal; }
    public double getPrecoDesconto() { return precoDesconto; }
    public LocalDate getValidade() { return validade; }
    public Restaurante getRestaurante() { return restaurante; }
    public int getEstoqueNormal() { return estoqueNormal; }
    public int getEstoqueDesconto() { return estoqueDesconto; }
    public int getQuantidadeDoada() { return quantidadeDoada; }
    public int getQuantidadeVendidaNormal() { return quantidadeVendidaNormal; }
    public int getQuantidadeVendidaDesconto() { return quantidadeVendidaDesconto; }
    public int getQuantidadeVencida() { return quantidadeVencida; }
    public boolean isTriagemAutomaticaRealizada() { return triagemAutomaticaRealizada; }

    public long diasAteVencimento() {
        return ChronoUnit.DAYS.between(LocalDate.now(), validade);
    }

    public boolean estaVencido() {
        return validade.isBefore(LocalDate.now());
    }

    public boolean estaProximoDaValidade(int limiteDias) {
        long dias = diasAteVencimento();
        return dias >= 0 && dias <= limiteDias;
    }

    public boolean registrarVendaNormal(int quantidade) {
        if (estaVencido() || quantidade <= 0 || quantidade > estoqueNormal) return false;
        estoqueNormal -= quantidade;
        quantidadeVendidaNormal += quantidade;
        return true;
    }

    public boolean registrarVendaDesconto(int quantidade) {
        if (estaVencido() || quantidade <= 0 || quantidade > estoqueDesconto) return false;
        estoqueDesconto -= quantidade;
        quantidadeVendidaDesconto += quantidade;
        return true;
    }

    // Transfere automaticamente parte do estoque normal para doação e o restante para venda promocional.
    public int[] executarTriagemAutomatica(int percentualDoacao) {
        if (percentualDoacao < 0 || percentualDoacao > 100) {
            throw new IllegalArgumentException("O percentual de doação deve estar entre 0 e 100.");
        }
        if (triagemAutomaticaRealizada || estaVencido()) return new int[] {0, 0};

        int quantidadeParaTriagem = estoqueNormal;
        if (quantidadeParaTriagem == 0) {
            triagemAutomaticaRealizada = true;
            return new int[] {0, 0};
        }

        int paraDoacao = (int) Math.floor(quantidadeParaTriagem * percentualDoacao / 100.0);
        int paraDesconto = quantidadeParaTriagem - paraDoacao;

        estoqueNormal = 0;
        quantidadeDoada += paraDoacao;
        estoqueDesconto += paraDesconto;
        triagemAutomaticaRealizada = true;

        return new int[] {paraDoacao, paraDesconto};
    }

    public int retirarEstoqueVencido() {
        if (!estaVencido()) return 0;
        int retirado = estoqueNormal + estoqueDesconto;
        estoqueNormal = 0;
        estoqueDesconto = 0;
        quantidadeVencida += retirado;
        triagemAutomaticaRealizada = true;
        return retirado;
    }

    public double calcularPercentualDesconto() {
        if (precoNormal == 0) return 0;
        return ((precoNormal - precoDesconto) / precoNormal) * 100.0;
    }

    @Override
    public String toString() {
        return nome + " | validade: " + validade
                + " | estoque normal: " + estoqueNormal
                + " | estoque promocional: " + estoqueDesconto;
    }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("O ID deve ser positivo.");
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("O nome do alimento é obrigatório.");
        this.nome = nome.trim();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao == null ? "" : descricao.trim();
    }

    public void setPrecoNormal(double precoNormal) {
        if (precoNormal < 0 || precoNormal < precoDesconto) {
            throw new IllegalArgumentException("O preço normal não pode ser negativo nem inferior ao promocional.");
        }
        this.precoNormal = precoNormal;
    }

    public void setPrecoDesconto(double precoDesconto) {
        if (precoDesconto < 0 || precoDesconto > precoNormal) {
            throw new IllegalArgumentException("O preço promocional deve estar entre zero e o preço normal.");
        }
        this.precoDesconto = precoDesconto;
    }

    public void setValidade(LocalDate validade) {
        if (validade == null) throw new IllegalArgumentException("A validade é obrigatória.");
        this.validade = validade;
    }

    public void setRestaurante(Restaurante restaurante) {
        if (restaurante == null) throw new IllegalArgumentException("O restaurante é obrigatório.");
        this.restaurante = restaurante;
    }

    public void setEstoqueNormal(int estoqueNormal) {
        if (estoqueNormal < 0) throw new IllegalArgumentException("O estoque normal não pode ser negativo.");
        this.estoqueNormal = estoqueNormal;
    }

    public void setEstoqueDesconto(int estoqueDesconto) {
        if (estoqueDesconto < 0) throw new IllegalArgumentException("O estoque promocional não pode ser negativo.");
        this.estoqueDesconto = estoqueDesconto;
    }

    public void setQuantidadeDoada(int quantidadeDoada) {
        if (quantidadeDoada < 0) throw new IllegalArgumentException("A quantidade doada não pode ser negativa.");
        this.quantidadeDoada = quantidadeDoada;
    }

    public void setQuantidadeVendidaNormal(int quantidadeVendidaNormal) {
        if (quantidadeVendidaNormal < 0) throw new IllegalArgumentException("A quantidade vendida não pode ser negativa.");
        this.quantidadeVendidaNormal = quantidadeVendidaNormal;
    }

    public void setQuantidadeVendidaDesconto(int quantidadeVendidaDesconto) {
        if (quantidadeVendidaDesconto < 0) throw new IllegalArgumentException("A quantidade vendida não pode ser negativa.");
        this.quantidadeVendidaDesconto = quantidadeVendidaDesconto;
    }

    public void setQuantidadeVencida(int quantidadeVencida) {
        if (quantidadeVencida < 0) throw new IllegalArgumentException("A quantidade vencida não pode ser negativa.");
        this.quantidadeVencida = quantidadeVencida;
    }

    public void setTriagemAutomaticaRealizada(boolean triagemAutomaticaRealizada) {
        this.triagemAutomaticaRealizada = triagemAutomaticaRealizada;
    }
}
