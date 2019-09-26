package cn.tcmp.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/26
 */
@Data
public class Appointment implements Serializable {

    private Integer carID,appID,staticc;
    private String userName,userPhone;
    private Date apptime;

}
