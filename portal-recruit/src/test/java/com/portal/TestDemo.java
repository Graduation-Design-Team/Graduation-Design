package com.portal;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.AppendObjectRequest;
import com.qcloud.cos.model.AppendObjectResult;
import com.qcloud.cos.region.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestDemo {


    @Test
    public void uploadPic() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("", "");
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-nanjing"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        String bucketName = "portal-pictures-1258887597";
        try {
            File localFile = new File("C:\\Users\\xiaohai\\Desktop\\11.png");
            //文件的后缀名
            String suffix = localFile.getName().substring(localFile.getName().lastIndexOf('.'));
            //上传的相对路径
            String key = UUID.randomUUID().toString().replace("-","") + suffix;
            AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName, key, localFile);
            appendObjectRequest.setPosition(0L);
            AppendObjectResult appendObjectResult = cosclient.appendObject(appendObjectRequest);
            System.out.println("文件上传的地址：https://portal-pictures-1258887597.cos.ap-nanjing.myqcloud.com/" + key);
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // 关闭客户端
        cosclient.shutdown();
    }

}
