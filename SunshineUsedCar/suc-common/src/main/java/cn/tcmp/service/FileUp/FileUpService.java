package cn.tcmp.service.FileUp;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/21
 */
public interface FileUpService {
    //图片上传
    String upLoad(MultipartFile file);
}
