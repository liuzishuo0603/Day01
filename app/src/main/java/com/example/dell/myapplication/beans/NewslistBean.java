package com.example.dell.myapplication.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by DELL on 2019/5/28.
 */
@Entity
public class NewslistBean {
    /**
     * ctime : 2019-05-28
     * title : 国家放大招！还没买车的恭喜了，又能省一笔，7月1日起实施！
     * description : 车澎湃
     * picUrl : http://mmbiz.qpic.cn/mmbiz_jpg/TBLBicGd3wY5TT2ibyXLNDXXb7QYDW6wyeRXHu7jibSc94GibEv2AoeIvoViboEKs5TAXebLKwW8rgO8syh9y2IPxGQ/0?wx_fmt=jpeg
     * url : https://mp.weixin.qq.com/s?src=11&amp;timestamp=1559041207&amp;ver=1634&amp;signature=YTKR89r1KDJw6hydlocQV7cGOYovRN1Naij*U-S0sob1TImTCoEVC*tcUN970g3tsE03aVQ6i0ITuYuQreN7QOY0elfGVABHZLTY-XEmyZM9KYJNEUTwpV55I9nC0ipR&amp;new=1
     */
    @Id
    @Property(nameInDb = "iid")
    private Long id;
    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    @Generated(hash = 1402622097)
    public NewslistBean(Long id, String ctime, String title, String description, String picUrl, String url) {
        this.id = id;
        this.ctime = ctime;
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }

    @Generated(hash = 923354944)
    public NewslistBean() {
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
