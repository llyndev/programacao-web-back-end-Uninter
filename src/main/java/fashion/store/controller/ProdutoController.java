package fashion.store.controller;

import fashion.store.model.Produto;
import fashion.store.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) { this.produtoService = produtoService; }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Produto produto = produtoService.getById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.addProduto(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);

        return ResponseEntity.ok().build();
    }
}
