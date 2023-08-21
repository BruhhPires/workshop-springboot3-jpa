package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

import jakarta.servlet.Servlet;

@RestController                  			 // ESSA ANOTAÇÃO GARANTE QUE ESSA CLASSE É UM RECURSO WEB QUE É IMPLEMENTADO PELO CONTROLADOR REST
@RequestMapping(value = "/users") 			 // ANOTAÇÃO QUE DA O NOME AO RECURSO QUE TBM É O CAMINHO /users
public class UserResource {
	
	@Autowired								 // ANOTAÇÃO USADA PARA INJETAR DEPENDENCIA AUTOMATICAMENTE
	private UserService service;
	
	@GetMapping                   			       // ESSA ANOTAÇÃO INDICA QUE ESSE METODO RESPONDE A REQUISIÇÃO GET DO HTTP
	public ResponseEntity<List<User>> findAll(){   // METODO QUE RETORNA RESPOSTAS DE REQUISIÇÕES WEB / COM O TIPO DA CLASSE
		List<User> list = service.findAll();       // AGORA USAREMOS O METODO DO USERRESOURCE
		return ResponseEntity.ok().body(list);     // .OK(RETORNAR RESPOSTA COM SUCESSO) / .BODY(RETONAR O CORPO DA RESPOSTA NO USUARIO U)
	}
	
	@GetMapping(value = "/{id}")                                  // REQUISIÇÃO DO TIPO GET E JÁ PASSAO VALOR DO TIPO ID
	public ResponseEntity<User> findById(@PathVariable Long id){  // AI ELE RECEBE O PARAMETRO LONG ID, E A ANOTAÇÃO @PATHVARIABLE PRA SER ACEITO COMO PARAMETRO
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> inser(@RequestBody User obj){  //@requestBody TRANSFORMA O OBJETO JSON A OBJETO USER
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // METODO QUE MONTA A URL DO NOVO USUARIO E RETORNA PRA URI
		return ResponseEntity.created(uri).body(obj);
	}
}
