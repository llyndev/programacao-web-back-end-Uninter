package baozi.store.baozi.service;

import baozi.store.baozi.model.Produto;
import baozi.store.baozi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public Produto getById(Long id) {
        return produtoRepository.findById(id)
                .orElse(null);
    }

    public Produto addProduto(Produto produto) {

        if (produto.getNome() == null) {
            throw new RuntimeException("Nome do produto é obrigatório");
        }

        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço do produto não pode ser vazio ou menor que 1");
        }

        produto.setEstoque(produto.getQuantidade() > 0);

        return produtoRepository.save(produto);
    }

    @Transactional
    public void venderProduto(Long id, Integer quantidade) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não existe."));

        if (!produto.getEstoque()) {
            throw new RuntimeException("Produto sem estoque.");
        }

        if (produto.getQuantidade() < quantidade ) {
            throw new RuntimeException("Quantidade em estoque insuficiente.");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);

        if (produto.getQuantidade() <= 0) {
            produto.deactiveEstoque();
        }
    }

}
