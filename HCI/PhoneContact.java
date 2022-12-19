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
 * Conditions:
 * - Any empty info is typed as "-". Why? Exists for convenient purpose, character choice however I'm not sure.
 * - MUST exist at least a name. Phone number and all can come later. (I mean, yeah?)
 * - Same name cannot exist, same with group. It'll just make you re-enter the name. For every other info however can be the same.
 * - For phone, it MUST be number only. Outside that throws you into re-enter.
 * - Trying to add a contact into a group yet there's none automatically asks you to make one.
 */

// here lies the main commands.
interface CMDList{
    public void home();

    public static void add(){

    }

    public static void remove(){

    }

    public static void edit(){

    }

    public static void search(){

    }

    public static void show(){

    }

    public static void help(){

    }

    public static boolean exit(){
        return true;
    }
}

// class which actually starts the program.
public class PhoneContact{
    public ArrayList<String> nameList = new ArrayList<>(),
        phoneList = new ArrayList<>(), emailList = new ArrayList<>(), groupList = new ArrayList<>(); // for each contact.
    public ArrayList<String> groupContact = new ArrayList<>(); // group alone.

    public static void main(String[] args) {
        Messages.startUp();
        Messages.atHome();
        Messages.insertInput();
        new Commands().home();
    }
}

/*
 * all the commands from the interface.
 * this is also where the whole program lies on.
 */
class Commands implements CMDList{
    private static Scanner sc = new Scanner(System.in);

    static PhoneContact contact = new PhoneContact();

    // where all the input exists. calls other functions.
    public void home(){
        String input;
        input = sc.next().toLowerCase();

        while (true){
            if (input.equals("home")){
                Messages.splitter();
                Messages.atHome();
                Messages.insertInput();

                input = sc.next();

                continue;
            } else if (input.equals("add")){
                Commands.add();
            } else if (input.equals("remove")){
                if (contact.nameList.isEmpty()){
                    Messages.splitter();
                    Messages.errorAmount();
                } else{
                    Commands.remove();
                }
            } else if (input.equals("search")){
                if (contact.nameList.isEmpty()){
                    Messages.splitter();
                    Messages.errorAmount();
                } else{
                    Commands.search();
                }
            } else if (input.equals("show")){
                if (contact.nameList.isEmpty()){
                    Messages.splitter();
                    Messages.errorAmount();
                } else{
                    Commands.show();
                }
            } else if (input.equals("help")){
                Commands.help();
            } else if (input.equals("exit")){
                if (Commands.exit()){
                    break;
                }
            } else{
                Messages.splitter();
                Messages.errorInput();
                Messages.atHome();
                Messages.insertInput();

                input = sc.next();

                continue;
            }

            input = "home";
        }
    }
    
    // below are all the functions.
    public static void add(){
        Messages.splitter();

        while (true){
            Messages.addOption();
            Messages.insertInput();

            String addOption;
            addOption = sc.next().toLowerCase();

            if (addOption.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                break;
            }

            Messages.splitter();

            if (addOption.equals("all")){
                Commands.addAll();
            } else if (addOption.equals("name")){
                Commands.addName();
            } else if (addOption.equals("number")){
                Commands.addNumber();
            } else if (addOption.equals("email")){
                Commands.addEmail();
            } else if (addOption.equals("group")){
                Commands.addGroup();
            } else{
                Messages.errorInput();

                continue;
            }

            break;
        }
    }

