package com.inkyi.iblog.vo;


/**
 * 注册vo类
 * @ClassName: RegVO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author InkYi
 * @date 2016年6月9日 下午5:19:04 
 *
 */
public class RegVo {

	private String username;

    private String password;
    
    private String repeatPW;

    private String email;

    private String mobile;
    
    private String reCaptcha;

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

	public String getRepeatPW() {
		return repeatPW;
	}

	public void setRepeatPW(String repeatPW) {
		this.repeatPW = repeatPW;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getReCaptcha() {
		return reCaptcha;
	}

	public void setReCaptcha(String reCaptcha) {
		this.reCaptcha = reCaptcha;
	}
    
    
}
