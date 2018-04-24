package group.greenbyte.lunchplanner.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/user", "/login", "/registration").permitAll()
                    .antMatchers("/**").authenticated()
                    .and()
                .formLogin()
                    .defaultSuccessUrl("/event")
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()
                .logout()
                    .logoutSuccessUrl("/login?logout")
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/403")
                    .and()
                .csrf();
    }

}
