package entity;

import java.util.Date;

import lombok.Data;

/** 作業者マスタ */
@Data
public class M01 {
	/** 管理NO */
	private int kanri_no;
	/** 作業者ID */
	private String user_id;
	/** 作業者名 */
	private String user_nm;
	/** 部門コード */
	private String bumon_cd;
	/** 作業場 */
	private String sagyoba_mei;
	/** メールアドレス */
	private String address;
	/** 携帯番号 */
	private String phone;
	/** 登録日 */
	private Date torokubi;
	/** 更新日 */
	private Date koshinbi;
	/** 無効フラグ */
	private boolean muko_flg;
}
