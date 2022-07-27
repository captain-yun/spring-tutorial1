package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    int disCount(Member member, int price);
}
