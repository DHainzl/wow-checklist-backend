package at.dhainzl.spring.wowchecklistbackend;

import at.dhainzl.spring.wowchecklistbackend.configuration.CustomLogoutSuccessHandler;
import at.dhainzl.spring.wowchecklistbackend.configuration.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class WowChecklistBackendApplication extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomSuccessHandler customSuccessHandler;

	@Autowired
	private CustomLogoutSuccessHandler customLogoutHandler;

	private static final String[] UNSECURED_URLS = {
			"/",
			"/login**",
			"/logout**",
			"/redirect**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf()
			.disable()
				.antMatcher("/**")
				.authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.antMatchers(UNSECURED_URLS).permitAll()
					.anyRequest().authenticated()
			.and()
				.oauth2Login()
					.successHandler(customSuccessHandler)
			.and()
				.logout()
					.logoutSuccessHandler(customLogoutHandler);
	}

	public static void main(String[] args) {
		SpringApplication.run(WowChecklistBackendApplication.class, args);
	}
}
