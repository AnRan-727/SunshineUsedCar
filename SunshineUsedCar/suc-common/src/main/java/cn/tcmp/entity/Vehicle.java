package cn.tcmp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * vehicle  generated at 2019-09-09 10:35:03 by: eric
 */
@Data
public class Vehicle implements Serializable {
	private Integer vehicleID;
	private String vehicleName;
	private Integer parentID;
}
