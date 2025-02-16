package com.josehumberto.testeDev.Service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.josehumberto.testeDev.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository; 

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	userRepository.findByUsername(username)
        	.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    	
    	// Criando um usuário com username, senha e permissões (roles)
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername("admin")
                .password("password") // A senha deve estar criptografada em um caso real
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))) // Define permissões
                .build();
        
        return userDetails;
    }
}