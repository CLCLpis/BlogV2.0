package com.xiaoshuai.utils;

import com.obs.services.ObsClient;
import com.obs.services.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author bear
 */
@Slf4j
public class ObsUploadUtil {
    /**
     * OBS图片访问域名
     */
    private static String endPoint = "https://obs.cn-south-1.myhuaweicloud.com";
    private static String accessKeyId = "67K8DPI3HUV4DSVDLOKL";
    private static String accessKeySecret = "gXHbjLY6mHQ5RiX6xpfRYFSEkdSWvxnpfbQ80Dn7";
    private static String bucketName = "pis-bear";
    private static String oss_domain = "oss.bear0901.cn/";


    /**
     * 创建ObsClient实例
      */
    private static ObsClient get(){
        return new ObsClient(accessKeyId, accessKeySecret, endPoint);
    }

    public static String obsUpload(MultipartFile file) throws IOException{
        //CommonsMultipartFile file = (CommonsMultipartFile)multipartFile;
        String fileName = "";
        if(file!=null && !"".equals(file.getOriginalFilename()) && file.getOriginalFilename()!=null){
            //获得指定文件的输入流
            InputStream content = file.getInputStream();
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 必须设置ContentLength
            meta.setContentLength(file.getSize());
            String originalFilename = file.getOriginalFilename();
            fileName =  UUID.randomUUID().toString().replaceAll("-","") + originalFilename.subSequence(originalFilename.lastIndexOf("."), originalFilename.length());
            // 上传Object.
            ObsClient obsClient = get();
            obsClient.putObject(bucketName,"admin/"+fileName,content,meta);
            if(!"".equals(fileName)){
                log.error("fileName: {}", fileName);
                fileName = oss_domain+"admin/"+fileName;
            }
            obsClient.close();
        }
        return fileName;
    }

    /**
     * 删除某个Object
     *
     * @param bucketUrl
     * @return
     */
    public static boolean deleteObject(String bucketUrl) {
        try {
            bucketUrl=bucketUrl.replace(oss_domain+"web","");
            // 删除Object.
            ObsClient obsClient = get();
            obsClient.deleteObject(bucketName, bucketUrl);
            obsClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
