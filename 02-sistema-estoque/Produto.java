
public class Produto {

    // ============================================
    // Atributos privados da classe
    // ============================================

    // Nome do produto
    private String nome;

    // Preço unitário do produto
    private double preco;

    // Quantidade disponível no estoque
    private int quantidade;


    // ============================================
    // Construtor da classe
    // ============================================

    // Recebe os dados e utiliza os setters para validar
    // as informações antes de armazená-las
    public Produto(String nome, double preco, int quantidade) {

        setNome(nome);
        setPreco(preco);
        setQuantidade(quantidade);
    }


    // ============================================
    // Métodos Getters
    // ============================================

    // Retorna o nome do produto
    public String getNome() {
        return nome;
    }

    // Retorna o preço do produto
    public double getPreco() {
        return preco;
    }

    // Retorna a quantidade disponível
    public int getQuantidade() {
        return quantidade;
    }


    // ============================================
    // Métodos Setters com Exceções de Domínio
    // ============================================

    // Define o nome do produto
    public void setNome(String nome) {

        // Verifica se o nome não é nulo ou vazio
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio.");
        }

        this.nome = nome.trim();
    }

    // Define o preço do produto
    public void setPreco(double preco) {

        // O preço deve ser finito e maior que zero
        if (!Double.isFinite(preco) || preco <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }

        this.preco = preco;
    }

    // Define a quantidade do produto
    public void setQuantidade(int quantidade) {

        // A quantidade não pode ser negativa
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade do produto não pode ser negativa.");
        }

        this.quantidade = quantidade;
    }


    // ============================================
    // Método para adicionar estoque
    // ============================================

    public boolean adicionarEstoque(int quantidadeEntrada) {

        // Verifica se a quantidade informada é positiva e não estoura o limite
        if (quantidadeEntrada <= 0 || quantidadeEntrada > Integer.MAX_VALUE - quantidade) {
            return false;
        }

        // Adiciona a quantidade ao estoque atual
        quantidade += quantidadeEntrada;
        return true;
    }


    // ============================================
    // Método para remover estoque
    // ============================================

    public boolean removerEstoque(int quantidadeSaida) {

        // Verifica se a quantidade informada é positiva e se há estoque suficiente
        if (quantidadeSaida <= 0 || quantidadeSaida > quantidade) {
            return false;
        }

        // Subtrai a quantidade do estoque atual
        quantidade -= quantidadeSaida;
        return true;
    }


    // ============================================
    // Método para apresentar os dados do produto
    // ============================================

    public void exibirInformacoes() {

        System.out.println("Nome: " + nome);
        System.out.println("Preço: R$ " + preco);
        System.out.println("Quantidade: " + quantidade);
    }

}
