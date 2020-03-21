package com.corp.sso.service;

import com.corp.oa.util.G;
import com.corp.sso.utils.HttpUtil;
import com.corp.sso.utils.PropUtil;
import com.corp.sso.utils.SignUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RegisterU8C {

    protected final Log logger = LogFactory.getLog(getClass());

    private static String Account_code = "U8cloud";
    private static String Ds_name  = "U8cloud";

    public static String serviceUrl;

    static {
        Properties pt = PropUtil.getProperties("/system-project.properties");
        serviceUrl = pt.getProperty("callback_url");
    }

    public String register(String userCode, String pkCrop) throws Exception {

        if (G.isNvl(serviceUrl)) {
            logger.error("[SSO]：register 调用地址为空");
            return null;
        }
        // 注册地址
        String url = serviceUrl + "service/RegisterServlet";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usercode", userCode);
        // key用于加密验证
        params.put("key", SignUtil.md5(userCode));
        params.put("accountcode", Account_code);
        params.put("dsname", Ds_name);
        params.put("pkcorp", pkCrop);
//        params.put("hidelogin", "y");
        logger.info("[SSO]: url：" + url);
        logger.info("[SSO]: params：" + params);

        String result = HttpUtil.get(url, params);
        logger.info("[SSO]: register结果：" + result);

        return result;
    }

}
