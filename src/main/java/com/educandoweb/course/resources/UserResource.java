package com.educandoweb.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

@RestController                  			 // ESSA ANOTAÇÃO GARANTE QUE ESSA CLASSE É UM RECURSO WEB QUE É IMPLEMENTADO PELO CONTROLADOR REST
@RequestMapping(value = "/users") 			 // ANOTAÇÃO QUE DA O NOME AO RECURSO QUE TBM É O CAMINHO /users
public class UserResource {
	
	@GetMapping                   			 // ESSA ANOTAÇÃO INDICA QUE ESSE METODO RESPONDE A REQUISIÇÃO GET DO HTTP
	public ResponseEntity<User> findAll(){   // METODO QUE RETORNA RESPOSTAS DE REQUISIÇÕES WEB / COM O TIPO DA CLASSE
		User u = new User(1L, "Maria", "maria@gmail.com", "99999999", "12345");
		return ResponseEntity.ok().body(u);  // .OK(RETORNAR RESPOSTA COM SUCESSO) / .BODY(RETONAR O CORPO DA RESPOSTA NO USUARIO U)
	}

}
