package cn.tcmp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * caruser  generated at 2019-09-09 10:35:03 by: eric
 */
@Data
public class CarUser implements Serializable {
	private Integer userID;
	private String userName;
	private String userPassWord;
	private String realName;
	private String userEmail;
	private String userPhone;
	private String userAddress;
	private String userGender;
	private String code;



}
