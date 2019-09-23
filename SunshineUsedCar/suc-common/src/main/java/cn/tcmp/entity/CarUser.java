package cn.tcmp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * caruser  generated at 2019-09-09 10:35:03 by: eric
 */
@Data
public class CarUser implements Serializable {
	private Integer userID;
	private String userName;//用户名
	private String userPassWord;//密码
	private String realName;//用户真实姓名
	private String userEmail;//邮箱
	private String userPhone;//手机号
	private String userAddress;//地址
	private String userGender;//性别
	private String code;//昵称



}
