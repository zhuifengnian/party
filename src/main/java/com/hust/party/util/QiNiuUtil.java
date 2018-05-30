package com.hust.party.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 七牛工具类，包括上传图片等功能<br/>
 * fan 2018/5/27 15:17
 */
public class QiNiuUtil {

    private static final String KEYS = "http://p8p5n2pli.bkt.clouddn.com/";

    //...生成上传凭证，然后准备上传
    private static final String accessKey = "f7S3xKlvKLRnqctORPPAth4GRw0JxtpqYUkgRhEL";
    private static final String secretKey = "SDvAjl6hONBXxM5CcEC4uf5ffZ-tiBCJ5-bhI6Id";

    //区域
    private static final Zone ZONE = Zone.zone0();

    //..存储空间
    private static final String BUCKET = "zhuifeng";

    public static String manageFile(MultipartFile file) {
        String tmpKeys = "";
        //判断是否大于5M
        if(file != null && file.getSize()<5*1048576) {
            String key = LocalDateTime.now().getNano() + file.getOriginalFilename();

            Configuration cfg = new Configuration(ZONE);    //zone2代表华南区
            UploadManager uploadManager = new UploadManager(cfg);

            //默认不指定key的情况下，以文件内容的hash值作为文件名
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(BUCKET);
            try {
                Response response = uploadManager.put(file.getBytes(), key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                tmpKeys = KEYS + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return tmpKeys;
        }
        //大于5M返回空
        return null;
    }
}