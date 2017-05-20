package ir.rendan.services;

import ir.rendan.dao.UserDAO;
import ir.rendan.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		UserInfo activeUserInfo = userDAO.getActiveUser(userName);

		if(activeUserInfo == null)
			throw new UsernameNotFoundException("username and password did not matched");

			GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
		return new User(activeUserInfo.getUserName(),
				activeUserInfo.getPassword(), Collections.singletonList(authority));
	}
}
