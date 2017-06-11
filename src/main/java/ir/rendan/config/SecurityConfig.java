
package ir.rendan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 // * Created by Amir Shams on 5/1/2017.
 // */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login.html?error")
                .and()
                .authorizeRequests()
                .antMatchers("/login.html"
                        ,"/test/**"
                        ,"/api/user/**"
                        ,"/js/login.js"
                        ,"/js/registerModal.js"
                        ,"/blog.html"
                        ,"/bootstrap/**"
                        ,"/css/**"
                        ,"/elements/**"
                        ,"/fonts/**"
                        ,"/templates/**").permitAll()
                .antMatchers("/api/question/reply"
                        ,"/games/**/admin.html").hasAuthority("ADMIN")
                .anyRequest().authenticated();
    }

}
