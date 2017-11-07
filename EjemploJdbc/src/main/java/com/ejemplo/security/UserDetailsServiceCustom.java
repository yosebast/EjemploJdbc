package com.ejemplo.security;


import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.model.Role;
import com.ejemplo.model.User;
import com.ejemplo.repository.UserRepository;



@Service("DetailsServiceCustom")
public class UserDetailsServiceCustom implements UserDetailsService {

	
	@Autowired	
	private UserRepository  userRepo;
	
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 try {
			 System.out.println("Entra por aqui 6000000000000000");
	            User user = userRepo.findByUsuario(username);
	            if (user == null) {
	              
	                return null;
	            }
	           
	            return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getPassword(), getAuthorities(user));
	        }
	        catch (Exception e){
	            throw new UsernameNotFoundException("User not found");
	        }
	}

	 private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role.getRole());
            authorities.add(grantedAuthority);
        }
    
        return authorities;
    }

}
