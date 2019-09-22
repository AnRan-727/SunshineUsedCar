package cn.tcmp.vo.jiance;

import cn.tcmp.entity.Car;
import cn.tcmp.entity.Testing;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/21
 */
@Data
public class CT1VO implements Serializable {

    private Integer testID;
    private String t1;
    private List<CT2VO> ct2VO;
    private Integer goodOrBad;
    private Date detectionTime;

}
