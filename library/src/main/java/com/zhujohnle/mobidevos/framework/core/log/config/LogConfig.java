package com.zhujohnle.mobidevos.framework.core.log.config;


public class LogConfig {
    //标签
    private String Tag = "FtLog";

    //是否显示线程信息
    private boolean isShowThreadInfo;


    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public boolean isShowThreadInfo() {
        return isShowThreadInfo;
    }

    public void setShowThreadInfo(boolean showThreadInfo) {
        isShowThreadInfo = showThreadInfo;
    }
}
