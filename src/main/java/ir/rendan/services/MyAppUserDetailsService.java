
package ir.rendan.services;

import ir.rendan.repository.UserRepository;
import ir.rendan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyAppUserDetailsService implements UserDetailsService {

	private final UserRepository repository;
	
	public MyAppUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User activeUserInfo = repository.getActiveUser(userName);

		if(activeUserInfo == null)
			throw new UsernameNotFoundException("username and password did not matched");

			GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());

		return new org.springframework.security.core.userdetails.User(activeUserInfo.getUsername(),
				activeUserInfo.getPassword(), Collections.singletonList(authority));
	}
}

