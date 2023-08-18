package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity													  // @Entity É UTILIZADO PARA INFORMAR QUE A CLASSE TAMBÉM É UMA ENTIDADE
@Table(name = "tb_order")								  // O NOME DA CLASSE ORDER É RESERVADO NO SQL, ENTÃO ESSA ANOTAÇÃO EVITA ISSO MUDANDO O NOME LÁ
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id                                                   // GARANTE QUE O ID SEJA A CHAVE PRIMARIA
	@GeneratedValue(strategy = GenerationType.IDENTITY)   // GARANTE QUE A CHAVE SEJA AUTOINCREMENTAVEL
	private Long id;
	private Instant moment;

	@ManyToOne											  //ANOTATION QUE DIZ QUE ESSA RELAÇÃO É "MUITOS PRA UM"
	@JoinColumn(name = "cliente_id")					  //CRIA A COLUNA LA NO BANCO, E JÁ DA O NOME PRA ESSA COLUNA
	private User client;                                  // ORDEM TEM UM CLIENTE OU "MUITOS PARA UM"
	
	public Order() {
	}

	public Order(Long id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
}
