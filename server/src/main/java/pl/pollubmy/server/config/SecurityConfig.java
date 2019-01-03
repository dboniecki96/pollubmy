package pl.pollubmy.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.pollubmy.server.security.CustomUserDetailsService;
import pl.pollubmy.server.security.JwtAuthenticationEntryPoint;
import pl.pollubmy.server.security.JwtAuthenticationFilter;
import pl.pollubmy.server.security.JwtAuthorizationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService customUserDetailsService;
    private JwtAuthenticationEntryPoint entryPoint;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthenticationEntryPoint entryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.entryPoint = entryPoint;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/register", "/login").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/student/**").access("hasRole('ROLE_USER')")
                .anyRequest().authenticated()
                .and()
            .addFilter(new JwtAuthenticationFilter((authenticationManager())))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), customUserDetailsService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors();
    }
}