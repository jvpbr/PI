package com.cultivar.armazemNatural.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cultivar.armazemNatural.model.UserLogin;
import com.cultivar.armazemNatural.model.Usuario;
import com.cultivar.armazemNatural.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {
		
		if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			return null;
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			
			return Optional.of(usuarioRepository.save(usuario));	
	}
		
	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByEmail(user.get().getEmail());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);				
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());
				
				return user;
			}
		}
		
		return null;
	
	}	
}