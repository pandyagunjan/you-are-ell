package views;

import models.Id;

public class IdTextView {

    private Id id;
    public IdTextView(Id idToDisplay) {
       this.id=idToDisplay;
    }
    @Override public String toString() {
        return String.format("Userid: %s Name : %s GitHubId:%s",id.getUserid(),id.getName(),id.getGithub());
    }
}