package main;

import java.time.LocalDate;
import model.Alimento;
import model.Consumidor;
import model.Instituicao;
import model.Pedido;
import model.Restaurante;
import service.RetasteService;

public class Main {
    public static void main(String[] args) {
        RetasteService retaste = new RetasteService();

        System.out.println("==============================================");
        System.out.println("                    RETASTE                   ");
        System.out.println("==============================================");

        Restaurante restaurante = new Restaurante(
                1, "Restaurante Verde Vida", "12345678000190",
                "Avenida Paulista, 1000", "11999999999");

        Consumidor consumidor = new Consumidor(
                1, "Joao da Silva", "12345678900",
                "joao@email.com", "11988887777");

        Instituicao instituicao = new Instituicao(
                1, "Instituto Solidariedade", "11222333000144",
                "Rua das Flores, 200", "11977776666", "Maria Oliveira");

        retaste.cadastrarRestaurante(restaurante);
        retaste.cadastrarConsumidor(consumidor);
        retaste.cadastrarInstituicao(instituicao);

        Alimento marmita = new Alimento(
                1, "Marmita de frango", "Arroz, frango e legumes",
                25.00, 15.00, 20, LocalDate.now().plusDays(2), restaurante);

        Alimento suco = new Alimento(
                2, "Suco natural", "Suco de laranja 300 ml",
                10.00, 7.00, 15, LocalDate.now().plusDays(10), restaurante);

        retaste.cadastrarAlimento(marmita);
        retaste.cadastrarAlimento(suco);

        System.out.println("=== ESTOQUE INICIAL ===");
        imprimirAlimento(marmita);
        imprimirAlimento(suco);

        System.out.println("=== VENDA NORMAL ===");
        Pedido vendaNormal = retaste.realizarVendaNormal(1, consumidor, marmita, 4);
        imprimirPedido(vendaNormal);

        System.out.println("=== MONITORAMENTO AUTOMATICO DE VALIDADE ===");
        retaste.monitorarValidades();

        System.out.println("=== OFERTAS COM DESCONTO ===");
        for (Alimento oferta : retaste.listarOfertasComDesconto()) {
            System.out.println(oferta.getNome()
                    + " | preco normal: R$ " + oferta.getPrecoNormal()
                    + " | preco promocional: R$ " + oferta.getPrecoDesconto()
                    + " | desconto: " + String.format("%.1f", oferta.calcularPercentualDesconto())
                    + "% | quantidade: " + oferta.getEstoqueDesconto());
        }

        System.out.println("=== VENDA COM DESCONTO ===");
        Pedido vendaDesconto = retaste.realizarVendaComDesconto(2, consumidor, marmita, 3);
        imprimirPedido(vendaDesconto);

        System.out.println("=== TESTE: ESTOQUE PROMOCIONAL INSUFICIENTE ===");
        Pedido tentativaPromocional = retaste.realizarVendaComDesconto(3, consumidor, marmita, 100);
        imprimirPedido(tentativaPromocional);

        System.out.println("=== TESTE: ESTOQUE NORMAL INSUFICIENTE ===");
        Pedido tentativaNormal = retaste.realizarVendaNormal(4, consumidor, suco, 100);
        imprimirPedido(tentativaNormal);

        System.out.println("=== TESTE: ALIMENTO VENCIDO ===");
        Alimento alimentoVencido = new Alimento(
                3, "Salada vencida", "Salada para teste de descarte",
                12.00, 6.00, 5, LocalDate.now().minusDays(1), restaurante);
        retaste.cadastrarAlimento(alimentoVencido);
        retaste.monitorarValidades();
        System.out.println("Estoque normal após retirada: " + alimentoVencido.getEstoqueNormal());
        System.out.println("Quantidade retirada por vencimento: " + alimentoVencido.getQuantidadeVencida());

        System.out.println("=== TESTE: VENDA DE ALIMENTO VENCIDO ===");
        Pedido tentativaVencido = retaste.realizarVendaNormal(5, consumidor, alimentoVencido, 1);
        imprimirPedido(tentativaVencido);

        System.out.println("=== RESUMO FINAL ===");
        System.out.println("Doacoes geradas automaticamente: " + retaste.getDoacoes().size());
        for (var doacao : retaste.getDoacoes()) {
            System.out.println("Instituicao: " + doacao.getInstituicao().getNome()
                    + " | alimento: " + doacao.getAlimento().getNome()
                    + " | quantidade: " + doacao.getQuantidade()
                    + " | status: " + doacao.getStatus());
        }

        System.out.println("Estoque final da marmita:");
        imprimirAlimento(marmita);

        System.out.println("==============================================");
        System.out.println("                 FIM DO RETASTE");
        System.out.println("==============================================");
    }

    private static void imprimirAlimento(Alimento alimento) {
        System.out.println("Alimento: " + alimento.getNome());
        System.out.println("Validade: " + alimento.getValidade());
        System.out.println("Estoque normal: " + alimento.getEstoqueNormal());
        System.out.println("Estoque com desconto: " + alimento.getEstoqueDesconto());
        System.out.println("Quantidade doada: " + alimento.getQuantidadeDoada());
        System.out.println("Quantidade vencida retirada: " + alimento.getQuantidadeVencida());
    }

    private static void imprimirPedido(Pedido pedido) {
        System.out.println("Tipo de venda: " + pedido.getTipoVenda());
        System.out.println("Consumidor: " + pedido.getConsumidor().getNome());
        System.out.println("Alimento: " + pedido.getAlimento().getNome());
        System.out.println("Quantidade: " + pedido.getQuantidade());
        System.out.println("Valor unitario: R$ " + pedido.getValorUnitario());
        System.out.println("Valor total: R$ " + pedido.getValorTotal());
        System.out.println("Status: " + pedido.getStatus());
    }
}
