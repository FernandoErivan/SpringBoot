package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.model.ClienteModel;
import com.example.demo.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class ClienteService {

    private final ClienteRepository clienteRepository;
    
    public ClienteModel createCliente(ClienteModel cliente) throws Exception{
        if(cliente.getUsername().isBlank() || cliente.getPassword().isEmpty())
            throw new Exception("Nome ou senha invalidos");
        return clienteRepository.save(cliente);
    }

    public List<ClienteModel> createClientesList(List<ClienteModel> clientes) throws Exception {
    for (ClienteModel cliente : clientes) {
        if (cliente.getUsername() == null || cliente.getPassword() == null ||
            cliente.getUsername().isBlank() || cliente.getPassword().isEmpty()) {
            throw new Exception("Nome ou senha invalidos em um dos clientes da lista");
        }
    }
    
    return clienteRepository.saveAll(clientes);
}

    public List<ClienteModel> getAllClients() {
        return clienteRepository.findAll();
    }

    public ClienteModel findClienteById(long id) {
        if(clienteRepository.existsById(id))
            return clienteRepository.findById(id).get();
        return null;
    }

    public HttpStatus deleteById(long id) {
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.FORBIDDEN;
    }

}