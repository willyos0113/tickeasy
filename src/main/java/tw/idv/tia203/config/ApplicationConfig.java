package tw.idv.tia203.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import tw.idv.tia203.member.dao.MemberRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	@Autowired
	private final MemberRepository repo;

	/**
	 * 用於查找用戶詳細信息
	 * 
	 * @return userName(用戶詳細信息)
	 */
	@Bean
	UserDetailsService userDetailsService() {
		return userName -> repo.getByUserName(userName);
	}

	/**
	 * 執行身份驗證的關鍵組件
	 */
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
	 * 管理身份驗證的組件
	 */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	/**
	 * 以便將用戶密碼進行安全的雜湊存儲
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
