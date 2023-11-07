package service;

import java.util.List;

import controller.Controller;
import dao.MemberDao;
import vo.Member;

public class MemberService {
	private static MemberService singleTon = null;

	private MemberService() {
	};

	public static MemberService getInstance() {
		if (singleTon == null) {
			singleTon = new MemberService();
		}
		return singleTon;
	}
	
	MemberDao mbDao = MemberDao.getInstance();

	public List<Member> selectList() {
		return mbDao.selectList() ;
	}

	public boolean sign(List<Object> param) {
		if(mbDao.sign(param) !=0) {
			return true;
		}
		return false;
	}

	public Member login(List<Object> param) {
		return mbDao.login(param);
	}

	public void update(List<Object> param, int select) {
		mbDao.update(param, select);
	}

	public void delete(List<Object> param) {
		mbDao.delete(param);
	}

}
