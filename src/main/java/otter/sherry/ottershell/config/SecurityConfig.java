package otter.sherry.ottershell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//나는 스프링 설정 파일이야
@EnableWebSecurity
// 이 프로젝트의 웹 보안을 활성화할게
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //보안 설정을 여기에다가 정의하는데,filterChain() 요청이 들어왔을 때 어떻게 처리할 지 설정하는 함수
        http
                .csrf(csrf -> csrf.disable())
                // csrf 공격방지 기능을 끄는 설정[사이트 간 요청 위조; Cross-Site Request Forgery]
                .authorizeHttpRequests((authorize) -> authorize
                        //특정 url들은 아무나 들어올 수 있게 허용해줘
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/webjars/**",
                                "/signUp"
                        ).permitAll()
                        .anyRequest().authenticated()
                        //나머지 모든 URL요청은 --> 로그인한 사용자만 접근 가능
                )
                .httpBasic(Customizer.withDefaults());
                //→ 팝업으로 아이디/비번 입력하는 방식
                //.formLogin(Customizer.withDefaults());
                //->HTML을 그냥 로그인 폼(하나의 페이지)로 사용

        return http.build();
        //지금까지 설정한 내용을 Spring Security에 등록
    }

    @Bean
    public PasswordEncoder encoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    //비밀번호 암호화에 Argon2 알고리즘(보안성이 높음)을 사용하겠다는 뜻

}
