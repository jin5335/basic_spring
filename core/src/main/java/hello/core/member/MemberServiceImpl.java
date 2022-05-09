package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

//    // 추상화와 구현체 둘 다에 의존하고 있다. (DIP 위반) -> 매우 안좋음 -> 변경에 닫혀 있음.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private MemberRepository memberRepository;

    // 생성자 주입
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
