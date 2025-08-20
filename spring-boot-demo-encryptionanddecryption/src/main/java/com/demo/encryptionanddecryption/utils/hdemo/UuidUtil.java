package com.demo.encryptionanddecryption.utils.hdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//import com.roadoor.framework.common.utils.FormatUtil;

/**
 * UUID生成工具类<br>
 * 支持自定义长度
 * 
 * @author Yuanqy
 *
 */
public class UuidUtil {
	private static Logger logger = LoggerFactory.getLogger(UuidUtil.class);
	private final static String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static long curLocalIP = 0L;
	private static long curPID = 0L;

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 获取UUID[java原生]
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获取UUID[指定长度]
	 */
	public static String getUUID(int len) {
		return getUUID(len, 36);
	}

	/**
	 * 获取UUID[指定长度，指定进制]
	 * 
	 * @param len 长度
	 * @param jz  进制[10~62]
	 */

	public static String getUUID(int len, int jz) {
		if (jz < 10 || jz > 62)
			jz = 36;
		String cu = "";
		if (len <= 12) {
			cu = parseStrJZ(System.currentTimeMillis(), jz) + getCharStr(5, jz);
		} else if (len > 12) {
			if (curLocalIP == 0) {
				try {
					curLocalIP = 0L;// Long.parseLong(HttpServletRequestUtil.getLocalRealIp().replaceAll("\\.",
									// ""));
				} catch (Exception e) {
					logger.error("error:",  e);
					curLocalIP = 255255255255L;
				}
				System.out.println("【curLocalIP=" + curLocalIP + "】");
			}
			if (curPID == 0) {
				String cPname = ManagementFactory.getRuntimeMXBean().getName();
				curPID = Long.parseLong(cPname.substring(0, cPname.indexOf("@")));
				System.out.println("【curPID=" + curPID + "】");
			}
			long cTid = Thread.currentThread().getId();
			/**
			 * String cos =FormatUtil.leftPad(parseStrJZ(curLocalIP + curPID + cTid, jz), 6,
			 * "0"); if (len < 18) { cu = parseStrJZ(System.currentTimeMillis(), jz) +
			 * cos.substring(cos.length() - 4) + getCharStr(6, jz); } else { cu =
			 * parseStrJZ(System.currentTimeMillis(), jz) + cos; cu = cu + getCharStr(len -
			 * cu.length(), jz); }
			 */
		}
		if (cu.length() < len)
			cu += getCharStr(len - cu.length(), jz);
		return cu.substring(cu.length() - len);
	}

	public static String getCharStr(int len, int jz) {
		String a = "";
		for (int i = 0; i < len; i++)
			a += chars.charAt((int) Math.round(Math.random() * (jz - 1)));
		return a;
	}

	/**
	 * 10进制转化成其他进制
	 */
	public static String parseStrJZ(long num, int jz) {
		String str = "";
		if (num == 0) {
			return "";
		} else {
			str = parseStrJZ(num / jz, jz);
			return str + chars.charAt((int) (num % jz));
		}
	}

	/**
	 * 其他进制转化成10进制
	 */
	public static long parseLong10(String str, int jz) {
		long result = 0;
		int len = str.length();
		for (int i = len; i > 0; i--) {
			result += chars.indexOf(str.charAt(i - 1)) * ((long) (Math.pow(jz, len - i)));
		}
		return result;
	}

	public static void main(String[] args) {

		long x = System.currentTimeMillis();
		// System.out.println(x);
		// System.out.println(parseStrJZ(238328, 62));
		// System.out.println(parseStrJZ(x, 62));
		// System.out.println(parseStrJZ(x, 16));
		// System.out.println(parseLong10(parseStrJZ(x, 36), 36));
		// System.out.println(parseLong10(parseStrJZ(x, 62), 62));
		// System.out.println(parseLong10(parseStrJZ(x, 16), 16));
		// System.out.println(parseLong10("ZZZ", 62));

		Set<String> set = new HashSet<String>();
		for (int i = 0; i < 2000; i++) {
			set.add(getUUID(18, 62));
		}
		long en = System.currentTimeMillis() - x;
		int y = 0;
		for (String string : set) {
			y++;
			if (y > 10)
				break;
			System.out.println(string);
		}
		System.out.println(en);
		System.out.println(set.size());
	}

}