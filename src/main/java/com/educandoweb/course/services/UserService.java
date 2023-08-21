package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service     // REGISTRA A CLASSE COMO UM COMPONENTE DO SPRING E AI ESSA CLASSE PODERÁ SER INJETADA AUTOMATICAMENTE COM @AUTOWIRED
public class UserService {
	
	@Autowired
	private UserRepository repository;
	public List<User> findAll(){
		return repository.findAll();
		
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); // PEGA O USUARIO, SE NÃO TIVER USUARIO, LANÇA A TRATATIVA COM PARAMETRO O ID
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id); // PEGA UM OBJ E DEIXA DISPONIVEL PRA VC ALTERAR, SEM QUE ALTERE ELE NO BD // PEGA UMA REFERENCIA
		updateDate(entity, obj);
		return repository.save(entity);
	}

	private void updateDate(User entity, User obj) { // METODO PARA ATUALIZAR OS DADOS
		entity.setName(obj.getName());               // LANÇA O NOME NO SETNAME -->> BASEADO NO NOME QUE RECEBEU DO OBJ
		entity.setEmail(obj.getEmail());			 // LANÇA O NOME NO SETEMAIL -->> BASEADO NO NOME QUE RECEBEU DO OBJ
		entity.setPhone(obj.getPhone());		     // LANÇA O NOME NO SETPHONE -->> BASEADO NO NOME QUE RECEBEU DO OBJ
		
	}
}
