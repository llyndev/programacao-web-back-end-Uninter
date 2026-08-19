package baozi.store.baozi.dto;

import baozi.store.baozi.model.Cliente;

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

