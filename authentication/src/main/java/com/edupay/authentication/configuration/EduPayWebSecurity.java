package com.edupay.authentication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.edupay.authentication.data.repository.EduPayUserServiceImpl;
import com.edupay.authentication.utility.EduPayAuthenticationConstants;
import com.edupay.authentication.utility.EduPayJWTAuthenticationFilter;
import com.edupay.authentication.utility.EduPayJWTAuthorizationFilter;

@EnableWebSecurity
public class EduPayWebSecurity extends WebSecurityConfigurerAdapter {
	    private EduPayUserServiceImpl eduPayUserServiceImpl;
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    public EduPayWebSecurity(EduPayUserServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.eduPayUserServiceImpl = userService;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().authorizeRequests()
	                .antMatchers(HttpMethod.POST, EduPayAuthenticationConstants.SIGN_UP_URL).permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .addFilter(new EduPayJWTAuthenticationFilter(authenticationManager()))
	                .addFilter(new EduPayJWTAuthorizationFilter(authenticationManager()))
	                // this disables session creation on Spring Security
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    }

	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(eduPayUserServiceImpl).passwordEncoder(bCryptPasswordEncoder);
	    }

	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

	        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
	        source.registerCorsConfiguration("/**", corsConfiguration);

	        return source;
	    }
}
