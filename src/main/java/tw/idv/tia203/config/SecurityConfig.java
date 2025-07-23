package tw.idv.tia203.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer -> AbstractHttpConfigurer.disable())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/error/**", "/api/auth/**")
						.permitAll() // 不需身份驗證即可訪問的 api
						.anyRequest()
						.authenticated()); // 其餘的 api 都需要身份驗證
		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("123456"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
