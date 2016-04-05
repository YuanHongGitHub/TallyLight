package com.qq281982108.sqlitedemo;

import java.util.Date;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-05 20:18
 * 类名：Comment
 * 修改备注：
 */
public class Comment {
    private int id;
    private String content;

    private Date publishDate;

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
