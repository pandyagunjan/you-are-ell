package youareell;

import controllers.*;
import models.*;
import views.IdTextView;

import java.io.IOException;
import java.util.ArrayList;

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
           // idtextDisplay=new IdTextView();
            String response = transactionController.get(mainurl ,method);
            ArrayList<Id> idsList = idCtrl.getIds(response);
            System.out.println(" The Users Id List is as below:");
            for (int i = 0; i < idsList.size(); i++)
            {
              System.out.println(new IdTextView(idsList.get(i)).toString());
            }
        }
        else if (mainurl.equals("/messages")){
            // call transaction controller
            // transaction controller returns response
            // send response to MessageController
        }
        return "nada";
    }
}
