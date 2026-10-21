package tamasia.blog.api.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tamasia.blog.api.model.Usuario;
import tamasia.blog.api.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UsuarioRepository usuarioRepository;

	public CustomUserDetailsService(UsuarioRepository usuariioRepository) {
		super();
		this.usuarioRepository = usuariioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Usuario usuario = usuarioRepository.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: "+ username));
		
		return User.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles(usuario.getRole())
				.disabled(!usuario.isEnable())
				.build();
				
	}

}
