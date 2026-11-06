import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;

        ArrayList<Pessoa> pessoas = new ArrayList<>();

        System.out.println("=== SISTEMA DE CADASTRO DE PESSOAS ===");
        System.out.println("Bem-vindo ao nosso sistema!");
    
        while (opcao != 3) {
            // Menu principal
            System.out.println("===== Menu Principal =====");
            System.out.println("1. Cadastrar pessoa");
            System.out.println("2. Listar pessoas");
            System.out.println("3. Sair");

            System.out.print("Escolha uma opção: ");
            String opcaoTexto = scanner.nextLine();
            try {
                opcao = Integer.parseInt(opcaoTexto);
            if (opcao < 1 || opcao > 3) {
                System.out.println("Opção inválida. Digite um número entre 1 e 3.");
                continue; // Volta para o início do loop
            }
            } catch (NumberFormatException erro) {
                System.out.println("Opção inválida. Digite um número entre 1 e 3.");
                continue; // Volta para o início do loop
            }
            if (opcao == 1) {
                // Nome da pessoa
                System.out.println("=== Cadastro de Pessoa ===");
                System.out.print("Digite o nome da pessoa: ");
                String nome = scanner.nextLine();

                // Idade da pessoa
                System.out.print("Digite a idade da pessoa: ");
                String idadeTexto = scanner.nextLine();

                boolean dadosValidos = true;

                // Validar nome
                if (nome.isEmpty()) {
                System.out.println("Digite um nome válido.");
                dadosValidos = false;
                }

                // Converter e validar idade
                int idade = 0;

                try {
                idade = Integer.parseInt(idadeTexto);

                if (idade <= 0) {
                System.out.println("Digite uma idade válida.");
                dadosValidos = false;
                }

                } catch (NumberFormatException erro) {
                System.out.println("Digite uma idade válida.");
                dadosValidos = false;
                }

                // Cadastrar se os dados forem válidos
                if (dadosValidos) {
                Pessoa pessoa = new Pessoa(nome, idade);
                pessoas.add(pessoa);

                System.out.println("Pessoa cadastrada com sucesso!");
                System.out.println("Nome: " + nome);
                System.out.println("Idade: " + idade + " anos");
                }
            }
            if (opcao == 2) {
                System.out.println("=== Lista de Pessoas Cadastradas ===");
                
                    if (pessoas.isEmpty()) {
                        System.out.println("Nenhuma pessoa cadastrada.");
                    } else {    

                    for (int i = 0; i < pessoas.size(); i++) {
                    Pessoa pessoa = pessoas.get(i);

                    System.out.println((i + 1) + ". Nome: " + pessoa.nome);
                    System.out.println("Idade: " + pessoa.idade + " anos");
}
                    }
            
        }
        }
            System.out.println("Saindo do sistema. Até logo!");
            scanner.close();
        
        }
    }
