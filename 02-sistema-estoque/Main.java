
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Lista para armazenar os produtos cadastrados
        ArrayList<Produto> produtos = new ArrayList<>();

        // Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Variável para controlar o funcionamento do sistema
        boolean sistemaAtivo = true;

        // Loop principal do sistema
        while (sistemaAtivo) {

            System.out.println("=========================================");
            System.out.println("Bem-vindo ao Sistema de Controle de Estoque!");
            System.out.println("=========================================");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Entrada de estoque");
            System.out.println("4 - Saída de estoque");
            System.out.println("5 - Buscar produto");
            System.out.println("6 - Sair");

            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            // Direciona o usuário para a funcionalidade escolhida
            switch (opcao) {

                case "1":
                    cadastrarProduto(scanner, produtos);
                    break;

                case "2":
                    listarProdutos(produtos);
                    break;

                case "3":
                    entradaEstoque(scanner, produtos);
                    break;

                case "4":
                    saidaEstoque(scanner, produtos);
                    break;

                case "5":
                    buscarProduto(scanner, produtos);
                    break;

                case "6":
                    sistemaAtivo = false;
                    System.out.println("Saindo do sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }

        // Fecha o Scanner ao encerrar o sistema
        scanner.close();
    }


    // ============================================
    // Método para cadastrar um produto
    // ============================================
    public static void cadastrarProduto(
            Scanner scanner, ArrayList<Produto> produtos) {

        // Solicita o nome do produto
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        // Valida o nome
        while (nome.trim().isEmpty()) {

            System.out.println("O nome do produto não pode ser vazio.");
            System.out.print("Digite o nome do produto: ");
            nome = scanner.nextLine();
        }

        // ----------------------------------------
        // Validação do preço do produto
        // ----------------------------------------
        boolean precoValido = false;
        double preco = 0.0;

        while (!precoValido) {

            System.out.print("Digite o preço do produto: ");
            String precoInput = scanner.nextLine();

            try {

                preco = Double.parseDouble(precoInput);

                if (Double.isFinite(preco) && preco > 0) {
                    precoValido = true;
                } else {
                    System.out.println("O preço deve ser maior que zero.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Entrada inválida. Digite um preço válido.");
            }
        }

        // ----------------------------------------
        // Validação da quantidade do produto
        // ----------------------------------------
        boolean quantidadeValida = false;
        int quantidade = 0;

        while (!quantidadeValida) {

            System.out.print("Digite a quantidade do produto: ");
            String quantidadeInput = scanner.nextLine();

            try {

                quantidade = Integer.parseInt(quantidadeInput);

                if (quantidade >= 0) {
                    quantidadeValida = true;
                } else {
                    System.out.println("A quantidade não pode ser negativa.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Entrada inválida. Digite uma quantidade válida.");
            }
        }

        // Cria um novo objeto Produto
        Produto produto = new Produto(nome, preco, quantidade);

        // Adiciona o produto à lista
        produtos.add(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }


    // ============================================
    // Método para listar os produtos cadastrados
    // ============================================
    public static void listarProdutos(ArrayList<Produto> produtos) {

        // Verifica se existem produtos cadastrados
        if (produtos.isEmpty()) {

            System.out.println("Nenhum produto cadastrado.");

        } else {

            System.out.println("========== LISTA DE PRODUTOS ==========");

            // Percorre a lista e apresenta os produtos
            for (Produto produto : produtos) {

                produto.exibirInformacoes();
                System.out.println("---------------------------------------");
            }
        }
    }


    // ============================================
    // Método para localizar um produto pelo nome
    // ============================================
    public static Produto localizarProduto(
            Scanner scanner, ArrayList<Produto> produtos) {

        // Solicita o nome do produto que será procurado
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine().trim();

        // Percorre a lista procurando o produto
        for (Produto produto : produtos) {

            // Compara os nomes ignorando maiúsculas e minúsculas
            if (produto.getNome().equalsIgnoreCase(nome)) {

                return produto;
            }
        }

        // Retorna null quando não encontra o produto
        return null;
    }


    // ============================================
    // Método para validar uma quantidade positiva
    // ============================================
    public static int lerQuantidadePositiva(Scanner scanner) {

        while (true) {

            System.out.print("Digite a quantidade: ");
            String quantidadeInput = scanner.nextLine();

            try {

                int quantidade = Integer.parseInt(quantidadeInput);

                if (quantidade > 0) {

                    return quantidade;

                } else {

                    System.out.println("A quantidade deve ser maior que zero.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }


    // ============================================
    // Método para registrar entrada de estoque
    // ============================================
    public static void entradaEstoque(
            Scanner scanner, ArrayList<Produto> produtos) {

        // Verifica se existem produtos cadastrados
        if (produtos.isEmpty()) {

            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Localiza o produto informado pelo usuário
        Produto produto = localizarProduto(scanner, produtos);

        // Verifica se o produto foi encontrado
        if (produto == null) {

            System.out.println("Produto não encontrado.");
            return;
        }

        // Exibe a quantidade atual
        System.out.println("Estoque atual: " + produto.getQuantidade());

        // Solicita a quantidade que será adicionada
        int quantidadeEntrada = lerQuantidadePositiva(scanner);

        // Adiciona a quantidade ao estoque
        produto.adicionarEstoque(quantidadeEntrada);

        // Exibe o estoque atualizado
        System.out.println("Estoque atualizado: " + produto.getQuantidade());
    }


    // ============================================
    // Método para registrar saída de estoque
    // ============================================
    public static void saidaEstoque(
            Scanner scanner, ArrayList<Produto> produtos) {

        // Verifica se existem produtos cadastrados
        if (produtos.isEmpty()) {

            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Localiza o produto informado pelo usuário
        Produto produto = localizarProduto(scanner, produtos);

        // Verifica se o produto foi encontrado
        if (produto == null) {

            System.out.println("Produto não encontrado.");
            return;
        }

        // Exibe a quantidade disponível
        System.out.println("Estoque atual: " + produto.getQuantidade());

        // Solicita a quantidade que será retirada
        int quantidadeSaida = lerQuantidadePositiva(scanner);

        // Remove a quantidade do estoque
        produto.removerEstoque(quantidadeSaida);

        // Exibe o estoque atualizado
        System.out.println("Estoque atualizado: " + produto.getQuantidade());
    }


    // ============================================
    // Método para buscar e apresentar um produto
    // ============================================
    public static void buscarProduto(
            Scanner scanner, ArrayList<Produto> produtos) {

        // Verifica se existem produtos cadastrados
        if (produtos.isEmpty()) {

            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Localiza o produto informado pelo usuário
        Produto produto = localizarProduto(scanner, produtos);

        // Verifica se o produto foi encontrado
        if (produto == null) {

            System.out.println("Produto não encontrado.");

        } else {

            // Apresenta os dados do produto encontrado
            System.out.println("========== PRODUTO ENCONTRADO ==========");

            produto.exibirInformacoes();

            System.out.println("=========================================");
        }
    }

}
