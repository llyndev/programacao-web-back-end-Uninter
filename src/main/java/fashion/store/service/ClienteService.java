package fashion.store.service;

import fashion.store.dto.ClienteResponse;
import fashion.store.model.Cliente;
import fashion.store.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse createCliente(Cliente cliente) {
        Cliente newCliente = new Cliente(cliente.getNome(), LocalDate.now());

        clienteRepository.save(newCliente);

        return new ClienteResponse(newCliente);
    }

    public List<ClienteResponse> getAll() {
        List<Cliente> clientes =  clienteRepository.findAll();

        return clientes
                .stream()
                .map(ClienteResponse::new)
                .toList();
    }

    public ClienteResponse getById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado.")
        );

        return new ClienteResponse(cliente);
    }

    public void deleteCliente(Long id) {
        var cliente = clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado."));

        clienteRepository.deleteById(id);
    }

}
