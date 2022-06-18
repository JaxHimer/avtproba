package com.bus.routes.busroutesapp.config;

import com.bus.routes.busroutesapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.sql.DataSource;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public void setUserService(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpFirewall allowPercent() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                //Доступ для всех пользователей
                //.antMatchers("/registration").not().fullyAuthenticated()

                //Доступ только для пользователей с ролью Администратор
                // .antMatchers("/admin/**").hasRole("ADMIN")

                //.antMatchers("/addproduct").hasRole("ADMIN")
                //Доступ разрешен всем пользователей для указанные url
                .antMatchers("/login", "/registration", "/acceptEmail/**").permitAll()
//              .antMatchers("/conductor/**").hasRole("CONDUCTOR")
//              .antMatchers("/admin/**").hasRole("admin")
//              .antMatchers("/user/**").hasRole("USER")
                .and()
                .authorizeRequests()
                .antMatchers("/resources/**",
                        "/static/**",
                        "/assets/**",
                        "/css/**",
                        "/js/**",
                        "/image/**",
                        "/webjars/**")
                .permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login")

        ;
        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(bCryptPasswordEncoder());
    }
}
