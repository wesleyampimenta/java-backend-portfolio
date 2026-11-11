# Sistema de Controle de Produtos e Estoque

## Sobre o projeto

O **Sistema de Controle de Produtos e Estoque** é uma aplicação desenvolvida em Java para realizar o cadastro e o gerenciamento básico de produtos de um estoque.

O projeto foi desenvolvido com foco na prática de conceitos fundamentais de programação e orientação a objetos, utilizando uma aplicação executada diretamente pelo terminal.

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

- **Cadastrar produtos:** permite registrar produtos informando nome, preço e quantidade inicial.
- **Listar produtos:** apresenta todos os produtos cadastrados.
- **Entrada de estoque:** permite adicionar unidades ao estoque de um produto.
- **Saída de estoque:** permite retirar unidades do estoque, respeitando a quantidade disponível.
- **Buscar produtos:** localiza produtos pelo nome.
- **Validação de dados:** verifica informações inválidas, como nomes vazios, preços inválidos e quantidades negativas.

## Tecnologias utilizadas

- Java
- JDK
- Visual Studio Code

## Conceitos de programação aplicados

Durante o desenvolvimento foram utilizados conceitos importantes da linguagem Java:

- Programação Orientada a Objetos (POO).
- Classes, objetos e construtores.
- Encapsulamento.
- Métodos Getters e Setters.
- Estruturas condicionais (`if` e `switch`).
- Estruturas de repetição (`while` e `for`).
- Listas dinâmicas com `ArrayList`.
- Tratamento de exceções com `try/catch`.
- Validação de entradas do usuário.
- Organização do código em métodos.

## Estrutura do projeto

```text
02-sistema-estoque/
│
├── Main.java
└── Produto.java
```

**Main.java:** contém o menu principal, a interação com o usuário e as operações de gerenciamento do estoque.

**Produto.java:** representa os produtos cadastrados, seus atributos e as operações de entrada e saída de estoque.

## Como executar o projeto

É necessário ter o JDK instalado na máquina.

1. Clone o repositório:

```bash
git clone https://github.com/wesleyampimenta/java-backend-portfolio.git
```

2. Acesse a pasta do projeto:

```bash
cd java-backend-portfolio/02-sistema-estoque
```

3. Compile os arquivos Java:

```bash
javac -encoding UTF-8 Main.java Produto.java
```

4. Execute a aplicação:

```bash
java Main
```

## Observação

Atualmente, os produtos são armazenados em memória utilizando `ArrayList`. Isso significa que os dados cadastrados são perdidos quando a aplicação é encerrada.

A persistência de dados em arquivos ou banco de dados poderá ser implementada em versões