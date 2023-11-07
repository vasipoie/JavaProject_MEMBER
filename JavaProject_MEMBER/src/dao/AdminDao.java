package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.Admin;

public class AdminDao {
	private static AdminDao singleTon = null;

	private AdminDao() {
	};

	public static AdminDao getInstance() {
		if (singleTon == null) {
			singleTon = new AdminDao();
		}
		return singleTon;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public Admin login(List<Object> param) {
		String sql = " select * from st_admin where id =? and pass =? ";
		Map<String, Object> map = jdbc.selectOne(sql, param);
		if(map == null) return null;
		Admin ad = ConvertUtils.convertToVo(map, Admin.class);
		return ad;
	}
	
}
