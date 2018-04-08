package cn.mybase.ssm.util;

/**
 * 
 * @Title: Constants
 * @author dongchuan
 * @date 2018年3月28日 下午4:07:24
 * @Description: TODO
 * @version 1.0
 */
public final class Constants {

	private Constants() {
	}

	/**
	 * 
	 * @Title: Token
	 * @author dongchuan
	 * @date 2018年3月28日 下午4:07:59
	 * @Description: 常量
	 * @version 1.0
	 */
	public interface Token {
		public final static String FLAG = "login";
		public final static String ERRMSG = "errmsg";
		public final static String NICK_NAME = "nick_name";
	}

	/**
	 * 
	 * @Title: Status
	 * @author dongchuan
	 * @date 2018年3月28日 下午4:11:51
	 * @Description: 状态类型
	 * @version 1.0
	 */
	public interface Status {
		public final static String ZERO = "0";
		public final static String ONE = "1";
		public final static String TWO = "2";
		public final static String THREE = "3";
	}

	/**
	 * 
	 * @Title: Num
	 * @author dongchuan
	 * @date 2018年3月28日 下午4:12:13
	 * @Description: 数字类型
	 * @version 1.0
	 */
	public interface Num {
		public final static int ZERO = 0;
		public final static int ONE = 1;
		public final static int FIVE = 5;
		public final static int TEN = 10;
	}

}
