package fashion.store.controller;

import fashion.store.dto.ClienteResponse;
import fashion.store.model.Cliente;
import fashion.store.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.createCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getAllCliente() {
        return ResponseEntity.ok(clienteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().build();
    }

}
