package baozi.store.baozi.controller;

import baozi.store.baozi.model.Cliente;
import baozi.store.baozi.service.ClienteService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.createCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> GetAllCliente() {
        return ResponseEntity.ok(clienteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> GetById(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);

        if (cliente == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void DeleteById(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }

}
