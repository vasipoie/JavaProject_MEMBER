package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import util.ScanUtil;
import vo.Member;

public class MemberDao {
	private static MemberDao singleTon = null;

	private MemberDao() {
	};

	public static MemberDao getInstance() {
		if (singleTon == null) {
			singleTon = new MemberDao();
		}
		return singleTon;
	}
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<Member> selectList() {
		String sql = "select * from student ";
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return ConvertUtils.convertToList(l, Member.class);
	}

	public int sign(List<Object> param) {
		String sql = "insert into student  (no, id, pass, name, age )VALUES ((select max(no)+1 from student), ?, ?,?,?)";
		return jdbc.update(sql, param);
	}

	public Member login(List<Object> param) {
		String sql = "select * from student where id =? and pass =? and delyn is null";
		Map m = jdbc.selectOne(sql, param);
		if(m == null) return null;
		return ConvertUtils.convertToVo(m, Member.class);
	}

	public void update(List<Object> param, int select) {
		String sql_front = "update student set";
		if(select == 1 || select == 5) {
			sql_front += " pass = ? ";
		}
		if(select == 2 || select == 5) {
			if(select == 5) sql_front +=",";
			sql_front += " name = ? ";
		}
		if(select == 3 || select == 5) {
			if(select == 5) sql_front +=",";
			sql_front += " age = ? ";
		}
		if(select == 4 || select == 5) {
			if(select == 5) sql_front +=",";
			sql_front += " rm = ? ";
		}
		String sql_where = " where id=? and pass =?";
		String sql = sql_front + sql_where;
		System.out.println(sql);
		jdbc.update(sql, param);
	}
	
	public void delete(List<Object> param) {
		String sql = "update student set delyn = 'y' where no = ?";
		jdbc.update(sql, param);
	}
}
