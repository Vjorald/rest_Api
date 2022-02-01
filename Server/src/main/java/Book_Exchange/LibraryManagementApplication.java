package Book_Exchange;

import org.salespointframework.EnableSalespoint;
import org.salespointframework.SalespointSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSalespoint
@SpringBootApplication
@EnableSwagger2
public class LibraryManagementApplication {

	private static final String LOGIN_ROUTE = "/login";

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

/*
	@Configuration
	static class PrototypeWebConfiguration implements WebMvcConfigurer {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController(LOGIN_ROUTE);
		}
	}
*/

	@Configuration
	static class WebSecurityConfiguration extends SalespointSecurityConfiguration {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();  // for lab purposes, that's ok!
			http.authorizeRequests().antMatchers("/**").permitAll().and().
					formLogin().loginProcessingUrl(LOGIN_ROUTE).and()
					.logout().logoutUrl("/logout").logoutSuccessUrl("/loggedOut");
		}


	}
}
