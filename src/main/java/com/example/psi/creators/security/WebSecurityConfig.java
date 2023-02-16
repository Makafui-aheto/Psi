package com.example.psi.creators.security;

import com.example.psi.commons.security.AuthEntryPointJwt;
import com.example.psi.creators.service.AuthorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthorDetailsService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthorTokenFilter authenticationJwtTokenFilter() {
        return new AuthorTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/swagger-ui/**", "/v2/api-docs",
                "/configuration/ui",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/premium/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/premium/**").hasAuthority("create")
                .antMatchers("/api/v1/permission/add-role").hasAnyAuthority("edit", "create")
                .antMatchers("/api/v1/group/setup").hasAuthority("create")
                .antMatchers(HttpMethod.GET,"/api/v1/membership/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/membership/**").hasAuthority("create")
                .antMatchers(HttpMethod.PUT, "/api/v1/membership/**").hasAuthority("edit")
                .antMatchers(HttpMethod.POST, "/api/v1/group/**").hasAuthority("create")
                .antMatchers(HttpMethod.GET, "/api/v1/group/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/group/**").hasAuthority("delete")
                .antMatchers(HttpMethod.PUT, "/api/v1/group/**").hasAuthority("edit")
                .antMatchers("/api/v1/mortality/**").hasAnyAuthority("create", "edit")
                .antMatchers("/api/v1/company/**").hasAnyAuthority("create", "edit", "read")
                .antMatchers("/api/v1/payroll/calculator").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/claims/**").hasAuthority("read")
                .antMatchers(HttpMethod.POST, "/api/v1/claims/**").hasAnyAuthority("create", "edit")
                .antMatchers(HttpMethod.GET, "/api/v1/staff/**").hasAuthority("read")
                .antMatchers(HttpMethod.POST, "/api/v1/staff/**").hasAnyAuthority("edit", "create")
                .antMatchers("/api/v1/server/**").hasAnyAuthority("edit","create")
                .anyRequest().authenticated();

//         http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
