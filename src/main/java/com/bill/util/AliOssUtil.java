package com.bill.util;

import java.io.InputStream;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;

public class AliOssUtil {
    private static final String ENDPOINT = "http://oss-ap-northeast-1.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAI5tGwg7RhMprvxTBLC8C4";
    private static final String ACCESS_KEY_SECRET = "pyTIB6HUCsD83DNMxXH3CGrukW7et3";
    private static final String BUCKET_NAME = "bill-project";

    // 防止實例化
    private AliOssUtil() {
        throw new IllegalStateException("Utility class");
    }
    
    public static String uploadFile(String fileName, InputStream inputStream) {
        // 建立OSSClient執行個體。
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url = "";
        try {
            // 上傳檔案。
            ossClient.putObject(BUCKET_NAME, fileName, inputStream);
            url = "https://" + BUCKET_NAME + "." + ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1) + "/" + fileName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return url;
    }
}
