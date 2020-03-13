import java.util.Scanner;

/**
 * EmailApplication
 */
public class EmailApplication {

    public static void main(String[] args) {
        LinkedListOfEmails Inbox = new LinkedListOfEmails();
        LinkedListOfEmails Archive = new LinkedListOfEmails();
        LinkedListOfEmails Trash = new LinkedListOfEmails();

        Scanner sc = new Scanner(System.in);

        boolean loopFlag = true;

        while (loopFlag) {
            System.out.println("");
            String inputLine = sc.nextLine();

            String[] commands = inputLine.split(" ", 2);
            switch (commands[0]) { // Command type (N, R, A, D, S, U, C)
                // New Email
                case "N":
                    Email email;

                    String[] emailContent = commands[1].split("//"); // emailContent[0] == ""
                    String subject = emailContent[1];
                    int ID = Integer.parseInt(emailContent[2]);
                    String message = emailContent[3];
                    int time = Integer.parseInt(emailContent[4]);

                    email = new Email(subject, message, ID, time);

                    Inbox.addEmail(email);
                    break;

                // Read Email
                case "R": {
                    int emailId = Integer.parseInt(commands[1]);

                    Inbox.read(emailId);
                    break;
                }

                // Archive Email
                case "A": {
                    int emailId = Integer.parseInt(commands[1]);
                    Email archived = Inbox.delete(emailId);
                    if (archived != null) {
                        Archive.addEmail(archived);
                        System.out.format("Email %d archived\n", emailId);
                    } else {
                        System.out.format("Archive problem occurred!\n");
                    }
                    break;
                }
                // Delete Email
                case "D": {
                    int emailId = Integer.parseInt(commands[1]);
                    Email deleted = Inbox.delete(emailId);

                    Trash.addEmail(deleted);

                    break;
                }

                // Show Folders
                case "S": {
                    String folderName = commands[1];
                    switch (folderName) {
                        case "Inbox": {
                            System.out.print(Inbox);
                            break;
                        }

                        case "Archive": {
                            System.out.print(Archive);
                            break;
                        }

                        case "Trash": {
                            System.out.print(Trash);
                            break;
                        }

                        default:
                            System.out.println("Unknown Folder Name!");
                            break;
                    }

                    break;
                }

                // Show Unread Emails
                case "U": {
                    String folderName = commands[1];
                    switch (folderName) {
                        case "Inbox": {
                            Inbox.showAll(false);
                            break;
                        }

                        case "Archive": {
                            Archive.showAll(false);
                            break;
                        }

                        case "Trash": {
                            Trash.showAll(false);
                            break;
                        }

                        default:
                            System.out.println("Unknown Folder Name!");
                            break;
                    }

                    break;
                }
                // Clear Folder
                case "C":
                    String folderName = commands[1];
                    switch (folderName) {
                        case "Inbox": {
                            System.out.println(Inbox.truncate(Trash)); // Transfer to Trash
                            break;
                        }

                        case "Archive": {
                            System.out.println(Archive.truncate(Trash)); // Transfer to Trash
                            break;
                        }

                        case "Trash": {
                            System.out.println(Trash.truncate(null)); // Directly truncate
                            break;
                        }

                        default:
                            System.out.println("Unknown Folder Name!");
                            break;
                    }

                    break;

                case "Q": {
                    loopFlag = false;
                    break;
                }
            }
        }
        sc.close();
    }
}