package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity													  // @Entity É UTILIZADO PARA INFORMAR QUE A CLASSE TAMBÉM É UMA ENTIDADE
@Table(name = "tb_order")								  // O NOME DA CLASSE ORDER É RESERVADO NO SQL, ENTÃO ESSA ANOTAÇÃO EVITA ISSO MUDANDO O NOME LÁ
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id                                                   // GARANTE QUE O ID SEJA A CHAVE PRIMARIA
	@GeneratedValue(strategy = GenerationType.IDENTITY)   // GARANTE QUE A CHAVE SEJA AUTOINCREMENTAVEL
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy:MM:dd'T'HH:mm:ss'Z'", timezone = "GMT") // FORMATO DO INSTANT
	private Instant moment;
	
	private Integer orderStatus;

	@ManyToOne											  //ANOTATION QUE DIZ QUE ESSA RELAÇÃO É "MUITOS PRA UM"
	@JoinColumn(name = "cliente_id")					  //CRIA A COLUNA LA NO BANCO, E JÁ DA O NOME PRA ESSA COLUNA
	private User client;                                  // ORDEM TEM UM CLIENTE OU "MUITOS PARA UM"
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // NO ONETOONE, É NECESSARIO COLOCAR O CASCADE PARA TER O MESMO ID PAGAMENTO E PEDIDO
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment,OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
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

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public double getTotal() {
		double sum = 0.0;
		for(OrderItem x: items) {
			sum += x.getSubTotal();
		}
		return sum;
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
