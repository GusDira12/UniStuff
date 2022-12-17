import java.util.Scanner;
import java.util.ArrayList;

/*
 * Phone contact, basically. HCI assignment (A TUI program based on phone contaact).
 * Everything is in here, no .txt or anything whether for messages or anything else.
 * Everything is public, because I'm too lazy to implement with private (such a waste).
 * Future note: Might just have one with .txt, not sure.
 * 
 * In here, informations available are:
 * - Name (must have, no exceptions)
 * - Number (..duh)
 * - Email (addition)
 * - Group (tags, basically)
 * 
 * Commands are:
 * - Add (either name or all. can be left empty OUTSIDE name, it'll just make it "-" for remove purpose)
 * - Remove (deleted the WHOLE contact)
 * - Edit (edits any info, can be empty) [MAY NOT BE IMPLEMENTED]
 * - Search (based on name (substring) or group (can be empty), or both..)
 * - Show (all contacts available, you can choose to show all or a specific group)
 * 
 * Conditions:
 * - Any empty info is typed as "-". Why? Exists for convenient purpose, character choice however I'm not sure.
 * - MUST exist at least a name. Phone number and all can come later. (I mean, yeah?)
 * - Same name cannot exist. It'll just make you re-enter the name. For every other info however can be the same.
 * - For phone, it MUST be number only. Outside that throws you into re-enter. [MAY NOT BE IMPLEMENTED]
 * - Trying to add a contact into a group yet there's none automatically forces you to make one.
 * - Editing a non-existing information throws you an option to add that information, or go home. [MAY NOT BE IMPLEMENTED]
 */

// here lies the main commands.
interface CMDList{
    public void home(String input);

    public void add();

    public void remove();

    public void edit();

    public void search();

    public void show();

    public void help();

    public void exit();
}

// class which actually runs the program.
public class PhoneContact{
    public ArrayList<String> nameList = new ArrayList<>(),
        phoneList = new ArrayList<>(), emailList = new ArrayList<>(), groupList = new ArrayList<>(); // for each contact.
    public ArrayList<String> groupContact = new ArrayList<>(); // group alone.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Messages.startUp();
        Messages.atHome();
        Messages.insertInput();
        new Commands().home();

        sc.close();
    }
}

// commands list which implements the interface.
class Commands{
    Scanner sc = new Scanner(System.in);

    PhoneContact contact = new PhoneContact();

    // where all the input exists. calls other functions.
    public void home(){
        String input = sc.next().toLowerCase();
        while (true){
            Messages.splitter();

            if (input.equals("home")){
                Messages.atHome();
                Messages.insertInput();

                input = sc.next();
            } else if (input.equals("add")){
                Messages.addOption();

                while (true){
                    Messages.splitter();
                    Messages.insertInput();

                    String addOption, name, num, email, group;
                    addOption = sc.next().toLowerCase();

                    Messages.splitter();

                    if (addOption.equals("all")){
                        Messages.addAll();
    
                        while (true){
                            Messages.splitter();
                            Messages.insertInput();
                            name = sc.next().toLowerCase();
                            num = sc.next().toLowerCase();
                            email = sc.next().toLowerCase();
                            group = sc.next().toLowerCase();

                            if (contact.nameList.indexOf(name) != -1){
                                Messages.splitter();
                                Messages.errorNaming();

                                continue;
                            } else if (!num.matches("[0-9]+")){
                                Messages.splitter();
                                Messages.errorNumber();

                                continue;
                            } else if (contact.groupList.indexOf(group) != -1){
                                Messages.splitter();
                                Messages.errorNaming();

                                continue;
                            } else{
                                if (contact.groupContact.isEmpty() || !contact.groupContact.contains(group)){
                                    Messages.addGroupOption();

                                    while (true){
                                        Messages.splitter();
                                        Messages.insertInput();

                                        String groupOption;
                                        groupOption = sc.next().toLowerCase();

                                        if (groupOption.equals("yes")){
                                            contact.groupContact.add(group);

                                            break;
                                        } else if (groupOption.equals("no")){
                                            group = "-";

                                            break;
                                        } else{
                                            Messages.splitter();
                                            Messages.errorInput();

                                            continue;
                                        }
                                    }
                                }

                                contact.nameList.add(name);
                                contact.phoneList.add(num);
                                contact.emailList.add(email);
                                contact.groupList.add(group);
                            }

                            break;
                        }
                    } else if (addOption.equals("name")){
                        Messages.addName();

                        while (true){
                            Messages.splitter();
                            Messages.insertInput();

                            name = sc.next().toLowerCase();

                            if (!contact.nameList.contains(name)){
                                contact.nameList.add(name);
                                contact.phoneList.add("-");
                                contact.emailList.add("-");
                                contact.groupList.add("-");

                                break;
                            } else{
                                Messages.splitter();
                                Messages.errorNaming();
                            }
                        }
                    } else if (addOption.equals("group")){
                        Messages.addGroup();

                        while (true){
                            Messages.splitter();
                            Messages.insertInput();
                            
                            group = sc.next().toLowerCase();

                            if (!contact.nameList.contains(group)){
                                contact.groupContact.add(group);
                                
                                break;
                            } else{
                                Messages.splitter();
                                Messages.errorNaming();
                            }
                        }
                    } else{
                        Messages.errorInput();

                        continue;
                    }

                    break;
                }

                input = "home";
            } else if (input.equals("remove")){
                Messages.remove();

                while (true){
                    Messages.splitter();
                    Messages.insertInput();

                    String remove;
                    remove = sc.next().toLowerCase();

                    if (!contact.nameList.contains(remove)){
                        Messages.splitter();
                        Messages.errorNaming();
                    } else{
                        int index = contact.nameList.indexOf(remove);

                        contact.nameList.remove(index);
                        contact.phoneList.remove(index);
                        contact.emailList.remove(index);
                        contact.groupList.remove(index);

                        break;
                    }
                }

                input = "home";
            } else if (input.equals("search")){
    
            } else if (input.equals("show")){
                
            } else if (input.equals("help")){
                Messages.helpInput();

                input = "home";
            } else if (input.equals("exit")){
                Messages.exitOption();
                Messages.insertInput();

                String exitOption = sc.next().toLowerCase();
                if (exitOption.equals("yes")){
                    break;
                } else if (exitOption.equals("no")){
                    Messages.exitCancel();

                    input = "home";
                } else{
                    Messages.errorInput();

                    input = "exit";
                }
            } else{
                Messages.errorInput();

                input = "home";
            }
        }
    }
    
    // below are all the functions.

    // public void add(){

    // }

    // public boolean addName(){

    // }

    // public boolean addGroup(){

    // }

    // public boolean addAll(){

    // }

    // public void remove(){

    // }

    // public boolean removeAll(){

    // }

    // public boolean removeOne(){

    // }

    // public boolean removeGroup(){

    // }

    // public void search(){

    // }

    // public boolean searchAll(){

    // }

    // public boolean searchName(){

    // }

    // public boolean searchGroup(){

    // }

    // public void show(){

    // }

    // public void showAll(){

    // }

    // public void showGroup(){

    // }

    // public void help(){

    // }

    // public void exit(){

    // }

    // public boolean exitConfirm(){

    // }
}

// message list here. it's all just text, nothing to worry about here.