package hello.core.member;

public interface MemberRepository {
    //회원가입, 회원조회
    void save(Member member);

    Member findById(Long memberId);
}
