package models;

/* 
 * POJO for an Id object
 */
public class Id {

    String userid;
    //String githubId;
    String name;

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    String github;

    public Id() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Id (String name, String githubId) {
        this.name=name;
        this.userid =githubId;
    }

}