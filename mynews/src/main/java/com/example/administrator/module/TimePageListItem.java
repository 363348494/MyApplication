package com.example.administrator.module;

/**
 * Created by Administrator on 2017-04-10.
 */

public class TimePageListItem {

    private String time;
    private String title;
    private String imgUrl;
    private String contentUrl;

    public TimePageListItem(String title, String time, String imgUrl, String contentUrl) {
        this.title = title;
        this.time = time;
        this.imgUrl = imgUrl;
        this.contentUrl = contentUrl;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    @Override
    public String toString() {
        return "TimePageListItem{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", contentUrl='" + contentUrl + '\'' +
                '}';
    }
}
