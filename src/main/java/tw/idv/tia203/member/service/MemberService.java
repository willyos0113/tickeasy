package tw.idv.tia203.member.service;

import java.util.List;

import tw.idv.tia203.common.entity.Core;
import tw.idv.tia203.member.entity.Member;

public interface MemberService {
	public Core<Member> register(Member reqMember);
	
	public List<Member> findAllMember();
}
