import com.xiaoshuai.MyblogMain;
import com.xiaoshuai.service.ApiService;
import com.xiaoshuai.utils.ObsUploadUtil;
import com.xiaoshuai.utils.OssUtil;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-24-上午 10:03
 **/

@SpringBootTest(classes = MyblogMain.class)
public class Test {


    @org.junit.jupiter.api.Test
    public void test() throws IOException {

        File file = new File("C:\\Users\\PIS\\Desktop\\1.png");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        ObsUploadUtil.obsUpload(multipartFile);
        System.out.println(11);
    }
}
