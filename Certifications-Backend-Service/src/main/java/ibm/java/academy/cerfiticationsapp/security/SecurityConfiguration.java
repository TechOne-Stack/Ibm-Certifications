package ibm.java.academy.cerfiticationsapp.security;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import ibm.java.academy.cerfiticationsapp.service.UserService;
// import lombok.AllArgsConstructor;

@EnableWebSecurity
@Configuration
// @AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
   
    // private final UserService userService;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/all-users").authenticated()
            .antMatchers("/**").permitAll()
            .and()
            .httpBasic();
        http.headers().frameOptions().disable();
        // http.authorizeRequests()
		// 		.antMatchers("/sign-up/**", "/sign-in/**")
		// 		.permitAll()
		// 		.anyRequest()
		// 		.authenticated()
		// 		.and()
		// 		.formLogin()
		// 		.loginPage("/sign-in")
		// 		.permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
