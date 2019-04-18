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
     * 消息 Msg
     */
    public String M;
    /**
     * 成功标志 Flag
     */
    public boolean F;

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }

    public boolean isF() {
        return F;
    }

    public void setF(boolean f) {
        F = f;
    }

    /**
     * 判断 否有错误提示
     *
     * @return true  有错  false  无错
     */
    public boolean isError() {
        if (getM() == null) {
            return false;
        }
        if (!getM().contains("|")) {
            return false;
        }
        if (getM().contains("|-980|-100")) {
            return true;
        }
        if (getM().contains("配置不符合")) {
            return true;
        }
        if (getErrorCode() < -500 && getErrorCode() != -900) {
            return true;
        }
        if (getErrorCode() ==-101) {
            return true;
        }
        return false;
    }

    /**
     * 一定要和 isError() 配合使用
     * @return
     */
    public int getErrorCode() {
        if (getM() == null) {
            return 200;
        }
        if (!getM().contains("|")) {
            return 200;
        }
        return Integer.valueOf(getM().substring(getM().lastIndexOf("|") + 1));
    }

}
