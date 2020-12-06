package youareell;

import controllers.*;
import models.*;
import views.IdTextView;
import views.MessageTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class YouAreEll {

    private MessageController msgCtrl;
    private static IdController idCtrl;
    private TransactionController transactionController;


    public YouAreEll(MessageController m, IdController j, TransactionController t) {
        // used j because i seems awkward
        this.msgCtrl = m;
        this.idCtrl = j;
        this.transactionController = t;
    }

    public static void main(String[] args) throws IOException {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(new MessageController(), new IdController(), new TransactionController());

        //   String getIdsURL = urlhandler.MakeURLCall("/ids", "GET", "{\"userid\": ...}");

        //Calling to display the ids
//        urlhandler.MakeURLCall("/ids", "GET", "");
//        urlhandler.MakeURLCall("/messages", "GET", "");
        urlhandler.MakeURLCall("/ids", "POST", "");
        // urlhandler.MakeURLCall("/ids", "PUT", "");

    }

    public String get_ids() throws IOException {
        return transactionController.get("/ids");
    }

    public String get_messages() throws IOException {
        return transactionController.get("/messages");
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) throws IOException {
        if (mainurl.equals("/ids")) {
            if (method.equalsIgnoreCase("GET")) {
                ArrayList<Id> idsList = getIds(mainurl);
                System.out.println(" The Users Id List is as below:");
                for (int i = 0; i < 20; i++) {
                    System.out.println(new IdTextView(idsList.get(i)).toString());
                }
            } else if (method.equalsIgnoreCase("POST")) {
                //   postIds(mainurl);
            }
            if (method.equalsIgnoreCase("PUT")) {
                // transactionController.put(mainurl,body);
            }
        } else if (mainurl.equals("/messages")) {

            String response = transactionController.get(mainurl);
            ArrayList<Message> idsMessage = msgCtrl.getMessages(response);

            System.out.println("\nThe Message Id List is as below:");
            for (int i = 0; i < idsMessage.size(); i++) {
                System.out.println(new MessageTextView(idsMessage.get(i)).toString());
            }
        }
        return "nada";
    }

    public ArrayList<Id> getIds(String mainurl) throws IOException {
        String response = transactionController.get(mainurl);
        ArrayList<Id> idsList = idCtrl.getIds(response);
        return idsList;
    }

    public String postIds(String mainurl, String name, String yourGitHub) throws IOException {
        return transactionController.post(mainurl, name, yourGitHub);

    }

    public String putIds(String mainurl, String putBody) throws IOException {
        // String putBody="";
        return transactionController.put(mainurl, putBody);

    }

    public Boolean decidePutORPostIds(String name,String gitHubId) throws IOException {
        Boolean found = false;
        String results="";
        ArrayList<Id> idsList = this.getIds("/ids");
        for (int i = 0; i < idsList.size(); i++) {
            if (idsList.get(i).getGithub().equalsIgnoreCase(gitHubId)) {
                idsList.get(i).setName(name);
                String putBody = idsList.get(i).toString();
                results = putIds("/ids", putBody);
                System.out.println(results);
                found = false;
            }
        }
        return found;
    }
}
