package com.example.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import form.UserForm;

@Service
public class ServiceM01 {

	@Autowired
	private M01DAO m01DAO;

	/**
	 * 画面の検索条件からレビューを検索
	 * @param param
	 * @return
	 */
	public List<UserForm> selectM01(UserForm param) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        result = m01DAO.selectM01(param);

        List<UserForm> rtn = new ArrayList<UserForm>();

        if (result != null && result.size() != 0) {
        	for (Map<String, Object> map : result) {
        		UserForm row = new UserForm();
        		row.setKanri_no((int)map.get("Kanri_no"));
        		row.setUser_id((String)map.get("User_id"));
        		row.setUser_nm((String)map.get("User_nm"));
        		row.setBumon_mei((String)map.get("Bumon_mei"));
        		row.setSagyoba_mei((String)map.get("Sagyoba_mei"));
        		row.setAddress((String)map.get("Address"));
        		row.setPhone((String)map.get("Phone"));
        		row.setTorokubi((LocalDateTime)map.get("Torokubi"));
        		row.setKoshinbi((LocalDateTime)map.get("Koshinbi"));
        		row.setMuko_flg((boolean)map.get("Muko_flg"));
        		rtn.add(row);
        	}
        }

        return rtn;
	}

	/**
	 * 最大の管理NO取得
	 * @return
	 */
	public int selectMaxNoM01() {
		int maxNo = 0;
        Map<String, Object> map = new HashMap<String, Object>();

        map = m01DAO.selectMaxNoM01();

        maxNo = (int)map.get("MAX_NO") + 1;

        return maxNo;
	}

	/**
	 * 管理NOからデータ取得
	 * @param param
	 * @return
	 */
	public Map<String, Object> selectByNoM01(UserForm param) {
        Map<String, Object> rtn = new HashMap<String, Object>();

        rtn = m01DAO.selectByNoM01(param);

        return rtn;
	}

	/**
	 * 新規登録
	 * @param param
	 */
	public void insertM01(UserForm param) {
        int rtn = m01DAO.insertM01(param);
	}

	/**
	 * 更新登録
	 * @param param
	 */
	public void updateByNoM01(UserForm param) {
        int rtn = m01DAO.updateByNoM01(param);
	}

	/**
	 * 削除
	 * @param param
	 */
	public void deleteByNoM01(UserForm param) {
        int rtn = m01DAO.deleteByNoM01(param);
	}
}
