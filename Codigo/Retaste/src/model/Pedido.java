package model;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private Consumidor consumidor;
    private Alimento alimento;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    private String tipoVenda;
    private String status;
    private LocalDateTime dataHora;

    public Pedido(int id, Consumidor consumidor, Alimento alimento,
                  int quantidade, String tipoVenda) {
        if (id <= 0) throw new IllegalArgumentException("O ID do pedido deve ser positivo.");
        if (consumidor == null) throw new IllegalArgumentException("O consumidor é obrigatório.");
        if (alimento == null) throw new IllegalArgumentException("O alimento é obrigatório.");
        if (quantidade <= 0) throw new IllegalArgumentException("A quantidade deve ser maior que zero.");

        this.id = id;
        this.consumidor = consumidor;
        this.alimento = alimento;
        this.quantidade = quantidade;
        this.tipoVenda = normalizarTipoVenda(tipoVenda);
        atualizarValores();
        this.status = "PENDENTE";
        this.dataHora = LocalDateTime.now();
    }

    public int getId() { return id; }
    public Consumidor getConsumidor() { return consumidor; }
    public Alimento getAlimento() { return alimento; }
    public int getQuantidade() { return quantidade; }
    public double getValorUnitario() { return valorUnitario; }
    public double getValorTotal() { return valorTotal; }
    public String getTipoVenda() { return tipoVenda; }
    public String getStatus() { return status; }
    public LocalDateTime getDataHora() { return dataHora; }

    private void garantirPendente() {
        if (!"PENDENTE".equals(status)) {
            throw new IllegalStateException("Não é possível alterar um pedido já processado.");
        }
    }

    private String normalizarTipoVenda(String tipoVenda) {
        if (tipoVenda == null ||
                (!"NORMAL".equalsIgnoreCase(tipoVenda) && !"DESCONTO".equalsIgnoreCase(tipoVenda))) {
            throw new IllegalArgumentException("Tipo de venda inválido. Use NORMAL ou DESCONTO.");
        }
        return tipoVenda.toUpperCase();
    }

    private void atualizarValores() {
        this.valorUnitario = "DESCONTO".equals(tipoVenda)
                ? alimento.getPrecoDesconto() : alimento.getPrecoNormal();
        this.valorTotal = valorUnitario * quantidade;
    }

    public boolean processar() {
        if (!"PENDENTE".equals(status)) return false;

        boolean sucesso;
        if ("DESCONTO".equals(tipoVenda)) {
            sucesso = alimento.registrarVendaDesconto(quantidade);
        } else {
            sucesso = alimento.registrarVendaNormal(quantidade);
        }

        status = sucesso ? "CONFIRMADO" : "CANCELADO";
        return sucesso;
    }

    public void setId(int id) {
        garantirPendente();
        if (id <= 0) throw new IllegalArgumentException("O ID deve ser positivo.");
        this.id = id;
    }

    public void setConsumidor(Consumidor consumidor) {
        garantirPendente();
        if (consumidor == null) throw new IllegalArgumentException("O consumidor é obrigatório.");
        this.consumidor = consumidor;
    }

    public void setAlimento(Alimento alimento) {
        garantirPendente();
        if (alimento == null) throw new IllegalArgumentException("O alimento é obrigatório.");
        this.alimento = alimento;
        atualizarValores();
    }

    public void setQuantidade(int quantidade) {
        garantirPendente();
        if (quantidade <= 0) throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        this.quantidade = quantidade;
        atualizarValores();
    }

    // O preço unitário é derivado do alimento e do tipo de venda.
    // O setter é mantido para atender à interface de acesso, mas não permite quebrar a regra de preço.
    public void setValorUnitario(double valorUnitario) {
        garantirPendente();
        if (valorUnitario < 0 || Math.abs(valorUnitario - this.valorUnitario) > 0.000001) {
            throw new IllegalArgumentException("O valor unitário é definido pelo alimento e pelo tipo de venda.");
        }
    }

    // O total também é derivado; só aceita o valor calculado pelo sistema.
    public void setValorTotal(double valorTotal) {
        garantirPendente();
        double esperado = valorUnitario * quantidade;
        if (valorTotal < 0 || Math.abs(valorTotal - esperado) > 0.000001) {
            throw new IllegalArgumentException("O valor total deve corresponder à quantidade multiplicada pelo preço unitário.");
        }
        this.valorTotal = esperado;
    }

    public void setTipoVenda(String tipoVenda) {
        garantirPendente();
        this.tipoVenda = normalizarTipoVenda(tipoVenda);
        atualizarValores();
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) throw new IllegalArgumentException("O status é obrigatório.");
        if (!"PENDENTE".equals(this.status)) {
            throw new IllegalStateException("O status de um pedido processado não pode ser alterado manualmente.");
        }
        if (!"PENDENTE".equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("O status é controlado pelo processamento do pedido.");
        }
        this.status = "PENDENTE";
    }

    public void setDataHora(LocalDateTime dataHora) {
        garantirPendente();
        if (dataHora == null) throw new IllegalArgumentException("A data e hora são obrigatórias.");
        this.dataHora = dataHora;
    }
}
