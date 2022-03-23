package com.example.main;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import form.UserForm;

@Controller
public class MainController {

    @Autowired
    private ServiceM01 serviceM01;
    @Autowired
    private ServiceM02 serviceM02;

    /* 部門コンボボックス */
    List<Map<String, Object>> bumonList;

    /**
     * 初期画面表示時処理
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model) {

    	//部門コンボボックス初期化
    	bumonList = serviceM02.selectAllM02();
    	model.addAttribute("bumonList", bumonList);

    	//検索条件初期化
    	model.addAttribute("searchData", new UserForm());

        return "index";
    }

    /**
     * 検索結果取得
     * @param param
     * @param model
     * @return
     */
    @PostMapping("/index")
    public String select(@ModelAttribute("formModel") UserForm param, Model model) {

    	//検索
    	List<UserForm> result = serviceM01.selectM01(param);

        model.addAttribute("userList", result);

        //初期化
        initView(model, param);

        return "index";
    }

    /**
     * 新規投稿画面への遷移
     * @param model
     * @return
     */
    @PostMapping(path = "edit", params = "insert")
    String moveInsert(Model model) {

    	//空データ設定
    	model.addAttribute("updateData", new UserForm());

    	//初期化
        initView(model, null);

        return "edit";
    }

    /**
     * 更新画面への遷移
     * @param param
     * @param model
     * @return
     */
    @PostMapping(path = "edit", params = "update")
    String moveUpdate(@ModelAttribute("updFormModel") UserForm param, Model model) {

    	//更新対象レコード取得
    	Map<String, Object> updData = serviceM01.selectByNoM01(param);

    	model.addAttribute("updateData", updData);
    	model.addAttribute("selectedBumonCd", updData.get("bumon_cd"));

    	//初期化
        initView(model, null);

        return "edit";
    }

    /**
     * 削除処理
     * @param delData
     * @param model
     * @return
     */
    @PostMapping(path = "edit", params = "delete")
    String delete(@ModelAttribute("updFormModel") UserForm delData, Model model) {

    	//削除
    	serviceM01.deleteByNoM01(delData);

    	//初期化
        initView(model, null);

        return "index";
    }

    /**
     * 登録更新処理
     * @param updData
     * @param error
     * @param model
     * @return
     */
    @PostMapping("/edit/update")
    String update(@ModelAttribute("updFormModel") UserForm updData, Model model) {

    	//TODO 必須チェック
    	if (null == updData.getUser_id() || null == updData.getUser_id()) {
    		return "edit";
    	}

    	if (updData.getKanri_no() == 0) {
    		//新規登録
        	//最大SEQNOを取得
        	int max_seq = serviceM01.selectMaxNoM01();
        	updData.setKanri_no(max_seq);

        	//登録値の設定
        	serviceM01.insertM01(updData);
    	}
    	else {
    		//更新登録
    		serviceM01.updateByNoM01(updData);
    	}

    	//初期化
        initView(model, null);

        return "index";
    }

    /**
     * 画面初期値設定 index
     * @param model
     * @param searchData
     */
    private void initView(Model model, UserForm searchData) {

    	//部門コンボボックス設定
    	model.addAttribute("bumonList", bumonList);

    	//検索条件がある場合は保持する
    	if (searchData == null) {
    		model.addAttribute("searchData", new UserForm());
    	}
    	else {
    		searchData.setSelectedBumonCd(searchData.getBumon_cd());
    		model.addAttribute("searchData", searchData);
    	}
    }

}
