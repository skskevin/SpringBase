package cn.mybase.ssm.util.base;

import java.util.ArrayList;
import java.util.List;

public class ParamUtil {
	
	/**
	 * string 转换成Long数组
	 * 
	 * @param str
	 * @param splitStr
	 * @return
	 */
	public final static List<Long> toLongList(String str, String splitStr) {
		if (str != null) {
			ArrayList<Long> longList = new ArrayList<Long>();
			String[] strList = str.split(splitStr);
			for (String string : strList) {
				longList.add(Long.parseLong(string));
			}
			return longList;
		}
		return null;
	}
	
	public final static List<Integer> toIntList(String str, String splitStr) {
		if (str != null) {
			ArrayList<Integer> intList = new ArrayList<Integer>();
			String[] strList = str.split(splitStr);
			for (String string : strList) {
				intList.add(Integer.parseInt(string));
			}
			return intList;
		}
		return null;
	}
}
