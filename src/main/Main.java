
package main;

import model.Alimento;
import model.Restaurante;


public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("     MARKETPLACE RETASTE        ");
        System.out.println("================================");

        // Criando O primeiro restaurante
        Restaurante restaurante1 = new Restaurante(
            1,
            "Spoleto",
            "12345678000190",
            "Shopping Center",
            "11999999999"
        );

        Restaurante restaurante2 = new Restaurante(
            2,
            "Giraffas",
            "98765432000190",
            "Shopping Center",
            "11988888888"
        );

        Alimento alimento1 = new Alimento(
            1,
            "Marmita de frango",
            "Marmita com arroz, frango e legumes",
            25.00,
            15.00,
            10,
            "15/11/2026",
            restaurante1
);

        // Consultando os atributos com getters
        System.out.println("\n=== RESTAURANTE CADASTRADO ===");
        System.out.println("ID: " + restaurante1.getId());
        System.out.println("Nome: " + restaurante1.getNome());
        System.out.println("CNPJ: " + restaurante1.getCnpj());
        System.out.println("Endereco: " + restaurante1.getEndereco());
        System.out.println("Telefone: " + restaurante1.getTelefone());

        System.out.println("\n=== SEGUNDO RESTAURANTE ===");
        System.out.println("ID: " + restaurante2.getId());
        System.out.println("Nome: " + restaurante2.getNome());
        System.out.println("CNPJ: " + restaurante2.getCnpj());
        System.out.println("Endereco: " + restaurante2.getEndereco());
        System.out.println("Telefone: " + restaurante2.getTelefone());


        System.out.println("\n=== ALIMENTO CADASTRADO ===");
        System.out.println("Nome: " + alimento1.getNome());
        System.out.println("Descricao: " + alimento1.getDescricao());
        System.out.println("Preco original: R$ " + alimento1.getPrecoOriginal());
        System.out.println("Preco com desconto: R$ " + alimento1.getPrecoDesconto());
        System.out.println("Quantidade: " + alimento1.getQuantidade());
        System.out.println("Validade: " + alimento1.getValidade());
        System.out.println("Restaurante: " + alimento1.getRestaurante().getNome());

        // Atualizando um atributo com setter
        restaurante1.setEndereco("Shopping Internacional");

        System.out.println("\n=== DADOS ATUALIZADOS ===");
        System.out.println("Novo endereco: " + restaurante1.getEndereco());

    }
}
