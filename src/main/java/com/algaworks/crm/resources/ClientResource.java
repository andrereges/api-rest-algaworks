package com.algaworks.crm.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
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

@ControllerAdvice
@RestController
@RequestMapping("/clients")
public class ClientResource {

	@Autowired
	private ClientRepository cr;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Client> index() {
		return cr.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@Valid @RequestBody Client client) {
		return cr.save(client);
	}

	@GetMapping("/{id}")
	public Client retrieve(@PathVariable long id) {
		Optional<Client> client = cr.findById(id);

		try {
			if (!client.isPresent())
				throw new ClientNotFoundException("Client not found.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return client.get();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> update(@RequestBody Client client, @PathVariable long id) {

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
