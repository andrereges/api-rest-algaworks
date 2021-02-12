package com.algaworks.crm.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.crm.exceptions.ClientNotFoundException;
import com.algaworks.crm.models.Client;
import com.algaworks.crm.repositories.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientRepository cr;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Client> index() {
		return cr.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@RequestBody Client client) {
		return cr.save(client);
	}

	@GetMapping("/{id}")
	public Client retrieve(@PathVariable long id) throws ClientNotFoundException {
		Optional<Client> client = cr.findById(id);

		if (!client.isPresent())
			throw new ClientNotFoundException("Client not found");

		return client.get();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> updateStudent(@RequestBody Client client, @PathVariable long id) {

		Optional<Client> clientOptional = cr.findById(id);

		if (!clientOptional.isPresent())
			return ResponseEntity.notFound().build();

		client.setId(id);

		cr.save(client);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) {
		cr.deleteById(id);
	}

}
