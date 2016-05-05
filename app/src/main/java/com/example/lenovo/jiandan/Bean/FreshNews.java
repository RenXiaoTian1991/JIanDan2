package com.example.lenovo.jiandan.Bean;


import java.io.Serializable;
import java.util.List;

/**
 * 新鲜事
 * Created by zhaokaiqiang on 15/4/24.
 */
public class FreshNews implements Serializable {

    public static final String URL_FRESH_NEWS = "http://jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1&page=";

    /**
     * id : 74405
     * url : http://jandan.net/2016/01/25/social-media-friends.html
     * title : 都市谣言：网络上的朋友比真实世界里多
     * date : 2016-01-25 14:00:27
     * tags : [{"id":847,"slug":"%e5%bf%83%e7%90%86%e5%ad%a6","title":"心理学","description":"","post_count":300}]
     * author : {"id":563,"slug":"siyi","name":"蛋花","first_name":"","last_name":"","nickname":"蛋花","url":"","description":""}
     * comment_count : 9
     * custom_fields : {"thumb_c":["http://tankr.net/s/custom/80EM.jpg"],"views":["19"]}
     */

    private int id;
    private String url;
    private String title;
    private String date;
    /**
     * id : 563
     * slug : siyi
     * name : 蛋花
     * first_name :
     * last_name :
     * nickname : 蛋花
     * url :
     * description :
     */

    private AuthorEntity author;
    private int comment_count;
    private CustomFieldsEntity custom_fields;
    /**
     * id : 847
     * slug : %e5%bf%83%e7%90%86%e5%ad%a6
     * title : 心理学
     * description :
     * post_count : 300
     */

    private List<TagsEntity> tags;

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setCustom_fields(CustomFieldsEntity custom_fields) {
        this.custom_fields = custom_fields;
    }

    public void setTags(List<TagsEntity> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public int getComment_count() {
        return comment_count;
    }

    public CustomFieldsEntity getCustom_fields() {
        return custom_fields;
    }

    public List<TagsEntity> getTags() {
        return tags;
    }

    public static class AuthorEntity {
        private int id;
        private String slug;
        private String name;
        private String first_name;
        private String last_name;
        private String nickname;
        private String url;
        private String description;

        public void setId(int id) {
            this.id = id;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getSlug() {
            return slug;
        }

        public String getName() {
            return name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getNickname() {
            return nickname;
        }

        public String getUrl() {
            return url;
        }

        public String getDescription() {
            return description;
        }
    }

    public static class CustomFieldsEntity {
        private List<String> thumb_c;
        private List<String> views;

        public void setThumb_c(List<String> thumb_c) {
            this.thumb_c = thumb_c;
        }

        public void setViews(List<String> views) {
            this.views = views;
        }

        public List<String> getThumb_c() {
            return thumb_c;
        }

        public List<String> getViews() {
            return views;
        }
    }

    public static class TagsEntity {
        private int id;
        private String slug;
        private String title;
        private String description;
        private int post_count;

        public void setId(int id) {
            this.id = id;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPost_count(int post_count) {
            this.post_count = post_count;
        }

        public int getId() {
            return id;
        }

        public String getSlug() {
            return slug;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getPost_count() {
            return post_count;
        }
    }
}
