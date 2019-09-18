package cn.tcmp.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sale  generated at 2019-09-09 10:35:03 by: eric
 */
@Data
public class Sale implements Serializable {
	private Integer saleID;
	private Integer carID;
	private Integer personnelID;
	private Integer userID;
	private Date saleTime;



}
