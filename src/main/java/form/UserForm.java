package form;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserForm {
	/** 管理NO */
	private int kanri_no;
	/** 作業者ID */
	private String user_id;
	/** 作業者名 */
	private String user_nm;
	/** 部門コード */
	private String bumon_cd;
	/** 部門名 */
	private String bumon_mei;
	/** 作業場 */
	private String sagyoba_mei;
	/** メールアドレス */
	private String address;
	/** 携帯番号 */
	private String phone;
	/** 登録日 */
	private LocalDateTime torokubi;
	/** 更新日 */
	private LocalDateTime koshinbi;
	/** 無効フラグ */
	private boolean muko_flg;
	/** 選択部門コード */
	private String selectedBumonCd;
}
