// 이 클래스는 스프링 부트 프로젝트의 시작점
package otter.sherry.ottershell;
// 이 파일이 어떤 "폴더(패키지)"에 있는지 선언

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Spring Boot에서 제공하는 기능을 사용하기 위해 가져오기(import)
// SpringApplication: 스프링 부트 애플리케이션을 실행시키는 메인 클래스


@SpringBootApplication
// 이 클래스가 스프링 부트 앱의 시작점임을 선언하는 어노테이션
public class OtterShellApplication {
    public static void main(String[] args) {
        SpringApplication.run(OtterShellApplication.class, args);
    }
//Spring Boot 애플리케이션을 실행하겠다.
// = 내장 웹 서버(Tomcat)를 켜고
// = 설정을 로딩하고
// = 컨트롤러 등 모든 Bean을 초기화함
}
