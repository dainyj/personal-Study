package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.member.MemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는
//별도의 설정 클래스
@Configuration // 스프링에서 설정정보
public class AppConfig { //애플리케이션 전체 환경설정하고 구성하는 클래스
    //객체 생성과 연결 담당

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    @Bean
    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());

        //1번
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
        // 생성자를 통해서 구현체에 뭐가 들어길지 정한다.
    }

    @Bean
    public OrderService orderService() {

        //1번
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
//                new MemoryMemberRepository(),
                memberRepository(),
//                new FixDiscountPolicy()
                discountPolicy()
        );

    }

    @Bean
    public MemberRepository memberRepository() {

        //2번?? 3번??
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 할일 정책 변경 (Fix -> Rate)
    }
}
//AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
//MemberServiceImpl
//MemoryMemberRepository
//OrderServiceImpl
//FixDiscountPolicy

//AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
//MemberServiceImpl -> MemoryMemberRepository
//OrderServiceImpl -> MemoryMemberRepository , FixDiscountPolicy