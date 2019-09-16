package cn.tcmp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * personnel  generated at 2019-09-09 10:35:03 by: eric
 */
@Data
public class Personnel implements Serializable {
	private Integer personnelID;
	private String personnelPhone;
	private String personnelName;
	private String personnelPassWord;
	private String personnelEmail;
	private String personnelAddress;
	private String personnelIDCard;
	private String personnelGender;


}
