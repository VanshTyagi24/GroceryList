package ca.sheridancollege.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

@Autowired
UserDetailsServiceImpl userDetailsService;
@Bean
public BCryptPasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
	
}
@Autowired
private LoginAccessDeniedHandler accessdeniedhandler;
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/member/**").hasAnyRole("MEMBER","ADMIN")
		.antMatchers("/guest/**").hasAnyRole("GUEST","ADMIN")
		.antMatchers("/create/**").hasRole("ADMIN")
		.antMatchers("/edit/**").hasRole("ADMIN")
		.antMatchers("/MemberCheckout/**").hasAnyRole("MEMBER","ADMIN")
		.antMatchers("/delete/**").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.POST,"/register").permitAll()
		.antMatchers(
				"/",
				"/js/**",
				"/css/**",
				"/images/**",
		         "/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedHandler(accessdeniedhandler)
		.and()
		.headers().disable()
		.csrf().disable()
		
		
		;
		
	
		
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
		
		auth.userDetailsService(userDetailsService).
		passwordEncoder(passwordEncoder());
	}

}
