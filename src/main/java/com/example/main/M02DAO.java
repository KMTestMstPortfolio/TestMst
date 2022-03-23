package com.example.main;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** 部門マスタDAO */
@Repository
public class M02DAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 全検索
	 * @return
	 */
	public List<Map<String, Object>> selectAllM02() {
		String query = "SELECT * FROM M02";
		List<Map<String, Object>> m02 = jdbcTemplate.queryForList(query);
		return m02;
	}
}
