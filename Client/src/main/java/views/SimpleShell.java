package views;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import models.Message;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {
    static Boolean found=false;
    private static IdController idCtrl;
    private static MessageController msgCtrl;
    private static TransactionController transactionController;


    public static void main(String[] args) throws java.io.IOException {
        transactionController=new TransactionController();
        msgCtrl=new MessageController();
        YouAreEll webber = new YouAreEll(new MessageController(), new IdController(),new TransactionController());
        String results="";
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            displayCommandsMenu();
            System.out.println("cmd? ");
            commandLine = console.readLine();
            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }
            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }
                // Specific Commands.
                // ids
                if (list.contains("ids")) {
                    if (list.get(0).equals("ids") && list.size() == 1) {
                        results = webber.get_ids();
                        SimpleShell.prettyPrintIds(results);
                        continue;
                    } else {
                        if (list.get(0).equals("ids") && list.size() == 3) {
                            ArrayList<Id> idsList = webber.getIds("/ids");
                            for (int i = 0; i < idsList.size(); i++) {
                                found=false;
                                if (idsList.get(i).getGithub().equalsIgnoreCase(list.get(2))) {
                                    Id IdForPut = webber.putIds( idsList.get(i).getUserid(),list.get(1), list.get(2));
                                    System.out.println("\nPUT ID on the server:");
                                    SimpleShell.prettyPrintIdsAsObject(IdForPut);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                Id Idreturned = webber.postIds(list.get(1), list.get(2));
                                System.out.println("\nPosted ID on the server:");
                                SimpleShell.prettyPrintIdsAsObject(Idreturned);
                                continue;
                            }
                        }
                    }
                }

                // messages
                if (list.contains("messages")) {
                    if (list.get(0).equals("messages") && list.size() == 1) {
                        results = webber.get_messages();
                        SimpleShell.prettyPrintMessages(results);
                        continue;
                    } else {
                        if (list.get(0).equals("messages") && list.size() == 2) {
                            String response = transactionController.getData("/ids/" + list.get(1) + "/messages");
                            ArrayList<Message> idsMessage = msgCtrl.getMessages(response);
                            for (int i = 0; i < idsMessage.size(); i++) {
                                System.out.println(new MessageTextView(idsMessage.get(i)).toString());
                            }
                        }
                    }

                }
                //POST and PUT for messages are pending , will complete it later
//                if (list.contains("send")) {
//                    String fromId = list.get(1);
//                    String message = list.get(2);
//                    String toId = list.get(4);
//                    int messageEnd = 2;
//                    for (int i=0; i<list.size(); i++){
//                        if (list.get(i).equals("to")) messageEnd = i-1;
//                    }
//                    if (messageEnd > 2){
//                        for (int i=3; i<=messageEnd; i++){
//                            message += list.get(i);
//                        }
//                    }
//                    webber.postMessagesURLCall(fromId, toId, message);
//                    continue;
//                }
                // you need to add a bunch more.

                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // wait, wait, what curiousness is this?
                Process process = pb.start();

                //obtain the input stream
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //read output of the process
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
                br.close();
            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }

    }

    private static void displayCommandsMenu() {
        String get = "\n******************COMMAND OPTIONS********************:\nTo get list of all users, type:      | ids |\nTo get list of all messages, type:   | messages |";
        String postId = "\nTo create a new user id, type:       | ids | your-name | your-github |";
        String putId ="\nTo change name for github, type:     | ids | new-name | same-github |";
        String postMessage = "\nTo post message, type:               | send | your-github | 'your message' | to | recipient-github |";
        String historyExit = "\nTo display shell history, type:      | history |\nTo exit, type:                       | exit |";
        //need to add one for get messages spf to UserID
        //and one for getting messages between 2 users
        System.out.println(get + postId + putId + postMessage + historyExit);
    }

    private static void prettyPrintIdsAsObject(Id idReturned) {
        IdTextView toView= new IdTextView(idReturned);
        System.out.println(toView.toString());
    }
    public static void prettyPrintIds(String output) throws JsonProcessingException {
        // yep, make an effort to format things nicely, eh?
        idCtrl=new IdController();
        ArrayList<Id> idsList = idCtrl.getIds(output);
        System.out.println(" The Users Id List is as below:");
        for (int i = 0; i <idsList.size(); i++) {
            System.out.println(new IdTextView(idsList.get(i)).toString());
        }
    }

    public static void prettyPrintMessages(String output) throws JsonProcessingException {
        // yep, make an effort to format things nicely, eh?
        msgCtrl=new MessageController();
        ArrayList<Message> idsMessage = msgCtrl.getMessages(output);
        System.out.println("\nThe Message Id List is as below:");
        for (int i = 0; i < idsMessage.size(); i++) {
            System.out.println(new MessageTextView(idsMessage.get(i)).toString());
        }

    }
}