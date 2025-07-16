package tw.idv.tia203.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.idv.tia203.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("FROM Member m WHERE m.userName = :userName")
	Member getByUserName(String userName);
}
