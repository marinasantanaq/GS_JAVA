package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Alimento;
import model.Consumidor;
import model.Doacao;
import model.Instituicao;
import model.Pedido;
import model.Restaurante;

public class RetasteService {
    private static final int DIAS_ANTECEDENCIA = 3;
    private static final int PERCENTUAL_DOACAO = 50;

    private final List<Restaurante> restaurantes = new ArrayList<>();
    private final List<Alimento> alimentos = new ArrayList<>();
    private final List<Consumidor> consumidores = new ArrayList<>();
    private final List<Instituicao> instituicoes = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private final List<Doacao> doacoes = new ArrayList<>();

    public void cadastrarRestaurante(Restaurante restaurante) {
        if (restaurante == null) throw new IllegalArgumentException("Restaurante não pode ser nulo.");
        restaurantes.add(restaurante);
    }

    public void cadastrarAlimento(Alimento alimento) {
        if (alimento == null) throw new IllegalArgumentException("Alimento não pode ser nulo.");
        alimentos.add(alimento);
    }

    public void cadastrarConsumidor(Consumidor consumidor) {
        if (consumidor == null) throw new IllegalArgumentException("Consumidor não pode ser nulo.");
        consumidores.add(consumidor);
    }

    public void cadastrarInstituicao(Instituicao instituicao) {
        if (instituicao == null) throw new IllegalArgumentException("Instituição não pode ser nula.");
        instituicoes.add(instituicao);
    }

    /**
     * Monitora os alimentos cadastrados.
     * Alimentos vencidos são retirados independentemente da existência de instituições.
     * A triagem de alimentos próximos do vencimento aguarda uma instituição,
     * pois a parcela destinada à doação não pode ser descartada nem desviada.
     */
    public void monitorarValidades() {
        for (Alimento alimento : alimentos) {
            if (alimento.estaVencido()) {
                int removido = alimento.retirarEstoqueVencido();
                if (removido > 0) {
                    System.out.println("ALERTA: " + alimento.getNome()
                            + " venceu. " + removido + " unidade(s) foram retiradas do estoque.");
                }
                continue;
            }

            if (alimento.estaProximoDaValidade(DIAS_ANTECEDENCIA)
                    && !alimento.isTriagemAutomaticaRealizada()) {
                if (instituicoes.isEmpty()) {
                    System.out.println("Triagem adiada para " + alimento.getNome()
                            + ": cadastre uma instituição para receber a doação.");
                    continue;
                }

                int[] divisao = alimento.executarTriagemAutomatica(PERCENTUAL_DOACAO);
                int quantidadeDoacao = divisao[0];
                int quantidadeDesconto = divisao[1];

                if (quantidadeDoacao > 0) {
                    Doacao doacao = new Doacao(
                            doacoes.size() + 1,
                            alimento.getRestaurante(),
                            instituicoes.get(0),
                            alimento,
                            quantidadeDoacao
                    );
                    doacoes.add(doacao);
                }

                System.out.println("TRIAGEM AUTOMATICA: " + alimento.getNome());
                System.out.println("Dias ate o vencimento: " + alimento.diasAteVencimento());
                System.out.println("Destinado a doacao: " + quantidadeDoacao);
                System.out.println("Disponibilizado com desconto: " + quantidadeDesconto);
            }
        }
    }

    public Pedido realizarVendaNormal(int id, Consumidor consumidor,
                                      Alimento alimento, int quantidade) {
        Pedido pedido = new Pedido(id, consumidor, alimento, quantidade, "NORMAL");
        pedido.processar();
        pedidos.add(pedido);
        return pedido;
    }

    public Pedido realizarVendaComDesconto(int id, Consumidor consumidor,
                                           Alimento alimento, int quantidade) {
        Pedido pedido = new Pedido(id, consumidor, alimento, quantidade, "DESCONTO");
        pedido.processar();
        pedidos.add(pedido);
        return pedido;
    }

    public List<Alimento> listarEstoqueNormal() {
        List<Alimento> resultado = new ArrayList<>();
        for (Alimento alimento : alimentos) {
            if (alimento.getEstoqueNormal() > 0 && !alimento.estaVencido()) resultado.add(alimento);
        }
        return Collections.unmodifiableList(resultado);
    }

    public List<Alimento> listarOfertasComDesconto() {
        List<Alimento> resultado = new ArrayList<>();
        for (Alimento alimento : alimentos) {
            if (alimento.getEstoqueDesconto() > 0 && !alimento.estaVencido()) resultado.add(alimento);
        }
        return Collections.unmodifiableList(resultado);
    }

    public List<Restaurante> getRestaurantes() { return Collections.unmodifiableList(restaurantes); }
    public List<Alimento> getAlimentos() { return Collections.unmodifiableList(alimentos); }
    public List<Consumidor> getConsumidores() { return Collections.unmodifiableList(consumidores); }
    public List<Instituicao> getInstituicoes() { return Collections.unmodifiableList(instituicoes); }
    public List<Pedido> getPedidos() { return Collections.unmodifiableList(pedidos); }
    public List<Doacao> getDoacoes() { return Collections.unmodifiableList(doacoes); }
}
