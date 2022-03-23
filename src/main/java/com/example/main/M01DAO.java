package com.example.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import form.UserForm;

@Repository
public class M01DAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

	public List<Map<String, Object>> selectM01(UserForm param) {
		String query = "SELECT M01.KANRI_NO,M01.KOSHINBI,M01.TOROKUBI,M01.USER_ID,M01.USER_NM,M01.ROLE_ID,M01.ADDRESS,M01.PHONE,M01.MUKO_FLG "
				+ ",M02.BUMON_MEI "
				+ "FROM M01 INNER JOIN M02 ON M01.BUMON_CD = M02.BUMON_CD "
				+ "WHERE 1=1 ";
		if (null != param.getUser_id() && !"".equals(param.getUser_id())) {
			query = query + " AND M01.USER_ID LIKE '%" + param.getUser_id() + "%'";
		}
		if (null != param.getUser_nm() && !"".equals(param.getUser_nm())) {
			query = query + " AND M01.USER_NM = '" + param.getUser_nm() + "'";
		}
		if (null != param.getBumon_cd() && !"".equals(param.getBumon_cd())) {
			query = query + " AND M01.BUMON_CD = '" + param.getBumon_cd() + "'";
		}
		if (!param.isMuko_flg()) {
			query = query + " AND M01.MUKO_FLG = false";
		}

		List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

		return result;
	}


	/**
	 * 最大の管理NO取得
	 * @return
	 */
	public Map<String, Object> selectMaxNoM01() {
		String query = "SELECT MAX(M01.kanri_no) AS MAX_NO FROM M01";

		Map<String, Object> M01 = jdbcTemplate.queryForMap(query);

		return M01;
	}

	/**
	 * 管理NOからデータ取得
	 * @param param
	 * @return
	 */
	public Map<String, Object> selectByNoM01(UserForm param) {
		String query = "SELECT M01.*, M01.USER_NM FROM M01 INNER JOIN M02 ON M01.BUMON_CD = M02.BUMON_CD "
				+ "WHERE M01.kanri_no = " + param.getKanri_no();

		Map<String, Object> rtn = jdbcTemplate.queryForMap(query);

		return rtn;
	}

	/**
	 * 新規登録
	 * @param param
	 * @return 登録件数
	 */
	public int insertM01(UserForm param) {
		int rtn = 0;

		Date nowDate = new Date();

		String query = "INSERT INTO M01 VALUES ("
				+ param.getKanri_no() + ","
				+ "'" + param.getUser_id() + "',"
				+ "'" + param.getUser_nm() + "',"
				+ "'" + param.getBumon_cd() + "',"
				+ "'" + param.getRole_id() + "',"
				+ "'" + param.getAddress() + "',"
				+ "'" + param.getPhone() + "',"
				+ "'" + dateFormat.format(nowDate) + "',"
				+ "'" + dateFormat.format(nowDate) + "',"
				+ param.isMuko_flg()
				+ ")";

		rtn = jdbcTemplate.update(query);

		return rtn;
	}

	/**
	 * 更新登録
	 * @param param
	 * @return 更新件数
	 */
	public int updateByNoM01(UserForm param) {
		int rtn = 0;

		Date nowDate = new Date();

		String query = "UPDATE M01 SET "
				+ "KOSHINBI = " + "'" + dateFormat.format(nowDate) + "',"
				+ "USER_ID = " + "'" + param.getUser_id() + "',"
				+ "USER_NM = " + "'" + param.getUser_nm() + "',"
				+ "BUMON_CD = " + "'" + param.getBumon_cd() + "',"
				+ "ROLE_ID = " + "'" + param.getRole_id() + "',"
				+ "ADDRESS = " + "'" + param.getAddress() + "',"
				+ "PHONE = " + "'" + param.getPhone() + "',"
				+ "MUKO_FLG = " + param.isMuko_flg()
				+ " WHERE kanri_no = " + param.getKanri_no();

		rtn = jdbcTemplate.update(query);

		return rtn;
	}

	/**
	 * 削除
	 * @param param
	 * @return 削除件数
	 */
	public int deleteByNoM01(UserForm param) {
		int rtn = 0;

		String query = "DELETE FROM M01 WHERE kanri_no = " + param.getKanri_no();

		rtn = jdbcTemplate.update(query);

		return rtn;
	}
}
