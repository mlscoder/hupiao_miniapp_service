package com.adong.start.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 静态工具类
 */
public class UtilTool {

	/**
	 * 字符编码转换
	 *
	 */
	public static String getUtfToISO(String code, String toCode, String str) {
		byte[] bytes;
		String reStr = "";
		try {
			bytes = str.getBytes(code);
			reStr = new String(bytes, toCode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reStr;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return 返回true或false
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]+.?[0-9]+");
		return pattern.matcher(str).matches();
	}

	/**
	 * 生成uuid
	 * 
	 * @return 返回uuid
	 */
	public static String getUUId() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}

	/**
	 * 格式化字符串到日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date strToDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date rdate = null;
		try {
			rdate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rdate;
	}

	/**
	 * 日期转换为只有年月日的短日期
	 * 
	 * @param tobAry
	 * @return 短日期
	 */
	public static Date longDateToShortDate(Object tobAry) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rdate = null;
		try {
			String strDate = sdf.format(tobAry);
			rdate = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rdate;
	}

	public static Date longDateToShortDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rdate = null;
		try {
			String strDate = sdf.format(date);
			rdate = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rdate;
	}

	/**
	 * 256位加密
	 * 
	 * @param str
	 * @return
	 * 
	 */

	public static String sha256(String str) {
		String hex256 = DigestUtils.sha256Hex(str);
		return hex256;
	}

	/**
	 * md5加密
	 * 
	 * @param inStr
	 * @return
	 * @throws Exception
	 */
	public static String md5Encode(String inStr) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 数字字符串转换成数组
	 * 
	 * @param 字符串
	 * @param 分隔符
	 * @return
	 */
	public static int[] String2IntArray(String str, String split) {

		String[] sts = str.split(split);
		int[] ints = new int[sts.length];
		for (int i = 0; i < sts.length; i++) {
			if (sts != null && !sts.equals("")) {
				ints[i] = Integer.parseInt(sts[i]);
			} else {
				ints[i] = 0;
			}
		}

		return ints;
	}

	/**
	 * 日期转化成相应字符串
	 * 
	 * @param date
	 * @return 字符串
	 */
	public static String date2Str(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(date);
		return str;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(md5Encode("1"));
	}

}
