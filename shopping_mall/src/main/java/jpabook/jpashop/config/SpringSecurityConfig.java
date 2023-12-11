//package jpabook.jpashop.config;
//
//import jakarta.servlet.DispatcherType;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableMethodSecurity
//public class SpringSecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(request -> request
//                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        .requestMatchers("/css/**","/bootstrap-4.3.1-dist/**","/js/**","/images/**", "/members/new","/").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(login -> login
//                        .loginPage("/members/login")  // [A] 커스텀 로그인 페이지 지정
//                        .loginProcessingUrl("/members/login")  // [B] submit 받을 url
//                        .usernameParameter("user-id")  // [C] submit할 아이디
//                        .passwordParameter("password")  // [D] submit할 비밀번호
//                        .defaultSuccessUrl("/", true)
//                        .permitAll()
//                )
//                .logout(withDefaults());
//
//        return http.build();
//    }
//}