package cn.tcmp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * personnel  generated at 2019-09-09 10:35:03 by: eric
 */
@Data
public class Personnel implements Serializable {
	private Integer personnelID;//id
	private String personnelPhone;//工作人员手机号
	private String personnelName;//工作人员姓名
	private String personnelRealName;//工作人员真实姓名
	private String personnelPassWord;//工作人员密码
	private String personnelEmail;//工作人员邮箱
	private String personnelAddress;//工作人员地址
	private String personnelIDCard;//工作人员身份证号
	private String personnelGender;//工作人员性别
	private String personnelDate;//工作人员入职时间

}
