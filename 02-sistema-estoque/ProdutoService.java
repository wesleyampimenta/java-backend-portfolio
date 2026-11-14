import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutoService {

    // ============================================
    // Lista privada e imutável para armazenar os produtos
    // ============================================
    private final List<Produto> produtos = new ArrayList<>();


    // ============================================
    // Método para cadastrar produto com validação
    // ============================================
    public void cadastrarProduto(Produto produto) {

        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }

        // Regra de negócio: impede produtos com o mesmo nome
        if (buscarPorNome(produto.getNome()) != null) {
            throw new IllegalArgumentException("Já existe um produto cadastrado com este nome: " + produto.getNome());
        }

        produtos.add(produto);
    }


    // ============================================
    // Método para buscar um produto pelo nome
    // ============================================
    public Produto buscarPorNome(String nome) {

        if (nome == null) {
            return null;
        }

        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome.trim())) {
                return produto; // Retorna o produto encontrado
            }
        }

        return null; // Não encontrou nenhum produto com esse nome
    }


    // ============================================
    // Método para retornar todos os produtos (somente leitura)
    // ============================================
    public List<Produto> listarProdutos() {
        return Collections.unmodifiableList(produtos);
    }


    // ============================================
    // Método para registrar entrada de estoque
    // ============================================
    public boolean adicionarEstoque(String nome, int quantidade) {

        Produto produto = buscarPorNome(nome);

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        return produto.adicionarEstoque(quantidade);
    }


    // ============================================
    // Método para registrar saída de estoque
    // ============================================
    public boolean removerEstoque(String nome, int quantidade) {

        Produto produto = buscarPorNome(nome);

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        return produto.removerEstoque(quantidade);
    }


    // ============================================
    // Método para atualizar os dados de um produto
    // ============================================
    public void atualizarProduto(String nomeAtual, String novoNome, double novoPreco) {

        // 1. Validação defensiva do nome atual
        if (nomeAtual == null || nomeAtual.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome atual do produto não pode ser nulo ou vazio.");
        }

        // 2. Validação defensiva do novo nome
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O novo nome do produto não pode ser nulo ou vazio.");
        }

        Produto produto = buscarPorNome(nomeAtual.trim());

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        String nomeLimpo = novoNome.trim();

        // 3. Regra de unicidade protegida contra null e espaços
        if (!nomeLimpo.equalsIgnoreCase(produto.getNome()) && buscarPorNome(nomeLimpo) != null) {
            throw new IllegalArgumentException("Já existe outro produto cadastrado com o nome: " + nomeLimpo);
        }

        // 4. Valida o preço antes de realizar qualquer alteração
        if (!Double.isFinite(novoPreco) || novoPreco <= 0) {
        throw new IllegalArgumentException("O preço deve ser finito e maior que zero.");
        }
        // 5. Aplica as alterações
        produto.setNome(nomeLimpo);
        produto.setPreco(novoPreco);
    }


    // ============================================
    // Método auxiliar para checar se a lista está vazia
    // ============================================
    public boolean estaVazio() {
        return produtos.isEmpty();
    }

}
