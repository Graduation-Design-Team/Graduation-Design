package com.portal.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.portal.config.GiteeImgBedConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageUtils {
    public static String upload(MultipartFile file) throws IOException {
        String trueFileName = file.getOriginalFilename();
        assert trueFileName != null;
        //str.substring(int start)  截取字符串从start索引开始到该字符串结束的内容
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
        String fileName = IdUtil.simpleUUID() + suffix;
        String paramImgFile = Base64.encode(file.getBytes());

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", GiteeImgBedConfig.ACCESS_TOKEN);
        paramMap.put("message", GiteeImgBedConfig.CREATE_REPOS_MESSAGE);
        paramMap.put("content", paramImgFile);
        String targetDir = GiteeImgBedConfig.IMG_FILE_DEST_PATH + fileName;
        String requestUrl = String.format(GiteeImgBedConfig.CREATE_REPOS_URL + "/" + fileName, GiteeImgBedConfig.OWNER,
                GiteeImgBedConfig.REPO_NAME, targetDir);
        String resultJson = HttpUtil.post(requestUrl, paramMap);

        System.out.println("resultJson1 = " + resultJson);
        if (JSONUtil.parseObj(resultJson).getObj("commit") != null) {
            return GiteeImgBedConfig.GITPAGE_REQUEST_URL + fileName;
        }
        return null;

    }
}
