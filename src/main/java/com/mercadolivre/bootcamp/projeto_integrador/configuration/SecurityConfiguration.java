package com.mercadolivre.bootcamp.projeto_integrador.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @EnableWebSecurity
    @Configuration
    @RequiredArgsConstructor
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

        private final AutenticacaoService autenticacaoService;

        private final TokenService tokenService;

        private final UserRepository repository;

        //autenticacao
        @Override
        @Bean
        protected AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }

        //autorizacao
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/anuncios").permitAll()
                    .antMatchers(HttpMethod.POST, "/auth").permitAll()
                    .antMatchers(HttpMethod.GET, "/vendas").hasAnyAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and().csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, repository), UsernamePasswordAuthenticationFilter.class);
        }

        //autenticacao
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            auth.userDetailsService(autenticacaoService).passwordEncoder(encoder);
        }
    }
}
