package com.crud.client.controller;

import com.crud.client.model.Client;
import com.crud.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/client"})
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        Optional<Client> client = repository.findById(id);

        if (client != null && client.isPresent()){
            return ResponseEntity.ok().body(client.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cliente não existe");
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Client client) {
        Client result = repository.findCpf(client.getCpf());
        if (result == null) {
            result = repository.save(client);
            return ResponseEntity.ok().body(result.getId());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cpf em uso");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Client client) {
        return repository.findById(id)
                .map(record -> {
                    record.setName(client.getName());
                    record.setDatanasc(client.getDatanasc());
                    record.setCpf(client.getCpf());
                    Client result = repository.findCpf(client.getCpf(), record.getId());
                    if (result == null) {
                        Client updated = repository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cpf em uso");
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("cliente não existe"));
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().body("Registro " + id + " removido com sucesso");
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("cliente não existe"));
    }

    @DeleteMapping(path = {"/all"})
    public ResponseEntity<?> deleteAll() {
        repository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("dados removidos com sucesos");
    }

    @GetMapping(path = {"/findName/{name}"})
    public ResponseEntity findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(repository.findName(name));
    }
}

