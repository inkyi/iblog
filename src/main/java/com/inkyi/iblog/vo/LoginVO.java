package com.inkyi.iblog.vo;
/**
 * 用户登陆VO类
 * @ClassName: LoginVO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author InkYi
 * @date 2016年3月26日 下午5:27:49 
 *
 */
public class LoginVO {

    private String username;

    private String password;

    private String verifyCode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
