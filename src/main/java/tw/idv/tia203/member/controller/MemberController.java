package tw.idv.tia203.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tia203.common.entity.Core;
import tw.idv.tia203.common.entity.DataStatus;
import tw.idv.tia203.member.entity.Member;
import tw.idv.tia203.member.service.MemberService;

@RestController
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService service;

	// 將帳密存入 member 資料表中
	@PostMapping("register")
	public Core<Member> register(@RequestBody Member reqMember) {
		var core = new Core<Member>();

		if (reqMember == null) {
			core.setDataStatus(DataStatus.NOT_FOUND);
			core.setMessage("沒有接收到資料");
			core.setSuccessful(false);
			return core;
		}

		return service.register(reqMember);
	}

	// 查詢所有會員
	@GetMapping
	public List<Member> findAllMember() {
		return service.findAllMember();
	}
}
