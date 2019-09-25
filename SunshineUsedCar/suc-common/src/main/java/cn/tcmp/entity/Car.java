package cn.tcmp.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * car  generated at 2019-09-09 10:35:03 by: eric
 */
@Data
public class Car implements Serializable {
	private Integer carID;
	private Integer pinpaiID;
	private Integer vehicleID;
	private Integer VehicleAge;//车龄
	private String mileage;
	private String model;
	private String presentPrice;
	private String originalPrice;
	private Integer typeID;
	private Integer seatID;
	private Integer gearboxID;
	private Integer displacement;
	private Integer emissionID;
	private Integer fuelID;
	private Integer colorID;
	private Integer countryID;
	private String topCity;
	private Date placingTime;
	private Date yearlyTime;
	private Date insuranceTime;
	private Integer superValue;
	private Integer urgentSale;
	private Integer newCar;
	private Integer transitive;
	private Integer personnelID;
	private String subordinateName;
	private Date shelfTime;//上架时间



}
