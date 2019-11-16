package at.dhainzl.spring.wowchecklistbackend;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableOAuth2Sso
public class WowChecklistBackendApplication extends WebSecurityConfigurerAdapter {
	@Value("${app.allowedCorsOrigins}")
	List<String> allowedCorsOrigins;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.antMatcher("/**")
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/", "/login**").permitAll()
				.anyRequest().authenticated();
	}

	public static void main(String[] args) {
		SpringApplication.run(WowChecklistBackendApplication.class, args);
	}
}
