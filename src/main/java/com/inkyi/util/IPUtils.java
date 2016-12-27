package com.inkyi.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

	public static String getRemortIP(HttpServletRequest request) {
		String ip = "";
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-real-ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		//windows容易出现这个问题
		if ("0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)) {
			ip = "127.0.0.1";
		}
		String[] ipArray = ip.split(",");
		if (ipArray != null && ipArray.length > 1) {
			return ipArray[0];
		}
		return ip;
	}

	/**
	 * 验证IP是否属于某个IP段
	 * 
	 * @author:InkYi
	 * @time:2016年11月25日 - 上午9:47:57
	 * @description:ipSection IP段（以'-'分隔） ip 所验证的IP号码
	 * @param ip
	 * @param ipSection
	 * @return
	 */
	public static boolean ipExistsInRange(String ip, String ipSection) {

		ipSection = ipSection.trim();

		ip = ip.trim();

		int idx = ipSection.indexOf('-');

		String beginIP = ipSection.substring(0, idx);

		String endIP = ipSection.substring(idx + 1);

		return getIp2long(beginIP) <= getIp2long(ip) && getIp2long(ip) <= getIp2long(endIP);

	}

	/**
	 * 将IP转Long型
	 * 
	 * @author:InkYi
	 * @time:2016年11月25日 - 上午10:00:28
	 * @description:
	 * @param ip
	 * @return
	 */
	public static long getIp2long(String ip) {

		ip = ip.trim();

		String[] ips = ip.split("\\.");

		long ip2long = 0L;

		for (int i = 0; i < 4; ++i) {

			ip2long = ip2long << 8 | Integer.parseInt(ips[i]);

		}

		return ip2long;

	}

	/**
	 * 将IP转Long型
	 * 
	 * @author:InkYi
	 * @time:2016年11月25日 - 上午10:00:28
	 * @description:
	 * @param ip
	 * @return
	 */
	public static long getIp2long2(String ip) {

		ip = ip.trim();

		String[] ips = ip.split("\\.");

		long ip1 = Integer.parseInt(ips[0]);

		long ip2 = Integer.parseInt(ips[1]);

		long ip3 = Integer.parseInt(ips[2]);

		long ip4 = Integer.parseInt(ips[3]);

		long ip2long = 1L * ip1 * 256 * 256 * 256 + ip2 * 256 * 256 + ip3 * 256 + ip4;

		return ip2long;

	}

	/**
	 * 将Long型转IP
	 * 
	 * @author:InkYi
	 * @time:2016年11月25日 - 上午10:00:28
	 * @description:
	 * @param ip
	 * @return
	 */
	public static String getLong2Ip(long longIp) {

		StringBuffer sb = new StringBuffer("");

		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");

		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");

		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));

		return sb.toString();
	}

	public static void main(String[] args) {

		// 10.10.10.116 是否属于固定格式的IP段10.10.1.00-10.10.255.255

		String ip = "10.10.10.116";

		String ipSection = "10.10.1.00-10.10.255.255";

		boolean exists = ipExistsInRange(ip, ipSection);

		System.out.println(exists);

		System.out.println(getIp2long(ip));

		System.out.println(getIp2long2(ip));

		System.out.println(getLong2Ip(168430196));

	}

}