    public static boolean addAll(){
        while (true){
            Messages.addAll();
            Messages.insertInput();

            String name, num, email, group;
            name = sc.next().toLowerCase();

            if (name.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            num = sc.next().toLowerCase();
            email = sc.next().toLowerCase();
            group = sc.next().toLowerCase();

            Messages.splitter();

            if (contact.nameList.contains(name)){
                Messages.errorNaming();
            } else if (!num.matches("[0-9]+")){
                Messages.errorNumber();
            } else{
                if (!contact.groupContact.contains(group)){
                    while (true){
                        Messages.addGroupOption();
                        Messages.insertInput();

                        String groupOption;
                        groupOption = sc.next().toLowerCase();

                        if (groupOption.equals("exit")){
                            Messages.splitter();
                            Messages.exitEmergency();
            
                            return false;
                        }

                        Messages.splitter();

                        if (groupOption.equals("yes")){
                            contact.groupContact.add(group);
                        } else if (groupOption.equals("no")){
                            group = "-";
                        } else{
                            Messages.errorInput();

                            continue;
                        }

                        break;
                    }
                }

                System.out.format("Contact '%s' has been added!\n", name);

                contact.nameList.add(name);
                contact.phoneList.add(num);
                contact.emailList.add(email);
                contact.groupList.add(group);

                return true;
            }
        }
    }

    public static boolean addName(){
        while (true){
            Messages.addName();
            Messages.insertInput();

            String name;
            name = sc.next().toLowerCase();

            if (name.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (contact.nameList.contains(name)){
                Messages.errorNaming();
            } else{
                System.out.format("'%s' has been added!\n", name);

                contact.nameList.add(name);
                contact.phoneList.add("-");
                contact.emailList.add("-");
                contact.groupList.add("-");

                return true;
            }
        }
    }

    public static boolean addNumber(){
        while (true){
            Messages.addOtherName();
            Messages.insertInput();

            String name, num, addOption;
            name = sc.next().toLowerCase();

            if (name.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (!contact.nameList.contains(name)){
                while (true){
                    Messages.errorAdd();
                    Messages.insertInput();

                    addOption = sc.next().toLowerCase();

                    if (addOption.equals("exit")){
                        Messages.splitter();
                        Messages.exitEmergency();
        
                        return false;
                    }

                    Messages.splitter();

                    if (addOption.equals("yes")){
                        Commands.addName();
                    } else if (addOption.equals("no")){
                    } else{
                        Messages.errorInput();

                        continue;
                    }

                    break;
                }
            } else{
                while (true){
                    Messages.addNumber();
                    Messages.insertInput();

                    num = sc.next().toLowerCase();

                    if (num.equals("exit")){
                        Messages.splitter();
                        Messages.exitEmergency();
        
                        return false;
                    }

                    Messages.splitter();

                    if (!num.matches("[0-9]+")){
                        Messages.errorNumber();
                    } else{
                        System.out.format("Number '%s' of contact '%s' has been added!\n", num, name);

                        contact.phoneList.set(contact.nameList.indexOf(name), num);

                        return true;
                    }
                }
            }
        }
    }

    public static boolean addEmail(){
        while (true){
            Messages.addOtherName();
            Messages.insertInput();

            String name, email, addOption;
            name = sc.next().toLowerCase();

            if (name.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (!contact.nameList.contains(name)){
                while (true){
                    Messages.errorAdd();
                    Messages.insertInput();

                    addOption = sc.next().toLowerCase();

                    if (addOption.equals("exit")){
                        Messages.splitter();
                        Messages.exitEmergency();
        
                        return false;
                    }

                    Messages.splitter();

                    if (addOption.equals("yes")){
                        Commands.addName();
                    } else if (addOption.equals("no")){
                    } else{
                        Messages.errorInput();

                        continue;
                    }

                    break;
                }
            } else{
                Messages.addEmail();
                Messages.insertInput();

                email = sc.next().toLowerCase();

                if (email.equals("exit")){
                    Messages.splitter();
                    Messages.exitEmergency();
    
                    return false;
                }

                Messages.splitter();

                System.out.format("Email '%s' of contact '%s' has been added!\n", email, name);

                contact.emailList.set(contact.nameList.indexOf(name), email);

                return true;
            }
        }
    }

    public static boolean addGroup(){
        while (true){
            Messages.addGroup();
            Messages.insertInput();
                    
            String group;
            group = sc.next().toLowerCase();

            if (group.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (contact.groupContact.contains(group)){
                Messages.errorNaming();
            } else{
                System.out.format("Group '%s' has been added!\n", group);

                contact.groupContact.add(group);

                return true;
            }
        }
    }

    public static void remove(){
        Messages.splitter();
                
        while (true){
            Messages.removeOption();
            Messages.insertInput();

            String removeOption;
            removeOption = sc.next().toLowerCase();

            if (removeOption.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                break;
            }

            Messages.splitter();

            if (removeOption.equals("name")){
                Commands.removeAll();
            } else if (removeOption.equals("number")){
                Commands.removeNumber();
            } else if (removeOption.equals("email")){
                Commands.removeEmail();
            } else if (removeOption.equals("group")){
                if (contact.groupContact.isEmpty()){
                    Messages.errorAmount();
                } else{
                    Commands.removeGroup();
                }
            } else{
                Messages.errorInput();

                continue;
            }

            break;
        }

    }

    public static boolean removeAll(){
        while (true){
            Messages.removeAll();
            Messages.insertInput();

            String name;
            name = sc.next().toLowerCase();

            if (name.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (!contact.nameList.contains(name)){
                Messages.errorNaming();
            } else{
                int index = contact.nameList.indexOf(name);

                System.out.format("Contact '%s' has been deleted entirely.\n", name);

                contact.nameList.remove(index);
                contact.phoneList.remove(index);
                contact.emailList.remove(index);
                contact.groupList.remove(index);

                return true;
            }
        }
    }

    public static boolean removeNumber(){
        while (true){
            Messages.removeOther();
            Messages.insertInput();

            String name;
            name = sc.next().toLowerCase();

            if (name.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (!contact.nameList.contains(name)){
                Messages.errorNaming();
            } else{
                System.out.format("Number '%s' of contact '%s' has been deleted.\n", contact.phoneList.get(contact.nameList.indexOf(name)), name);

                contact.phoneList.set(contact.nameList.indexOf(name), "-");

                return true;
            }
        }
    }

    public static boolean removeEmail(){
        while (true){
            Messages.removeOther();
            Messages.insertInput();

            String name;
            name = sc.next().toLowerCase();

            if (name.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (!contact.nameList.contains(name)){
                Messages.errorNaming();
            } else{
                System.out.format("Email '%s' of contact '%s' has been deleted.\n", contact.emailList.get(contact.nameList.indexOf(name)), name);

                contact.emailList.set(contact.nameList.indexOf(name), "-");

                return true;
            }
        }
    }

    public static boolean removeGroup(){
        while (true){
            Messages.removeGroup();
            Messages.insertInput();

            String group;
            group = sc.next().toLowerCase();

            if (group.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (!contact.groupList.contains(group)){
                Messages.errorNaming();
            } else{
                System.out.format("Group '%s' has been deleted and any contact in the group has the group tag removed.\n", group);

                contact.groupContact.remove(group);

                for (int temp = 0;temp < contact.nameList.size();temp++){
                    if (contact.groupList.get(temp).equals(group)){
                        contact.groupList.set(temp, "-");
                    }
                }

                return true;
            }
        }
    }

    public static void search(){
        Messages.splitter();

        while (true){
            Messages.searchOption();
            Messages.insertInput();

            String searchOption;
            searchOption = sc.next().toLowerCase();

            if (searchOption.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                break;
            }

            Messages.splitter();

            if (searchOption.equals("name")){
                Commands.searchName();
            } else if (searchOption.equals("group")){
                Commands.searchGroup();
            } else{
                Messages.errorInput();

                continue;
            }

            break;
        }
    }

    public static boolean searchName(){
        while (true){
            Messages.searchName();
            Messages.insertInput();

            String name;
            name = sc.next().toLowerCase();

            if (name.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (!contact.nameList.contains(name)){
                System.out.format("Name '%s' doesn't exist.\n", name);

                return false;
            } else{
                System.out.format("Name '%s' exists.\n", name);

                return true;
            }
        }
    }

    public static boolean searchGroup(){
        while (true){
            Messages.searchGroup();
            Messages.insertInput();

            String group;
            group = sc.next().toLowerCase();

            if (group.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                return false;
            }

            Messages.splitter();

            if (!contact.groupContact.contains(group)){
                System.out.format("Group '%s' doesn't exist.\n", group);

                return false;
            } else{
                System.out.format("Group '%s' exists. Members are:\n", group);

                for (int temp = 0;temp < contact.nameList.size();temp++){
                    if (contact.groupList.get(temp).equals(group)){
                        System.out.println("| " + contact.nameList.get(temp));
                    }
                }

                return true;
            }
        }
    }

    public static void show(){
        Messages.splitter();

        while (true){
            Messages.showOption();
            Messages.insertInput();

            String showOption;
            showOption = sc.next().toLowerCase();

            if (showOption.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                break;
            }

            Messages.splitter();

            if (showOption.equals("all")){
                Commands.showAll();
            } else if (showOption.equals("name")){
                Commands.showName();
            } else if (showOption.equals("group")){
                if (contact.groupContact.isEmpty()){
                    Messages.errorAmount();
                } else{
                    Commands.showGroup();
                }
            } else{
                Messages.errorInput();

                continue;
            }

            break;
        }
    }

    public static void showAll(){
        System.out.println("Here are all the contacts that exist with format of '| [name] [number] [email] [group]' (If empty, it shows '-'):");

        for (int temp = 0;temp < contact.nameList.size();temp++){
            System.out.print("| " + contact.nameList.get(temp));
            System.out.print(" " + contact.phoneList.get(temp));
            System.out.print(" " + contact.emailList.get(temp));
            System.out.println(" " + contact.groupList.get(temp));
        }
    }

    public static void showName(){
        while (true){
            Messages.showGroup();
            Messages.insertInput();

            String name;
            name = sc.next();

            if (name.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                break;
            }

            Messages.splitter();

            if (!contact.nameList.contains(name)){
                Messages.errorNaming();
            } else{
                System.out.format("Here the info about contact '%s' with the format '| [name] [number] [email] [group]' (If empty, it shows '-'):\n", name);

                System.out.print("| " + name);
                System.out.print(" " + contact.phoneList.get(contact.nameList.indexOf(name)));
                System.out.print(" " + contact.emailList.get(contact.nameList.indexOf(name)));
                System.out.println(contact.groupList.get(contact.nameList.indexOf(name)));

                break;
            }
        }
    }

    public static void showGroup(){
        while (true){
            Messages.showGroup();
            Messages.insertInput();

            String group;
            group = sc.next();

            if (group.equals("exit")){
                Messages.splitter();
                Messages.exitEmergency();

                break;
            }

            Messages.splitter();

            if (!contact.groupContact.contains(group)){
                Messages.errorNaming();
            } else{
                System.out.format("Here are all the contacts that exist in group '%s' with the format '| [name] [number] [email]' (If empty, it shows '-'):\n", group);

                for (int temp = 0;temp < contact.nameList.size();temp++){
                    if (contact.groupList.get(temp).equals(group)){
                        System.out.print("| " + contact.nameList.get(temp));
                        System.out.print(" " + contact.phoneList.get(temp));
                        System.out.println(" " + contact.emailList.get(temp));
                    }
                }

                break;
            }
        }
    }

    public static void help(){
        Messages.splitter();
        Messages.helpInput();
    }

    public static boolean exit(){
        Messages.splitter();
        
        while (true){
            Messages.exitOption();
            Messages.insertInput();

            String exitOption;
            exitOption = sc.next().toLowerCase();

            Messages.splitter();

            if (exitOption.equals("yes")){
                Messages.exitConfirm();
                
                break;
            } else if (exitOption.equals("no")){
                Messages.exitCancel();
    
                return false;
            } else{
                Messages.errorInput();
            }
        }

        return true;
    }
}
