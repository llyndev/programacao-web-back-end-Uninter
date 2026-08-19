package baozi.store.baozi.service;

import baozi.store.baozi.dto.PedidoRequest;
import baozi.store.baozi.dto.PedidoResponse;
import baozi.store.baozi.model.Cliente;
import baozi.store.baozi.model.Pedido;
import baozi.store.baozi.model.Produto;
import baozi.store.baozi.repository.ClienteRepository;
import baozi.store.baozi.repository.PedidoRepository;
import baozi.store.baozi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    public List<PedidoResponse> getAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos
                .stream()
                .map(PedidoResponse::new)
                .toList();
    }

    public PedidoResponse registrarPedido(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Produto produto = produtoRepository.findById(request.produtoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (!produto.getEstoque()) {
            throw new RuntimeException("Produto indisponível no estoque.");
        }

        if (produto.getQuantidade() < request.quantidade()) {
            throw new RuntimeException("Estoque insuficiente");
        }

        if (request.quantidade() < 1) {
            throw new RuntimeException("Quantidade não pode ser menor que 1");
        }

        produtoService.venderProduto(request.produtoId(), request.quantidade());

        Pedido newPedido = new Pedido();
        newPedido.setCliente(cliente);
        newPedido.setProduto(produto);
        newPedido.setQuantidade(request.quantidade());
        newPedido.setStatus(true);

        pedidoRepository.save(newPedido);

        return new PedidoResponse(newPedido);
    }

    public PedidoResponse getById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pedido não encontrado.")
        );

        return new PedidoResponse(pedido);
    }

    public void cancelarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pedido não encontrado.")
        );

        if (!pedido.getStatus()) {
            throw new RuntimeException("O pedido já esta cancelado");
        }

        pedido.setStatus(false);
        pedidoRepository.save(pedido);
    }

}
