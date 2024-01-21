package com.bill;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.bill.TestClass.DAO;
import com.bill.TestClass.Student;
import com.bill.TestClass.User;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

public class TestApp {
    @Test
    public void test() {

//        C:\Users\User\Desktop

//        try(FileWriter fw = new FileWriter("C:\\Users\\User\\Desktop\\test.txt", true);
//            FileReader fr = new FileReader("C:\\Users\\User\\Desktop\\test.txt");) {
//            fw.write("\r\n寫檔測試");
//            char[] cbuf = new char[10];
//            int num = 0;
//            int sum = 0;
//            String str = "";
//
//            while ((num = fr.read(cbuf)) != -1) {
//                String str1 = new String(cbuf,0,num);
//                str = str + str1;
//                sum += num;
//            }
//            System.out.println(str);
//            System.out.println(sum);
//
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }

//        Random r = new Random();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(r.nextInt(1));
//        }

        DAO<User> dao = new DAO<User>();

        dao.save("0001", new User(1, 18, "Andy"));
        dao.save("0002", new User(2, 20, "Bill"));
        dao.save("0003", new User(3, 11, "Car"));
        dao.save("0004", new User(4, 30, "Dog"));

        System.out.println(dao.list());
        System.out.println("----------------------");

        System.out.println(dao.get("0004"));

        dao.update("0004", new User(4, 26, "Email"));

        System.out.println(dao.get("0004"));

        dao.delete("0004");

        System.out.println(dao.get("0004"));

        System.out.println("----------------------");
        System.out.println(dao.list());


    }

    @Test
    public void test2() {
//        String[] strArray = new String[] {"1", "2", "3", "4", "5"};
//        func2(strArray);
//        System.out.println(Arrays.toString(strArray));


        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<?> list2 = new ArrayList<>();

        list1.add("0");
        list1.add("1");
        list1.add("2");

        String a = "";
        a.toUpperCase();
//        func(list1);
//        func1(list2);

    }

    @Test
    public void test3() {
        String md5 = SecureUtil.md5("ABC");
        String md52 = DigestUtil.md5Hex("ABC");
        byte[] abcs = DigestUtil.md5("ABC");
        String md5Base64 = Base64.getEncoder().encodeToString(abcs);
        System.out.println(md5);
        System.out.println(md52);
        System.out.println(md5Base64);

    }

    @Test
    public void test4() throws FileNotFoundException {
        // Endpoint以杭州為例，其它Region請按實際情況填寫。
        String endpoint = "http://oss-ap-northeast-1.aliyuncs.com";
// 阿里雲主帳號AccessKey擁有所有API的存取權限，風險很高。強烈建議您建立並使用RAM帳號進行API訪問或日常運維，請登入 https://ram.console.aliyun.com 建立RAM帳號。
        String accessKeyId = "LTAI5tGwg7RhMprvxTBLC8C4";
        String accessKeySecret = "pyTIB6HUCsD83DNMxXH3CGrukW7et3";
        String bucketName = "bill-project";
        String objectName = "001.jpg";
// 建立OSSClient執行個體。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
// 上傳檔案。
        String content = "Hello OSS";
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, new FileInputStream("C:\\Users\\User\\Pictures\\AAA.jpg"));
        // 關閉OSSClient。
        ossClient.shutdown();

    }

    public <T> void func1(ArrayList<T> list) {
        for (T o : list) {
//            System.out.println(o.toUpperCase());
        }
    }

    public void func2(ArrayList<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
