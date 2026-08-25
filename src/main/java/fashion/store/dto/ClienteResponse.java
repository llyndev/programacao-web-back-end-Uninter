package fashion.store.dto;

import fashion.store.model.Cliente;

import java.time.LocalDate;


public record ClienteResponse(
        String nome,
        LocalDate clienteDesde
)
{
    public ClienteResponse(Cliente cliente) {
        this(cliente.getNome(), cliente.getClienteDesde());
    }
}

