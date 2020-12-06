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
   // private IdTextView idtextDisplay;

    public YouAreEll (MessageController m, IdController j,TransactionController t) {
        // used j because i seems awkward
        this.msgCtrl = m;
        this.idCtrl = j;
        this.transactionController=t;
    }

    public static void main(String[] args) throws IOException {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(new MessageController(), new IdController() ,new TransactionController());

        //   String getIdsURL = urlhandler.MakeURLCall("/ids", "GET", "{\"userid\": ...}");

       //Calling to display the ids
         urlhandler.MakeURLCall("/ids", "GET", "");
        urlhandler.MakeURLCall("/messages", "GET", "");
        urlhandler.MakeURLCall("/ids", "POST", "");
        urlhandler.MakeURLCall("/ids", "PUT", "");
       // System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
        }

//    public String get_ids() {
//        return MakeURLCall("/ids", "GET", "");
//    }
//
//    public String get_messages() {
//        return MakeURLCall("/messages", "GET", "");
//    }

    public String MakeURLCall(String mainurl, String method, String jpayload) throws IOException {
        if (mainurl.equals("/ids")) {
               if(method.equalsIgnoreCase("GET")) {
                String response = transactionController.get(mainurl);
                ArrayList<Id> idsList = idCtrl.getIds(response);
                System.out.println(" The Users Id List is as below:");
                for (int i = 0; i < 20; i++) {
                    System.out.println(new IdTextView(idsList.get(i)).toString());
                   }
                 }
            else if(method.equalsIgnoreCase("POST"))
            {
                transactionController.post(mainurl);
            }if(method.equalsIgnoreCase("PUT"))
            {
                String response = transactionController.get(mainurl);
                ArrayList<Id> idsList = idCtrl.getIds(response);
                Random random= new Random();
                int index =random.nextInt(idsList.size());
                idsList.get(index).setName("TESTING PUT RANDOM");
                String body= idsList.get(index).toString();
                transactionController.put(mainurl,body);
            }

        }
        else if (mainurl.equals("/messages")){

            String response = transactionController.get(mainurl);
            ArrayList<Message> idsMessage = msgCtrl.getMessages(response);

            System.out.println("\nThe Message Id List is as below:");
            for (int i = 0; i < idsMessage.size(); i++) {
                System.out.println(new MessageTextView(idsMessage.get(i)).toString());
            }
        }
        return "nada";
    }
}
