
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
            System.out.println("6 - Editar produto");
            System.out.println("7 - Sair");

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
                    editarProduto(scanner, produtos);
                    break;

                case "7":
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

        try {
            // Cria um novo objeto Produto
            Produto produto = new Produto(nome, preco, quantidade);

            // Adiciona o produto à lista
            produtos.add(produto);

            System.out.println("Produto cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {

            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
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


    // ============================================
    // Método para editar os dados de um produto
    // ============================================
    public static void editarProduto(
            Scanner scanner, ArrayList<Produto> produtos) {

        // 1. Verifica se existem produtos cadastrados
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // 2. Localiza o produto informado pelo usuário
        Produto produto = localizarProduto(scanner, produtos);

        // Verifica se o produto foi encontrado
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("\nEditando o produto: " + produto.getNome());
        System.out.println("(Pressione [Enter] sem digitar nada para manter o valor atual)\n");

        // Guardar os valores atuais do produto como padrão inicial
        String nomeAtualizado = produto.getNome();
        double precoAtualizado = produto.getPreco();

        // ----------------------------------------
        // 3. Edição do Nome
        // ----------------------------------------
        System.out.print("Novo nome [" + produto.getNome() + "]: ");
        String novoNome = scanner.nextLine().trim();

        if (!novoNome.isEmpty()) {
            nomeAtualizado = novoNome;
        }

        // ----------------------------------------
        // 4. Edição do Preço
        // ----------------------------------------
        boolean precoValido = false;

        while (!precoValido) {
            System.out.print("Novo preço [R$ " + produto.getPreco() + "]: ");
            String precoInput = scanner.nextLine().trim();

            // Se o usuário apenas der Enter, mantemos o preço atual
            if (precoInput.isEmpty()) {
                precoValido = true;
            } else {
                try {
                    double novoPreco = Double.parseDouble(precoInput);

                    if (Double.isFinite(novoPreco) && novoPreco > 0) {
                        precoAtualizado = novoPreco;
                        precoValido = true;
                    } else {
                        System.out.println("O preço deve ser maior que zero.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um preço válido ou dê Enter para manter.");
                }
            }
        }

        // -----------------------------------------------
        // Atualização somente após a validação das informações
        // -----------------------------------------------
        try {
            produto.setNome(nomeAtualizado);
            produto.setPreco(precoAtualizado);
            System.out.println("\nProduto atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nNão foi possível atualizar o produto: " + e.getMessage());
        }

        // 5. Exibe os dados finais atualizados
        System.out.println("--- Dados do Produto ---");
        produto.exibirInformacoes();
        System.out.println("------------------------");
    }

}
