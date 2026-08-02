package baozi.store.baozi.service;

import baozi.store.baozi.model.Cliente;
import baozi.store.baozi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente createCliente(Cliente cliente) {
        Cliente newCliente = new Cliente(cliente.getNome(), LocalDate.now());
        return clienteRepository.save(newCliente);
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                .orElse(null);
    }

    public void deleteCliente(Long id) {
        var cliente = clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado."));

        clienteRepository.deleteById(id);
    }

}
