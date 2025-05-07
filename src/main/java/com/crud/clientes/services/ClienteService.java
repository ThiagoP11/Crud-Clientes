package com.crud.clientes.services;

import com.crud.clientes.dto.ClienteDTO;
import com.crud.clientes.entities.Cliente;
import com.crud.clientes.repositories.ClienteRepository;
import com.crud.clientes.services.exceptions.DatabaseException;
import com.crud.clientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Optional<Cliente> result = repository.findById(id);
        Cliente cliente = result.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));

        ClienteDTO dto = new ClienteDTO(cliente);
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAll(Pageable pageable) {
        Page<Cliente> result = repository.findAll(pageable);
        return result.map(ClienteDTO::new);
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto) {
        Cliente entity = new Cliente();

        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ClienteDTO(entity);
    }

    private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {

        try{
            Cliente entity = repository.getReferenceById(id);

            copyDtoToEntity(dto, entity);

            entity = repository.save(entity);

            return new ClienteDTO(entity);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
