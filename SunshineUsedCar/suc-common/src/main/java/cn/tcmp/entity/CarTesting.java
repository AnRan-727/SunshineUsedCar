package cn.tcmp.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * cartesting  generated at 2019-09-09 10:35:03 by: eric
 */
@Data
public class CarTesting implements Serializable {

	private Integer ctID;
	private Testing testID;
	private Car carID;
	private Integer goodOrBad;
	private Date detectionTime;


}
