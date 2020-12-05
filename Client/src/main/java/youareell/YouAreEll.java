package youareell;

import controllers.*;

import java.io.IOException;

public class YouAreEll {

    private MessageController msgCtrl;
    private static IdController idCtrl;

    public YouAreEll (MessageController m, IdController j) {
        // used j because i seems awkward
        this.msgCtrl = m;
        this.idCtrl = j;
    }

    public static void main(String[] args) throws IOException {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(new MessageController(), new IdController());
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));

        String getIdsURL = urlhandler.MakeURLCall("/ids", "GET", "{\"userid\": ...}");
        idCtrl.getIds(getIdsURL);
      //  System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String get_ids() {
        return MakeURLCall("/ids", "GET", "");
    }

    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        return "http://zipcode.rocks:8085"+ mainurl + " "+method+" "+jpayload ;
    }
}
