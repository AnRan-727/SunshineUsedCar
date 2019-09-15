package cn.tcmp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * datadictionary  generated at 2019-09-09 10:35:03 by: eric
 */
@Data
public class DataDictionary implements Serializable {
	private Integer ddID;
	private String typeCode;
	private String typeName;
	private Integer valueID;
	private String valueName;



}
