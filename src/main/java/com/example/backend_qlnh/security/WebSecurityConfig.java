package com.example.backend_qlnh.security;
import com.example.backend_qlnh.security.jwt.AuthEntryPointJwt;
import com.example.backend_qlnh.security.jwt.AuthTokenFilter;
import com.example.backend_qlnh.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
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
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/phieuhen/**").permitAll()
                .antMatchers("/danhgia/**").permitAll()
                .antMatchers("/sanhtiec/**").permitAll()
                .antMatchers("/khuyenmai/**").permitAll()
                .antMatchers("/loaihinhsukien/**").permitAll()
                .antMatchers("/loainhanvien/**").permitAll()
                .antMatchers("/dichvu/**").permitAll()
                .antMatchers("/hinhanh/**").permitAll()
                .antMatchers("/hoadon/**").permitAll()
                .antMatchers("/monan/**").permitAll()
                .antMatchers("/nguyenlieu/**").permitAll()
                .antMatchers("/nhahang/**").permitAll()
                .antMatchers("/phieudattiec/**").permitAll()
                .antMatchers("/taisan/**").permitAll()
                .antMatchers("/thucdon/**").permitAll()
                .antMatchers("/vaitro/**").permitAll()
                .antMatchers("/nhanvien/**").permitAll()
                .antMatchers("/khachhang/**").permitAll()
                .antMatchers("/chitiethoadon/**").permitAll()
                .antMatchers("/tapdichvu/**").permitAll()

                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
