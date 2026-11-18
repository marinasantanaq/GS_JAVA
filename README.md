# 🍃 RETASTE — Sistema Inteligente de Gestão de Alimentos

## 📌 Sobre o projeto

O **Retaste** é uma solução tecnológica desenvolvida para combater o desperdício de alimentos em estabelecimentos alimentícios, promovendo o aproveitamento de produtos próximos ao vencimento por meio de vendas promocionais e doações para instituições sociais.

O sistema realiza o monitoramento das datas de validade e automatiza a destinação dos alimentos elegíveis, contribuindo para uma gestão de estoque mais eficiente e para a redução do desperdício.

A proposta conecta três participantes principais:

- **Restaurantes:** cadastram e gerenciam seus alimentos.
- **Consumidores:** podem adquirir produtos normalmente ou por preços promocionais.
- **Instituições sociais:** recebem alimentos destinados à doação.

## 🎯 Objetivos

- Reduzir o desperdício de alimentos em estabelecimentos.
- Automatizar o monitoramento das datas de validade.
- Facilitar a destinação de alimentos próximos ao vencimento.
- Incentivar o consumo consciente por meio de vendas promocionais.
- Apoiar instituições sociais com doações de alimentos.

## ⚙️ Funcionalidades

### 1. Gestão de estoque

Permite cadastrar alimentos vinculados aos restaurantes, armazenando informações como nome, descrição, preços, quantidade disponível e data de validade.

O sistema acompanha separadamente o estoque normal, promocional e as quantidades destinadas à doação.

### 2. Monitoramento automático de validade

O sistema verifica as datas de validade dos alimentos cadastrados.

Na implementação demonstrativa, alimentos que estão a até **3 dias do vencimento** passam por uma triagem automática, desde que ainda não tenham sido processados.

A triagem utiliza a seguinte regra:

- 50% do estoque elegível é destinado à doação.
- Os outros 50% são disponibilizados para venda promocional.

Os percentuais e o prazo são parâmetros demonstrativos, podendo ser ajustados conforme a necessidade do estabelecimento.

Alimentos vencidos são retirados do estoque disponível para comercialização.

### 3. Vendas normais e promocionais

O sistema permite registrar dois tipos de venda:

- **Venda normal:** utiliza o preço original do alimento e reduz o estoque convencional.
- **Venda promocional:** utiliza o preço com desconto e reduz exclusivamente o estoque promocional.

Antes de confirmar uma venda, o sistema verifica a disponibilidade da quantidade solicitada.

### 4. Gestão de doações

Após a triagem automática, o sistema gera registros de doação vinculados ao restaurante, à instituição social e ao alimento destinado.

Cada registro contém informações como quantidade, data, horário e status da doação.

## 🧠 Regras de negócio

| Regra | Descrição |
|---|---|
| RN01 | Os alimentos devem possuir informações de identificação, preço, estoque e validade. |
| RN02 | As vendas normais reduzem somente o estoque convencional. |
| RN03 | Alimentos próximos do vencimento passam pela triagem automática. |
| RN04 | A triagem é realizada apenas uma vez para cada alimento. |
| RN05 | O estoque elegível é dividido entre doação e venda promocional. |
| RN06 | As vendas promocionais reduzem somente o estoque promocional. |
| RN07 | Não são permitidas vendas em quantidade superior ao estoque disponível. |
| RN08 | Alimentos vencidos são retirados do estoque comercializável. |

## 🛠️ Tecnologias utilizadas

- **Java:** linguagem de programação utilizada no desenvolvimento.
- **JDK 25 (Temurin):** ambiente de desenvolvimento e compilação.
- **Visual Studio Code:** ambiente de desenvolvimento.
- **Git e GitHub:** controle de versão e gerenciamento do código-fonte.

## 📂 Estrutura do projeto

```text
Retaste/
│
├── src/
│   ├── model/
│   │   ├── Restaurante.java
│   │   ├── Consumidor.java
│   │   ├── Instituicao.java
│   │   ├── Alimento.java
│   │   ├── Pedido.java
│   │   └── Doacao.java
│   │
│   ├── service/
│   │   └── RetasteService.java
│   │
│   └── main/
│       └── Main.java
│
├── .gitignore
└── README.md
```

## 🏗️ Organização das classes

### Model

Contém as classes responsáveis pela representação dos objetos e entidades do sistema.

| Classe | Responsabilidade |
|---|---|
| `Restaurante` | Representa os estabelecimentos cadastrados. |
| `Consumidor` | Representa os consumidores que realizam pedidos. |
| `Instituicao` | Representa as instituições sociais beneficiadas. |
| `Alimento` | Armazena informações dos alimentos, validade e controle de estoque. |
| `Pedido` | Representa as vendas realizadas, seus valores e status. |
| `Doacao` | Representa as doações destinadas às instituições. |

### Service

A classe `RetasteService` centraliza as operações do sistema, incluindo:

- Cadastro de restaurantes, alimentos, consumidores e instituições.
- Monitoramento das datas de validade.
- Execução da triagem automática.
- Processamento de vendas normais e promocionais.
- Registro e gerenciamento de doações.

### Main

A classe `Main` inicializa o sistema e executa os cenários de demonstração, permitindo verificar o funcionamento das principais operações.

