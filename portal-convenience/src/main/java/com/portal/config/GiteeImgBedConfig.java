package com.portal.config;

/**
 * @ClassName: GiteeImgBedConfig
 * @Description: 码云图床的常量配置类
 **/


import cn.hutool.core.date.DateUtil;

import java.util.Date;

public interface GiteeImgBedConfig {

    /**
     * 码云分配的私人令牌Token
     */
    String ACCESS_TOKEN = "841326b7fc221b233762098687876a1a";

    /**
     * 码云用户名
     */
    String OWNER = "rzj2121";

    /**
     * 仓库名称
     */
    String REPO_NAME = "portal";

    /**
     * 上传图片的message
     */
    String CREATE_REPOS_MESSAGE = "(ง ˙o˙)ว";

    /**
     * 文件前缀
     */
    String IMG_FILE_DEST_PATH = DateUtil.format(new Date(), "yyyy-MM-dd") + "/";

    /**
     * 新建文件URL
     * 第一个 %s =>仓库所属空间地址(owner)
     * 第二个 %s => 仓库路径(repo)
     * 第三个 %s => 文件的路径(path)
     */
    String CREATE_REPOS_URL = "https://gitee.com/api/v5/repos/rzj2121/portal/contents/img";

    /**
     * GitPage请求路径
     */
    String GITPAGE_REQUEST_URL = "https://gitee.com/" + OWNER + "/" + REPO_NAME + "/raw/master/img/";

}
