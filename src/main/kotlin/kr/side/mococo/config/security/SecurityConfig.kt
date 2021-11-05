package kr.side.mococo.config.security

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import javax.servlet.http.HttpServletResponse

object Role {
    const val User = "USER"
    const val Admin = "ADMIN"
}

@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
                .antMatchers("/api")
                    .hasRole(Role.User)
                .antMatchers("/api/admin")
                    .hasRole(Role.Admin)
                .antMatchers("/api/login", "/api/logout")
                    .anonymous()
                .anyRequest()
                .permitAll()
            .and()
                .csrf()
            .and()
                .formLogin()
                    .disable()
                .logout()
                    .clearAuthentication(true)
                .permitAll()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint { _, response, _ ->
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                }
    }
}