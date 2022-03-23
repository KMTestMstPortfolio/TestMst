package com.example.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceM02 {
	@Autowired
	private M02DAO m02DAO;

	/**
	 * 全検索
	 * @return
	 */
	public List<Map<String, Object>> selectAllM02() {
		List<Map<String, Object>> rtn = new ArrayList<Map<String, Object>>();
		rtn = m02DAO.selectAllM02();
		return rtn;
	}
}
