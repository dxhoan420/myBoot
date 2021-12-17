package academy.kata.myboot.config;

import academy.kata.myboot.config.handler.LoginSuccessHandler;
import academy.kata.myboot.service.RoleService;
import academy.kata.myboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService; // сервис, с помощью которого тащим пользователя
    private LoginSuccessHandler successHandler; // класс, в котором описана логика перенаправления пользователей по ролям
    private RoleService roleService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //стандартная форма логина доступна всем и обрабатывается следующим образом
        http.formLogin().successHandler(successHandler).permitAll();
        //выключаем кроссдоменную безопасность (на этапе обучения неважна)
        http.csrf().disable();
        //ссылка для выхода из учётной записи и страца, на которую попадёт пользователь после
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
        //страницы аутентификаци доступна всем
        http.authorizeRequests().antMatchers("/login").anonymous()
            // защищенные URL
            .antMatchers("/user").access("hasAnyRole('ADMIN', 'USER')")
            .antMatchers("/admin").access("hasRole('ADMIN')").anyRequest().authenticated();
    }

    //TODO: Поменять на bCrypt
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

//    @PostConstruct
//    public void makeAdmin() {
//        userService.findUserByEmail("admin@kata.academy").setAuthorities(roleService.getAllRoles());
//    }
}
