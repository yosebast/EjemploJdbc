package com.ejemplo.sevice;



import java.util.List;

import com.ejemplo.model.User;


public interface UserService {		
	List<User>  findAllUsuarios();
	
	User findUsuarioById(int id);
}
