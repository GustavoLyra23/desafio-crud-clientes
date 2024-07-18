package com.desafiocrud.aula.services;

import com.desafiocrud.aula.dto.ClientDto;
import com.desafiocrud.aula.entities.Client;
import com.desafiocrud.aula.repositories.ClientRepository;
import com.desafiocrud.aula.services.exception.DatabaseException;
import com.desafiocrud.aula.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional()
    public ClientDto insert(ClientDto clientDto) {
        Client client = new Client();
        dtoToEntity(clientDto, client);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    @Transactional()
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return new ClientDto(client);
    }

    @Transactional()
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(ClientDto::new);
    }

    @Transactional()
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found");
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity error");
        }
    }

    @Transactional()
    public ClientDto update(Long id, ClientDto clientDto) {
        try {
            Client entity = clientRepository.getReferenceById(id);
            dtoToEntity(clientDto, entity);
            entity = clientRepository.save(entity);
            return new ClientDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client not found");
        }
    }


    private void dtoToEntity(ClientDto clientDto, Client client) {
        client.setBirthDate(clientDto.getBirthDate());
        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setChildren(clientDto.getChildren());
        client.setIncome(clientDto.getIncome());
    }

}
