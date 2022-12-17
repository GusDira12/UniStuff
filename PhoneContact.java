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
class Messages{
    public static void startUp(){
        Messages.splitter();
        System.out.println("Hello! Welcome to PhoneContact, a Java-based TUI program that is essentially based on the contact application available in all range of devices.");
        System.out.println("This gets a bit complex, but throughout the program I'll try my best to help you whether it's an error, or anything else.");
        
        System.out.println("\nAll of this is available at: https://github.com/GusDira12/UniStuff/HCI/PhoneContact.java (any feedback is welcome)");

        System.out.println("\nLet's get started! Below you can type a command at the home section. Why not start with 'help' to see all the commands available?");
        Messages.splitter();
    }

    public static void atHome(){
        System.out.println("This is the home section! You'll be directed here whenever you're done with a command or an error happened.\n");
    }

    public static void addOption(){
        System.out.println("Would you like to add a bunch of information for your contact, just the name, or a group?");
        System.out.println("Type 'all' if you want to do the first option, 'name' to only add the name, or 'group' to add a group/tag. don't worry, you can edit them.");
    }

    public static void addAll(){
        System.out.println("You decided to add all info at once, which are name, number, email, and group! If you want to leave it empty, type '-'.");
        System.out.println("You can't have contacts of the same name, and you can only have numbers for your number info.");
        System.out.println("If there are no groups or the group name you enter doesn't exist, you can have the option to add it or leave it empty instead.");
    }

    public static void addName(){

    }

    public static void addGroupOption(){

    }

    public static void addGroup(){

    }

    public static void remove(){

    }

    public static void searchOption(){

    }

    public static void searchAll(){

    }

    public static void searchName(){

    }

    public static void searchGroup(){

    }

    public static void showOption(){

    }

    public static void helpInput(){
        System.out.println("either this is your first time using this program or you forget the commands. anyways, here you go:");
        System.out.println("- add: lets you create a new contact or group, or both.");
        System.out.println("- remove: deletes a specific content of a contact, or remove a contact entirely.");
        System.out.println("- edit: change an information of a contact! you can also make an information empty which will remove that information.");
        System.out.println("- search: searches a specific contact or a group of contacts, or both if that's your thing.");
        System.out.println("- show: shows all contacts or only contacts in a group. only a group, I can only do one or all, sorry.");
        System.out.println("- help: this! type this while you're at home.");
        System.out.println("- exit: exits the program. I'll have to confirm whether you actually want to exit or not as warning.");
    }

    public static void helpAdd(){

    }

    public static void helpRemove(){

    }

    public static void helpEdit(){

    }

    public static void helpSsearch(){

    }

    public static void helpShow(){

    }

    public static void exitOption(){
        
    }

    public static void exitCancel(){

    }

    public static void exitConfirm(){
        
    }

    public static void errorInput(){
        System.out.println("the command you entered doesn't exist. if you're lost, check the help section depending on where you are.");
    }

    public static void errorNaming(){
        System.out.println("name you entered is either invalid or already exists. please type a new name.");
    }

    public static void errorNumber(){
        System.out.println("hm? you can only have numbers in the number info.");
        System.out.println("you wouldn't type any other character while trying to call someone, would you?");
    }

    public static void splitter(){
        System.out.println("----------------------------------------------------------------");
    }

    public static void insertInput(){
        System.out.print("hey! type your input here: ");
    }
}
