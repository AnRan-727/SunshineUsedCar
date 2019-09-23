package cn.tcmp.controller.houtai;

import cn.tcmp.service.CarQicheService;
import cn.tcmp.util.PageQiche;
import cn.tcmp.vo.CarVO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QicheguanliController {

    @Reference
    private CarQicheService carQicheService;
    @RequestMapping("qiche-list")
    public  String queryQiChe(Integer pageNum, Integer pageSize, Model model) {
        if (pageNum == null) {
            pageNum=1;
        }
        if (pageSize == null) {
            pageSize=5;
        }
        PageQiche<CarVO> pageQiche = carQicheService.queryAllQiche(pageNum, pageSize);
        System.err.println(pageQiche);

        model.addAttribute("carlist",pageQiche);
        return "houtai/qiche/qiche-list";
    }

}
