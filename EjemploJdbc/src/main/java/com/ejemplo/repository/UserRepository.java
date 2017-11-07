package com.ejemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.model.User;
import java.lang.String;
import java.util.List;




public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsuario(String usuario);
	

	
	
	

	
	
}
