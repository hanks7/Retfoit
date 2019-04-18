package com.hanks.retfoit.bean;

import java.io.Serializable;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mdhdatasyncc.base.BaseBean
 * @time 2018/10/24 16:23
 * @description 请填写描述
 */
public class BaseBean implements Serializable {

    /**
     * success : 0
     * msgid : 1000501
     * msg : 请求接口不存在
     */

    private String success;
    private String msgid;
    private String msg;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
