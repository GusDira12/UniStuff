/*
 * All the texts go here. Yeah, this is quite the amount.
 */

public class Messages{
    public static void startUp(){
        Messages.splitter();
        System.out.println("Hello! Welcome to PhoneContact, a Java-based TUI program that is essentially based on the contact application available in all range of devices.");
        System.out.println("This gets a bit complex, but throughout the program I'll try my best to help you whether it's an error, or anything else.");
        
        System.out.println("\nAll of this is available at: https://github.com/GusDira12/UniStuff/tree/master/HCI/PhoneContact.java (any feedback is welcome)");

        System.out.println("\nLet's get started! Below you can type a command at the home section. Why not start with 'help' to see all the commands available?");
        Messages.splitter();
    }

    public static void atHome(){
        System.out.println("This is the home section! You'll be directed here whenever you're done with a command or an error happened.");
        System.out.println("If you forget the commands here, just type 'help'! It'll save you time. During a command, you can just type 'exit' to cancel the command (besides 'exit' itself).\n");
    }

    public static void addOption(){
        System.out.println("Would you like to add a bunch of information for your contact, just a specific information, or a group?");
        System.out.println("Type 'all' if you want to do the first option, 'name' to only add the name, 'number' to add a number, 'email' to add an email, or 'group' to add a group/tag.");
        System.out.println("Outside adding a name and a group, you MUST have existing name available if you want to add a new number or an email.\n");
    }

    public static void addAll(){
        System.out.println("You decided to add all info at once, which are name, number, email, and group! If you want to leave it empty, type '-'.");
        System.out.println("You can't have contacts of the same name, and you can only have numbers for your number info.");
        System.out.println("If there are no groups or the group name you enter doesn't exist, you can have the option to add it or leave it empty instead.");
    
        System.out.println("\nEnter all info in one succession with this format: [name] [number] [email] [group]\n");
    }

    public static void addName(){
        System.out.println("Type the name of the contact you want to add below! Remember that it can't be the same name as existing contact name.\n");
    }

    public static void addOtherName(){
        System.out.println("Type the name of the contact you want to add a new info of! If the name doesn't exist, you can add it here for your convenience.\n");
    }

    public static void addNumber(){
        System.out.println("Please type the number below! It must only contain numbers from 0 to 9, no other characters or anything like that. It can be the same number from another contact.\n");
    }

    public static void addEmail(){
        System.out.println("Add the email for the contact below! It can be the same name as existing email from another contact, as it won't break anything.\n");
    }

    public static void addGroupOption(){
        System.out.println("Hey! Seems that the group name you entered doesn't exist yet. If you want to add it, type 'yes'. Otherwise, type 'no'.\n");
    }

    public static void addGroup(){
        System.out.println("Ahead of time it seems. Anyways, enter your new group name below. Again, it can't be the same name as existing group name.\n");
    }

    public static void removeOption(){
        System.out.println("You have the option to either delete a whole contact information or a group name only, which won't delete any contact.");
        System.out.println("Type 'name' if you want to remove a contact, or 'group' if you want to just delete a group name.\n");
    }

    public static void removeAll(){
        System.out.println("Type the contact name you want to remove. The name must exist in the contact and it'll delete the whole information of the contact.\n");
    }

    public static void removeOther(){
        System.out.println("Type the contact name you want to remove a specific info of that you selected. It'll only remove that info, so your contact is safe.\n");
    }

    public static void removeGroup(){
        System.out.println("Enter the group name you want to remove. Good for you, this is just a tag, so nothing too valuable will be gone.\n");
    }

    public static void searchOption(){
        System.out.println("Do you want to search a specific contact name or contacts in a group? Only these 2 options in case you're asking.");
        System.out.println("Type 'name' if you want to find a contact name, or 'group' to search contacts in a group.\n");
    }

    public static void searchName(){
        System.out.println("Hey, type the contact name you want to search below. If the name doesn't exist, I'll return it as false.");
        System.out.println("Use the 'show' command instead if you want to see all information about the contact. This is just search.\n");
    }

    public static void searchGroup(){
        System.out.println("Type the group you want to find! I'll print the contact names only if the group you enter exists, otherwise I'll just return it as false.");
        System.out.println("If you want to see every information of the group, use the 'show' command instead at home.\n");
    }

    public static void showOption(){
        System.out.println("Do you want to see all contacts list, just a contact name, or in a specific group?");
        System.out.println("Type 'all' for the first option, 'name' to show a specific contact name, or 'group' for the group list.\n");
    }

    public static void showGroup(){
        System.out.println("Enter the group name you want to check! A warning that the group name must exist.\n");
    }

    public static void helpInput(){
        System.out.println("Either this is your first time using this program or you forget the commands. Anyways, here you go:");
        System.out.println("- Add: Lets you create a new contact or group, or both. You can also add either a number or email by itself to an existing contact.");
        System.out.println("- Remove: Deletes a specific content of a contact, or remove a contact entirely.");
        System.out.println("- Search: Searches a specific contact or a group of contacts. To show all information, refer to 'show' command.");
        System.out.println("- Show: Shows information of all contact, a specific contact name, or contacts in a group.");
        System.out.println("- Help: This! type this while you're at home.");
        System.out.println("- Exit: Exits the program. I'll have to confirm whether you actually want to exit or not as a warning.");
    }
    
    public static void exitOption(){
        System.out.println("Looks like you want to exit. Are you sure? If so, type 'yes'. Else, type 'no'.\n");
    }

    public static void exitCancel(){
        System.out.println("Exit cancelled. Sending you back to home.");
    }

    public static void exitConfirm(){
        System.out.println("Exit completed. Thank you for using the program.");
    }

    public static void exitEmergency(){
        System.out.println("Sending you back to home now.");
    }

    public static void errorInput(){
        System.out.println("The command you entered doesn't exist. If you're lost, check the help section depending on where you are.\n");
    }

    public static void errorNaming(){
        System.out.println("Name you entered is either invalid or already exists. Please type a new name.\n");
    }

    public static void errorNumber(){
        System.out.println("Hm? You can only have numbers in the number info.");
        System.out.println("You wouldn't type any other character while trying to call someone, would you?\n");
    }

    public static void errorAdd(){
        System.out.println("Looks like the name you entered doesn't exist. Would you like to add the name you just entered? Type 'yes' if so, or 'no'.\n");
    }

    public static void errorAmount(){
        System.out.println("Value of contact/group list is at 0. You might want to add a contact or a group first.");
    }

    public static void splitter(){
        System.out.println("----------------------------------------------------------------");
    }

    public static void insertInput(){
        System.out.print("Hey! Type your input here: ");
    }
}
