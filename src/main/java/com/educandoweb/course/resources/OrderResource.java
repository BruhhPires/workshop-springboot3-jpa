package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

@RestController                  			 // ESSA ANOTAÇÃO GARANTE QUE ESSA CLASSE É UM RECURSO WEB QUE É IMPLEMENTADO PELO CONTROLADOR REST
@RequestMapping(value = "/orders") 			 // ANOTAÇÃO QUE DA O NOME AO RECURSO QUE TBM É O CAMINHO /orders
public class OrderResource {
	
	@Autowired								 // ANOTAÇÃO USADA PARA INJETAR DEPENDENCIA AUTOMATICAMENTE
	private OrderService service;
	
	@GetMapping                   			        // ESSA ANOTAÇÃO INDICA QUE ESSE METODO RESPONDE A REQUISIÇÃO GET DO HTTP
	public ResponseEntity<List<Order>> findAll(){   // METODO QUE RETORNA RESPOSTAS DE REQUISIÇÕES WEB / COM O TIPO DA CLASSE
		List<Order> list = service.findAll();       // AGORA USAREMOS O METODO DO USERRESOURCE
		return ResponseEntity.ok().body(list);      // .OK(RETORNAR RESPOSTA COM SUCESSO) / .BODY(RETONAR O CORPO DA RESPOSTA NO USUARIO U)
	}
	
	@GetMapping(value = "/{id}")                                  // REQUISIÇÃO DO TIPO GET E JÁ PASSAO VALOR DO TIPO ID
	public ResponseEntity<Order> findById(@PathVariable Long id){  // AI ELE RECEBE O PARAMETRO LONG ID, E A ANOTAÇÃO @PATHVARIABLE PRA SER ACEITO COMO PARAMETRO
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
