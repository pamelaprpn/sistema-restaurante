# 🍔 Sistema Restaurante — Java

Sistema de gerenciamento de pedidos desenvolvido em **Java** como projeto prático do **Bootcamp Java Full Stack ElasTech**.

A aplicação funciona pelo terminal e permite cadastrar pedidos, personalizar lanches, adicionar extras, modificar pedidos, registrar pagamentos e realizar o fechamento financeiro.

O objetivo é aplicar conceitos de **Programação Orientada a Objetos (POO)**, coleções Java, tratamento de exceções e padrões de projeto.

## 🚀 Funcionalidades

- Cadastro de pedidos com identificação do cliente e numeração automática.
- Seleção de lanches com preços predefinidos.
- Personalização dos lanches, permitindo retirar ingredientes.
- Inclusão de adicionais como bacon, cheddar e molho.
- Consulta e listagem dos pedidos do dia.
- Modificação de pedidos, adicionando ou removendo itens.
- Cancelamento de pedidos.
- Registro de pagamentos com gorjeta opcional.
- Prevenção de pagamentos duplicados.
- Bloqueio de modificações em pedidos pagos pelo menu.
- Relatório financeiro com valores pagos, pendentes e gorjetas.
- Aviso e confirmação de fechamento quando existem pagamentos pendentes.


## 🎥 Demonstração do projeto

Confira a apresentação da arquitetura UML e a demonstração do Sistema Restaurante em funcionamento.

O vídeo apresenta os padrões de projeto utilizados, o gerenciamento de pedidos, a personalização de lanches, os pagamentos e o fechamento financeiro.

▶️ [Assistir à demonstração](https://youtu.be/vw5Vw-Qu21Q)


## 🛠️ Tecnologias utilizadas

- Java
- Maven
- JUnit 5
- Java Collections (`ArrayList`, `HashMap`, `HashSet`)
- Programação Orientada a Objetos

## 🏗️ Arquitetura do projeto

O sistema foi organizado em quatro pacotes principais, seguindo a separação de responsabilidades.

```text
src/main/java/com/example/sistema_restaurante/
│
├── app/
│   ├── Main.java
│   ├── MenuConsole.java
│   └── RestauranteFacade.java
│
├── cardapio/
│   ├── ItemCardapio.java
│   ├── Lanche.java
│   ├── LancheFactory.java
│   ├── AdicionalDecorator.java
│   ├── BaconExtra.java
│   ├── CheddarExtra.java
│   └── MolhoExtra.java
│
├── pedido/
│   ├── Pedido.java
│   └── GerenciadorPedidos.java
│
└── financeiro/
    ├── Caixa.java
    └── Transacao.java
```

### Responsabilidades dos pacotes

| Pacote | Responsabilidade |
|---|---|
| `app` | Inicialização, interação pelo console e coordenação das operações |
| `cardapio` | Criação e personalização dos lanches e adicionais |
| `pedido` | Cadastro, armazenamento e gerenciamento dos pedidos |
| `financeiro` | Registro de pagamentos, gorjetas e fechamento financeiro |

## 🧩 Padrões de projeto

### Facade

A classe `RestauranteFacade` centraliza o acesso às funcionalidades do sistema, intermediando a comunicação entre o `MenuConsole` e as classes responsáveis pelos pedidos, cardápio e pagamentos.

**Benefício:** reduz o acoplamento e simplifica a comunicação entre os componentes.

### Factory

A classe `LancheFactory` centraliza a criação dos lanches.

Cada seleção retorna uma nova instância de `Lanche`, permitindo personalizar os ingredientes sem afetar outros pedidos.

**Benefício:** centraliza a criação dos objetos e evita o compartilhamento indevido de estado.

### Decorator

A interface `ItemCardapio` define os métodos:

- `getDescricao()`
- `getPreco()`

A classe abstrata `AdicionalDecorator` mantém uma referência a `ItemCardapio`.

As classes `BaconExtra`, `CheddarExtra` e `MolhoExtra` acrescentam descrição e preço ao item original.

**Benefício:** permite combinar adicionais sem criar uma classe para cada combinação possível.

### Singleton

A classe `Caixa` utiliza o padrão Singleton para garantir uma única instância responsável pelo registro dos pagamentos durante a execução do sistema.

**Benefício:** centraliza o controle financeiro da aplicação.

## 📚 Estruturas de dados

| Estrutura | Utilização |
|---|---|
| `ArrayList<ItemCardapio>` | Armazenamento dos itens de cada pedido |
| `HashMap<Integer, Pedido>` | Armazenamento e busca de pedidos pelo número |
| `HashSet<Transacao>` | Registro de pagamentos sem duplicidades |

A classe `Transacao` implementa `equals()` e `hashCode()` considerando o número do pedido, impedindo que dois pagamentos sejam registrados para o mesmo pedido.

## 💳 Fluxo de pagamento

1. O usuário informa o número do pedido.
2. O sistema verifica se o pedido existe.
3. Verifica se o pedido já foi pago.
4. Apresenta o valor total.
5. Permite informar uma gorjeta opcional.
6. Cria uma `Transacao`.
7. Registra o pagamento no `Caixa`.

O sistema impede pagamentos duplicados e bloqueia modificações em pedidos pagos pelo menu.

## 📊 Fechamento financeiro

O relatório apresenta:

- Total dos pedidos cadastrados.
- Total de vendas pagas.
- Total de pagamentos pendentes.
- Total de gorjetas.
- Total recebido no caixa.

Quando existem pagamentos pendentes, o sistema apresenta um aviso e solicita confirmação antes de encerrar.

## ▶️ Como executar

### Pré-requisitos

- JDK compatível com o projeto.
- Maven.
- IntelliJ IDEA ou outra IDE Java.

Clone o repositório:

```bash
git clone https://github.com/pamelaprpn/sistema-restaurante.git
```

Abra no IntelliJ IDEA a pasta do projeto que contém o arquivo `pom.xml`.

Execute a classe `Main.java` para iniciar o menu interativo.

### Executar os testes

No terminal, dentro da pasta que contém o `pom.xml`:

```bash
mvn clean test
```

## 🧪 Testes automatizados

O projeto utiliza **JUnit 5** para testar regras de negócio, incluindo:

- Criação e seleção de lanches.
- Cálculo do total dos pedidos.
- Aplicação de adicionais.
- Personalização dos ingredientes.
- Gerenciamento de pedidos.
- Cancelamento de pedidos.
- Financeiro - Pendente.

## 💡 Conceitos aplicados

- Encapsulamento
- Abstração
- Interfaces
- Herança
- Polimorfismo
- Composição
- Factory
- Decorator
- Facade
- Singleton
- Java Collections
- `equals()` e `hashCode()`
- Tratamento de exceções
- Separação de responsabilidades
- Testes unitários

## 🎓 Sobre o projeto

Projeto desenvolvido durante o **Bootcamp Java Full Stack ElasTech**, com foco na aplicação prática de conceitos de Programação Orientada a Objetos, organização de código, regras de negócio e padrões de projeto.
