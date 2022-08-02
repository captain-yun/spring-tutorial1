package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너, 호출할 때마다 다른 객체를 생성")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 조회: 호출할 때마다 새로운 객체를 생성하는지 조회
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

//        System.out.println("memberService1 = " + memberService1);
//        System.out.println("memberService2 = " + memberService2);

        // 서로 다른 객체이어야 한다.
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        //private으로 생성자를 막아둠. 컴파일 시 컴파일 오류 발생.
        //new SingletonService();

        // 조회: 호출할 때마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        // 참조값이 같은 것을 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너, 호출할 때마다 다른 객체를 생성")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext((AppConfig.class));

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 서로 같은 객체이어야 한다.
        assertThat(memberService1).isSameAs(memberService2);
    }
}
