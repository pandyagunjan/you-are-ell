package models;
import views.IdTextView;
/* 
 * POJO for an Id object
 */
public class Id {

    String userid;
    //String githubId;
    String name;
    String github;

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }


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

    public Id (String name, String userid,String github) {
        this.name=name;
        this.userid =userid;
        this.github=github;
    }
    @Override
    public String toString() {
        return "\n{" +
                "\n\t\"userid\": \"" + this.userid + "\"," +
                "\n\t\"name\": \"" + this.name + "\"," +
                "\n\t\"github\": \"" + this.github + "\"" +
                "\n}";
    }


}