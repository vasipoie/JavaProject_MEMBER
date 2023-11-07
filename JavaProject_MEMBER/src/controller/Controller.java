package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;
import service.AdminService;
import service.MemberService;
import util.ScanUtil;
import util.View;
import vo.Admin;
import vo.Member;

public class Controller extends Print {
	// 세션
	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	AdminService adService = AdminService.getInstance();
	MemberService mbService = MemberService.getInstance();
	
	public static void main(String[] args) {
		new Controller().start();
	}

	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case ADMIN:
				view = admin();
				break;
			case ADMIN_LOGIN:
				view = adminLogin();
				break;
			case ADMIN_HOME:
				view = adminHome();
				break;
			case MEMBER:
				view = memberHome();
				break;	
			case MEMBER_JOIN:
				view = memberJoin();
				break;	
			case MEMBER_UPDATE:
				view = memberUpdate();
				break;	
			case MEMBER_OUT:
				view = memberOut();
				break;	
			case MEMBER_LOGIN:
				view = memberLogin();
				break;	
			}
		}
	}

	private View memberOut() {
		Member mb = (Member) sessionStorage.get("member");
		if(mb == null) {
			sessionStorage.put("view", View.MEMBER_OUT);
			return View.MEMBER_LOGIN;
		}
		String syn = ScanUtil.nextLine(mb.getName()+ "님 회원 탈퇴하시겠습니까? y/n");
		if(syn.equalsIgnoreCase("y")) {
			List<Object> param = new ArrayList();
			param.add(mb.getNo());
			mbService.delete(param);
		}
		
		return View.MEMBER;
	}

	private View memberLogin() {
		List<Object> param = new ArrayList<Object>();
		System.out.println("----- 로그인 -------- ");
		param.add(ScanUtil.nextLine("id>>"));
		param.add(ScanUtil.nextLine("pass>>"));
		Member mb = mbService.login(param);
		if(mb == null) {
			System.out.println("회원 정보가 없습니다.");
			String selyn = ScanUtil.nextLine("회원가입 하시겠습니까(y/n)");
			if(selyn.equalsIgnoreCase("y")) {
				return View.MEMBER_JOIN;
			}else {
				return View.MEMBER;
			}
		}
		sessionStorage.put("member",mb);
		View v = (View) sessionStorage.get("view");
		return v;
		
	}
	
	private View memberUpdate() {
		Member mb = (Member) sessionStorage.get("member");
		if(mb == null) {
			sessionStorage.put("view", View.MEMBER_UPDATE);
			return View.MEMBER_LOGIN;
		}
		printMbUpdate(mb);
		String syn = ScanUtil.nextLine("수정하시겠습니까 y/n");
		if(syn.equalsIgnoreCase("y")) {
			printMBUpdateDetail();
			int select = ScanUtil.nextInt("수정 정보를 선택하세요");
			List<Object> param = new ArrayList<>();
			if(select == 1 || select == 5) {
				param.add(ScanUtil.nextLine("pass>>"));
			}
			if(select == 2 || select == 5) {
				param.add(ScanUtil.nextLine("name>>"));
			}
			if(select == 3 || select == 5) {
				param.add(ScanUtil.nextLine("age>>"));
			}
			if(select == 4 || select == 5) {
				param.add(ScanUtil.nextLine("room>>"));
			}
			param.add(mb.getId());
			param.add(mb.getPass());
			mbService.update(param, select);
		}
		return View.MEMBER;
		
	}



	private View memberJoin() {
		List<Object> param = new ArrayList<Object>();
		param.add(ScanUtil.nextLine("id>>"));
		param.add(ScanUtil.nextLine("pass>>"));
		param.add(ScanUtil.nextLine("name>>"));
		param.add(ScanUtil.nextLine("age>>"));
		boolean chk = mbService.sign(param);
		if(chk) {
			Member ad = (Member) sessionStorage.get("member");
			System.out.println("403호에 가입을 환영합니다.");
			return View.MEMBER;
		}else {
			System.out.println("회원 가입에 실패하였습니다.");
			return View.MEMBER_JOIN;
		}
	}

	private View memberHome() {
		printMbHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.MEMBER_JOIN;
		case 2:
			return View.MEMBER_UPDATE;
		case 3:
			return View.MEMBER_OUT;
		case 4:
			sessionStorage.clear();
			return View.MEMBER;
		case 5:
			return View.HOME;
		default :
			return View.MEMBER;
		}
	}

	private View adminHome() {
		List<Member> list = mbService.selectList();
		for(Member m : list) {
			System.out.println(m);
		}
		System.out.println("1. 홈");
		System.out.println("2. 다시보기");
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.HOME;
		case 2:
			return View.ADMIN_HOME;
		default :
			return View.ADMIN_HOME;
		}
	}

	private View adminLogin() {
		
		String id = ScanUtil.nextLine("ID >> ");
		String pass = ScanUtil.nextLine("PASS >> ");
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		boolean chk = adService.login(param);
		if(chk) {
			Admin ad = (Admin) sessionStorage.get("admin");
			System.out.println(ad.getName()+"님 환영합니다.");
			return View.ADMIN_HOME;
		}else {
			System.out.println("해당 아이디가 없습니다.");
			return View.ADMIN_LOGIN;
		}
	}

	private View admin() {
		printAdmin();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.ADMIN_LOGIN;
		case 2:
			return View.HOME;
		default :
			return View.ADMIN;
		}
	}

	private View home() {
		printHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.ADMIN;
		case 2:
			return View.MEMBER;
		default :
			return View.HOME;
		}
	}
}
