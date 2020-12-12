package youareell;

import controllers.*;
import models.*;
import views.IdTextView;
import views.MessageTextView;

import java.io.IOException;
import java.util.ArrayList;

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
//        urlhandler.MakeURLCall("/ids", "POST", "");
//        urlhandler.MakeURLCall("/ids", "PUT", "");

    }

    public String get_ids() throws IOException {
        return transactionController.getData("/ids");
    }

    public String get_messages() throws IOException {
        return transactionController.getData("/messages");
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

            String response = transactionController.getData(mainurl);
            ArrayList<Message> idsMessage = msgCtrl.getMessages(response);

            System.out.println("\nThe Message Id List is as below:");
            for (int i = 0; i < idsMessage.size(); i++) {
                System.out.println(new MessageTextView(idsMessage.get(i)).toString());
            }
        }
        return "nada";
    }

    public ArrayList<Id> getIds(String mainurl) throws IOException {
        String response = transactionController.getData(mainurl);
        ArrayList<Id> idsList = idCtrl.getIds(response);
        return idsList;
    }

    public Id postIds(String name, String yourGitHub) throws IOException {
        Id idOBject = new Id(name, "-", yourGitHub);
        return idCtrl.postId(idOBject);

    }

    public Id putIds(String userId, String userName, String gitHub) throws IOException {
        Id idOBject = new Id(userName, userId, gitHub);
        return idCtrl.putId(idOBject);



        //return null;
    }

//    public String postMessagesURLCall(String from, String to, String message) throws IOException {
//        //return transactionController.post(from, to, message);
//        getIds("/Ids"+from+)
//        return msgCtrl.postMessage(from,to,message);
//
//    }


    }


