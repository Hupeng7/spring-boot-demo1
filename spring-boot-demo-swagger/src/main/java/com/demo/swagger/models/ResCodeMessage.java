package com.demo.swagger.models;

import java.util.HashMap;
import java.util.Map;

public class ResCodeMessage {
	public static final int SUCCESS = 10000;
	public static final int FAILED  = -10000;
	public static final int FILE_ERROR = -10001;
	public static final int NONE_RECORD = -10002;

	
	private static Map<Integer, String> codeMessage = new HashMap<>();
	
	static {
		codeMessage.put(SUCCESS, "成功");
		codeMessage.put(FAILED, "失败");
		codeMessage.put(FILE_ERROR, "获取模板文件失败");
		codeMessage.put(NONE_RECORD, "没有此记录");
	}
	
	public static String getMessage(int resCode) {
		return codeMessage.get(resCode) == null ? codeMessage.get(FAILED) : codeMessage.get(resCode);
	}

}
