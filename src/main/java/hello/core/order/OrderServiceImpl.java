package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order CreateOrder(Long memberId, String itemName, int itemPrice) {
        // 회원조회
        Member member = memberRepository.findById(memberId);
        // 할인값적용
        // discountPolicy 인터페이스를 만듦으로써 단일체계원칙을 잘 지켰다. 왜냐? 할인에 대한 변경이 필요하면 할인쪽만 고치면 되잖아.
        // 주문쪽을 고칠 필요가 없자나. 만약 분리하여 인터페이스를 만들지 않았다면, 할인정책 변경시 주문쪽에서도 고쳐야 했다.
        int discountPrice = discountPolicy.disCount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}
