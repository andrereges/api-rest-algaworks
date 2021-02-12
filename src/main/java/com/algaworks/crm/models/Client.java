package com.algaworks.crm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="O nome não pode ser nulo")
	@NotEmpty(message="O nome não pode ser vazio")
	@Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
	@Size(max = 80, message = "O nome deve ter no máximo 80 caracteres")
	@Column(name = "name", nullable = false, length = 80, unique = true)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
