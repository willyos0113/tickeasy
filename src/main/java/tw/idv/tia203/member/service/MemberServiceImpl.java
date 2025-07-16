package tw.idv.tia203.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tw.idv.tia203.common.entity.Core;
import tw.idv.tia203.common.entity.DataStatus;
import tw.idv.tia203.member.dao.MemberRepository;
import tw.idv.tia203.member.entity.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository repo;

	// 將帳密存入 member 資料表中
	@Override
	public Core<Member> register(Member reqMember) {
		var core = new Core<Member>();

		// 驗證 userName 不為空
		if (!StringUtils.hasText(reqMember.getUserName())) {
			core.setDataStatus(DataStatus.INVALID);
			core.setMessage("請輸入帳號");
			core.setSuccessful(false);
			return core;
		}
		// 驗證 password 不為空
		if (!StringUtils.hasText(reqMember.getPassword())) {
			core.setDataStatus(DataStatus.INVALID);
			core.setMessage("請輸入密碼");
			core.setSuccessful(false);
			return core;
		}
		// 驗證 nickName 不為空
		if (!StringUtils.hasText(reqMember.getNickName())) {
			core.setDataStatus(DataStatus.INVALID);
			core.setMessage("請輸入暱稱");
			core.setSuccessful(false);
			return core;
		}
		// 驗證 email 不為空
		if (!StringUtils.hasText(reqMember.getEmail())) {
			core.setDataStatus(DataStatus.INVALID);
			core.setMessage("請輸入信箱");
			core.setSuccessful(false);
			return core;
		}

		// 以 userName 比對既有會員沒有重複

		var member = repo.getByUserName(reqMember.getUserName());
		if (member != null) {
			core.setDataStatus(DataStatus.EXECUTION_FAILED);
			core.setMessage("使用者名稱重複");
			core.setSuccessful(false);
			return core;
		}

		// 存入 member 資料表
		member = repo.save(reqMember);
		core.setDataStatus(DataStatus.EXECUTION_PASSED);
		core.setMessage("新增會員成功");
		core.setData(member);
		core.setSuccessful(true);
		return core;
	}

	// 查詢所有會員
	@Override
	public List<Member> findAllMember() {
		return repo.findAll();
	}
}
