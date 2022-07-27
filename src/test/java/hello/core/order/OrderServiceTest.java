package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        // primitive 타입으로 하지 않은 이유 => NULL을 넣을 수 없어서
        // 이후 DB에 NULL이 들어갈 수도 있기 때문에 primitive로 안함
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.CreateOrder(memberId, "itemA", 10000);
        // Assertions는 assertj 라이브러리로 선택
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
