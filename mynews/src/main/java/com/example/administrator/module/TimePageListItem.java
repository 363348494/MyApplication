package com.example.administrator.module;

/**
 * Created by Administrator on 2017-04-10.
 */

public class TimePageListItem {

    private String time;
    private String title;
    private String imgurl;
    private String contenturl;

    public TimePageListItem(String title, String time, String imgurl, String contenturl) {
        this.title = title;
        this.time = time;
        this.imgurl = imgurl;
        this.contenturl = contenturl;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getContenturl() {
        return contenturl;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }
}
