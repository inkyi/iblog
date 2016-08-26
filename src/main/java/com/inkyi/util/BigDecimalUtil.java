package com.inkyi.util;

import java.math.BigDecimal;

/**
 * BigDecimal算法
 * 
 * @author <a href="mailto:Administrator@abc.com">Administrator</a>
 * @version 1.0
 * @since 2013-7-26
 */
public class BigDecimalUtil {

	private static int DEF_DIV_SCALE = 10; // 默认精确的小数位

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
	 	return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 提供精确的小数位处理，去掉保留位数后的数字
	 * 
	 * @param v 需要处理的数字
	 * @param scale 小数点后保留几位
	 * @return 去掉保留位数后的结果
	 */
	public static double decimal(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	public static double decimal(String v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v.trim());
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	
	/**
	 * 提供精确的小数位处理，向前进位+1
	 * 
	 * @param v 需要处理的数字
	 * @param scale 小数点后保留几位
	 * @return 去掉保留位数后的结果
	 */
	public static double ROUND_UP(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_UP).doubleValue();
	}
	
	/**
	 * 创建金额
	 * @param o
	 * @return
	 */
	public static BigDecimal nDec (String o) {
		try {
			if (o ==null && "".equals(o)){
				return new BigDecimal("0");
			}else{
				return new BigDecimal(new BigDecimal(o).toPlainString());
			}
		} catch (Exception e) {
			return new BigDecimal("0");
		}
	}
	
	public static BigDecimal nDec (BigDecimal o) {
		try {
			if (o ==null ){
				return new BigDecimal("0");
			}else{
				return new BigDecimal(o.toPlainString());
			}
		} catch (Exception e) {
			return new BigDecimal("0");
		}
	}
	
	public static BigDecimal nDec (double o) {
		try {
			return new BigDecimal(new BigDecimal(Double.toString(o)).toPlainString());
		} catch (Exception e) {
			return new BigDecimal("0");
		}
	}
	
	
	/**
	 * 判断金额是否是两位小数
	 * @param o
	 * @return
	 */
	public static boolean isTWOscale (BigDecimal o) {
		try {
			if (o ==null ){
				return false;
			}else{
				BigDecimal o_tmp = new BigDecimal(o.toPlainString()).setScale(2, BigDecimal.ROUND_DOWN);
				if(o.compareTo(o_tmp)!=0 ){
					return false;
				}else{
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public static double divUp(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_UP).doubleValue();
	}
	
	public static double divDown(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * new BigDecimal("1")
	 */
	public static final BigDecimal num_1 =new BigDecimal("1");
	/**
	 * new BigDecimal("-1")
	 */
	public static final BigDecimal num_n1 =new BigDecimal("-1");
	public static final BigDecimal num_0 =new BigDecimal("0");
	public static void main(String[] args) {
//		String str = "1.035";
//		String tmp =StringUtils.isNull(str).replaceAll(",", "");
//		BigDecimal tmpBD = new BigDecimal(tmp);
//		tmpBD=tmpBD.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_DOWN);
//		DecimalFormat df = new DecimalFormat(",###.00");
//		System.out.println( decimal(942010.549997,2));
		BigDecimal tmpBD = new BigDecimal("942010.549997");
		System.out.println(tmpBD.setScale(2, BigDecimal.ROUND_DOWN).toString());
	}
}