## ▶️ Como executar o projeto

### Pré-requisitos

Antes de executar, é necessário possuir:

- JDK instalado (versão 25 utilizada no desenvolvimento).
- Visual Studio Code ou outro ambiente compatível com Java.
- Terminal com acesso aos comandos `javac` e `java`.

### 1. Clonar o repositório

```bash
git clone https://github.com/marinasantanaq/GS_JAVA.git
```

### 2. Acessar a pasta do projeto

```bash
cd GS_JAVA
```

### 3. Compilar os arquivos

No terminal, execute:

```powershell
javac -d out src/model/*.java src/service/*.java src/main/Main.java
```

### 4. Executar o sistema

```powershell
java -cp out main.Main
```

## 🧪 Cenários demonstrados

A classe `Main` apresenta cenários para validar as funcionalidades implementadas:

1. Cadastro de restaurante, consumidor e instituição social.
2. Cadastro de alimentos com diferentes datas de validade.
3. Realização de uma venda convencional.
4. Monitoramento automático das validades.
5. Execução da triagem e divisão do estoque elegível.
6. Geração automática de registros de doação.
7. Disponibilização de alimentos para venda promocional.
8. Realização de uma venda com desconto.
9. Teste de tentativa de venda com estoque insuficiente.
10. Exibição do resumo final do estoque e das doações.

## ⚠️ Limitações da implementação

Esta versão representa uma implementação demonstrativa da lógica de negócio do Retaste.

Atualmente:

- O cadastro e a alimentação dos dados são realizados diretamente pela aplicação Java.
- O monitoramento é executado quando o método `monitorarValidades()` é chamado, não existindo um agendador em segundo plano.
- A destinação das quantidades segue percentuais predefinidos para demonstração.
- A doação é direcionada à primeira instituição cadastrada.
- Não há integração implementada com sistemas externos de estoque, bancos de dados ou plataformas de pagamento.

Essas funcionalidades podem ser evoluídas em versões futuras.

## 🌱 Impacto esperado

O Retaste busca transformar o gerenciamento de alimentos próximos ao vencimento em uma oportunidade de geração de valor econômico, social e ambiental.

Ao conectar estabelecimentos, consumidores e instituições sociais, a solução contribui para o consumo consciente, o aproveitamento de alimentos e o fortalecimento de iniciativas de combate à insegurança alimentar.

---

**Projeto desenvolvido para a Global Solution — FIAP | 2026.**

**Equipe Retaste**

<div align="center">

<table>
  <tr>
    <td align="center" width="180">
      <img src="imagens/rafael.jpeg" width="80" height="80" alt="Rafael Madeira"/>
      <br><br>
      <strong>Rafael Madeira</strong>
      <br>
      <sub>Banco de Dados</sub>
      <br>
      <sub>RM 575635 · 1TDSPA</sub>
      <br><br>
      <a href="https://www.linkedin.com/in/rafael-madeira-b8545b312/">LinkedIn</a>
      ·
      <a href="https://github.com/Ribeir0296">GitHub</a>
    </td>

<td align="center" width="180">
  <img src="imagens/enrique.jpeg" width="80" height="80" alt="Enrique Trindade"/>
  <br><br>
  <strong>Enrique Trindade</strong>
  <br>
  <sub>Chatbot</sub>
  <br>
  <sub>RM 576314 · 1TDSPA</sub>
  <br><br>
  <a href="https://www.linkedin.com/in/enrique-trindade-46a0012b4/">LinkedIn</a>
  ·
  <a href="https://github.com/enriquetrindade">GitHub</a>
</td>

<td align="center" width="180">
  <img src="imagens/pollo.jpeg" width="80" height="80" alt="Julio Bernardes"/>
  <br><br>
  <strong>Julio Bernardes</strong>
  <br>
  <sub>Desenvolvimento Python</sub>
  <br>
  <sub>RM 575649 · 1TDSPA</sub>
  <br><br>
  <a href="https://www.linkedin.com/in/julio-bernardes-da-silva-40738b431/">LinkedIn</a>
  ·
  <a href="https://github.com/JulioSilva101">GitHub</a>
</td>

<td align="center" width="180">
  <img src="imagens/marina.jpeg" width="80" height="80" alt="Marina Queiroz"/>
  <br><br>
  <strong>Marina Queiroz</strong>
  <br>
  <sub>Java e Business Model</sub>
  <br>
  <sub>RM 574491 · 1TDSPA</sub>
  <br><br>
  <a href="https://www.linkedin.com/in/marina-santana-queiroz-9baa77241/">LinkedIn</a>
  ·
  <a href="https://github.com/marinasantanaq">GitHub</a>
</td>

<td align="center" width="180">
  <img src="imagens/sofia.jpg" width="80" height="80" alt="Sofia Oliveira Gonçalves"/>
  <br><br>
  <strong>Sofia Oliveira</strong>
  <br>
  <sub>Designer</sub>
  <br>
  <sub>RM 576524 · 1TDSPA</sub>
  <br><br>
  <a href="https://www.linkedin.com/in/sofia-goncalves-272472431/">LinkedIn</a>
  ·
  <a href="https://github.com/sofiaolivagoliva">GitHub</a>
</td>

  </tr>
</table>

</div>


