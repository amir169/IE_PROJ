//package ir.rendan.config;
//
//
//import ir.rendan.services.MyAppUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.*;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.*;
//
//
///**
// * Created by Amir Shams on 5/1/2017.
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled=true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter  {
//
//    @Autowired
//    MyAppUserDetailsService myAppUserDetailsService;
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .formLogin()
////                .loginPage("/login.html")
////                .failureUrl("/login.html?error")
////                .and()
////                .authorizeRequests()
////                .antMatchers("/login.html"
////                        ,"/blog.html"
////                        ,"/bootstrap/**"
////                        ,"/css/**"
////                        ,"/elements/**"
////                        ,"/fonts/**"
////                        ,"/templates/**").permitAll()
////                .antMatchers("/admin/**").hasRole("ADMIN")
////                .anyRequest().authenticated();
////    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myAppUserDetailsService);
//    }
//
//}
