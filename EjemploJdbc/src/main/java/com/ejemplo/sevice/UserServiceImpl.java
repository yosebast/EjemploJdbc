package com.ejemplo.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.model.User;
import com.ejemplo.repository.UserRepository;

@Service("userService")
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository UserRepo;

	public List<User> findAllUsuarios() {
		// TODO Auto-generated method stub
		return UserRepo.findAll();
	}

	
	@Secured("hasRole('ROLE_ADMIN')")
	public User findUsuarioById(int id) {
		// TODO Auto-generated method stub		
		return UserRepo.findOne(id);
	}
	
	
	
	
	

}
