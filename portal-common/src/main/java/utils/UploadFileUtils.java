package utils;

import com.qcloud.cos.auth.COSSigner;
import com.qcloud.cos.utils.VersionInfoUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UploadFileUtils {

    private static String secretId = "";

    private static String secretKey = "";

    /**
     * 构造body
     *
     * @param boundary
     * @param formFields
     * @param filename
     * @param contentType
     * @return
     */
    public static String buildPostObjectBody(String boundary, Map<String, String> formFields,
                                       String filename, String contentType) {
        StringBuffer stringBuffer = new StringBuffer();
        for(Map.Entry entry: formFields.entrySet()) {
            // 添加boundary行,行首以--开头
            stringBuffer.append("--").append(boundary).append("\r\n");
            // 字段名
            stringBuffer.append("Content-Disposition: form-data; name=\""
                    + entry.getKey() + "\"\r\n\r\n");
            // 字段值
            stringBuffer.append(entry.getValue() + "\r\n");
        }
        // 添加boundary行,行首以--开头
        stringBuffer.append("--").append(boundary).append("\r\n");
        // 文件名
        stringBuffer.append("Content-Disposition: form-data; name=\"file\"; "
                + "filename=\"" + filename + "\"\r\n");
        // 文件类型
        stringBuffer.append("Content-Type: " + contentType + "\r\n\r\n");
        return stringBuffer.toString();
    }

    /**
     *
     * @param pic
     * @param dir 上传的目录
     * @param baseUrl 访问照片的基路径
     * @param bucketNam cos地区
     * @param endpointName 访问照片的基路径
     * @return
     */
    public static String uploadPicture(MultipartFile pic,String dir,String baseUrl,String bucketNam,String endpointName) {
        if(pic.isEmpty() || StringUtils.isBlank(pic.getOriginalFilename())) {
            return null;
        }
        String contentType = pic.getContentType();
        if(!contentType.contains("")) {
            return null;
        }
        //获取文件的名字
        String filename = pic.getOriginalFilename();
        //获取照片的后缀
        String suffix = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf('.'));
        //生成uuid确保每个文件唯一
        String uuid = UUID.randomUUID().toString().replace("-","");
        //上传到云上的路径
        String key = dir + uuid + suffix;
        //配置云的一些参数
        String bucketName = bucketNam;
        String endpoint = endpointName;
        String secretId = UploadFileUtils.secretId;
        String secretKey = UploadFileUtils.secretKey;
        long startTimestamp = System.currentTimeMillis() / 1000;
        long endTimestamp = startTimestamp +  30 * 60;
        String endTimestampStr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
                format(endTimestamp * 1000);
        String keyTime = startTimestamp + ";" + endTimestamp;
        String boundary = "----WebKitFormBoundaryZBPbaoYE2gqeB21N";
        // 设置表单的body字段值
        Map<String, String> formFields = new HashMap<>();
        formFields.put("q-sign-algorithm", "sha1");
        formFields.put("key", key);
        formFields.put("q-ak", secretId);
        formFields.put("q-key-time", keyTime);
        // 构造policy，参考文档: https://cloud.tencent.com/document/product/436/14690
        String policy = "{\n" +
                "    \"expiration\": \"" + endTimestampStr + "\",\n" +
                "    \"conditions\": [\n" +
                "        { \"bucket\": \"" + bucketName + "\" },\n" +
                "        { \"q-sign-algorithm\": \"sha1\" },\n" +
                "        { \"q-ak\": \"" + secretId + "\" },\n" +
                "        { \"q-sign-time\":\"" + keyTime + "\" }\n" +
                "    ]\n" +
                "}";
        // policy需要base64后算放入表单中
        String encodedPolicy = new String(Base64.encodeBase64(policy.getBytes()));
        // 设置policy
        formFields.put("policy", encodedPolicy);
        // 根据编码后的policy和secretKey计算签名
        COSSigner cosSigner = new COSSigner();
        String signature = cosSigner.buildPostObjectSignature(secretKey,
                keyTime, policy);
        // 设置签名
        formFields.put("q-signature", signature);
        // 根据以上表单参数，构造最开始的body部分
        String formBody = buildPostObjectBody(boundary, formFields,
                filename, contentType);
        HttpURLConnection conn = null;
        try {
            String urlStr = "http://" + bucketName + "." + endpoint;
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", VersionInfoUtils.getUserAgent());
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 写入表单的最开始部分
            out.write(formBody.getBytes());
            // 将文件内容写入到输出流中
            InputStream in = pic.getInputStream();
            int readBytes;
            byte[] bytes = new byte[4096];
            while ((readBytes = in.read(bytes)) != -1) {
                out.write(bytes, 0, readBytes);
            }
            in.close();
            // 添加最后一个分割符，行首和行尾都是--
            byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
//            System.out.println("文件保存的路径：https://portal-pictures-1258887597.cos.ap-nanjing.myqcloud.com/" + key);
//          读取响应头部
            for (Map.Entry<String, List<String>> entries : conn.getHeaderFields().entrySet()) {
                String values = "";
                for (String value : entries.getValue()) {
                    values += value + ",";
                }
                if(entries.getKey() == null) {
                    System.out.println("reponse line:" +  values );
                } else {
                    System.out.println(entries.getKey() + ":" +  values );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        //返回照片上传的访问路径
        return baseUrl + key;
    }
}
