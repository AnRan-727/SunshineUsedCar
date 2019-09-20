package cn.tcmp.vo;
import cn.tcmp.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * car  generated at 2019-09-09 10:35:03 by: eric
 *
 */

/**
 * 汽车vo类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarVO implements Serializable {
	private Integer carID;
	private String pinpaiID;
	private String vehicleIDsss;
	private Integer vehicleAge;//车龄
	private String mileage;
	private String model;
	private String presentPrice;
	private String originalPrice;
	private String typeID;
	private String seatID;
	private String gearboxID;
	private Integer displacement;
	private String emissionID;
	private String fuelID;
	private String colorID;
	private String countryID;
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
	private Vehicle vehicle;


}
