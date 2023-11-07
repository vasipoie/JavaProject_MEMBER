package print;

import vo.Member;

public class Print {
	
	public void printVar() {
		System.out.println("------------------------------------");
	}
	
	public void printLn(int num) {
		for(int i=0; i<num; i++) System.out.println();
	}
	
	public void printMBUpdateDetail() {
		printVar();
		printLn(2);
		System.out.println("1. 비밀 번호 ");
		System.out.println("2. 이름");
		System.out.println("3. 나이");
		System.out.println("4. 호수");
		System.out.println("5. 전체");
		printLn(3);
		printVar();
	}
	
	public void printMbUpdate(Member mb) {
		System.out.println("--------------정보 수정----------------");
		printLn(3);
		System.out.println(mb);
		printLn(4);
		printVar();
	}
	
	
	
	public void printMbHome() {
		System.out.println("--------------일반 회원----------------");
		printLn(3);
		System.out.println("1. 회원 가입 ");
		System.out.println("2. 회원 정보 수정");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 로그아웃");
		System.out.println("5. 종료");
		printLn(3);
		printVar();

	}
	
	public void printAdmin() {
		printVar();
		System.out.println("1. 관리자 로그인 ");
		System.out.println("2. 홈");
		printLn(8);
		printVar();
	}
	
	public void printHome() {
		printVar();
		System.out.println("1. 관리자");
		System.out.println("2. 일반회원");
		printLn(8);
		printVar();
	}
	
}
