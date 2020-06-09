package cn.yohane.community.dto;

import lombok.Data;

/**
 * Created by SuwaKanan on 2020/06/08
 */
// @Data是lombok自动生成getter和setter
//@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
    // private String avatarUrl; // fastjson可以把带下划线的转成驼峰的



    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
