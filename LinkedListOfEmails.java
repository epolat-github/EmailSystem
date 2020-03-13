/**
 * LinkedListOfEmails
 */
public class LinkedListOfEmails {

    private Node head, tail;
    private int count = 0;

    private class Node {
        Email data;
        Node next;

        public Node(Email email) {
            this.data = email;
        }
    }

    public void addEmail(Email E) {
        Node newNode = new Node(E);
        newNode.next = null;

        if (this.head == null) { // if LinkedList is emptys
            this.head = newNode;
            this.tail = newNode;
        } else { // if LinkedList isn't empty
            newNode.next = this.head;
            this.head = newNode;
        }

        this.count++;

    }

    public void read(int id) {
        Node temp = head;

        for (int i = 0; i < this.count; i++) {
            if (id == temp.data.getID()) {
                System.out.print(temp.data);
                temp.data.setFlag(true); // mark as read
                return;
            }
            temp = temp.next;
        }

        System.out.format("No such email.\n", id);
    }

    // Delete all folder data
    public String truncate(LinkedListOfEmails folder) {

        if (this.head == null) {
            return "Folder is already empty!\n";
        }

        if (folder == null) { // Empty Trash
            for (int i = 0; i < this.count; i++) {
                this.delete();
            }
        } else { // Other than Trash
            for (int i = 0; i < this.count; i++) {
                folder.addEmail(this.delete());
            }
        }

        return "Folder cleared!\n";
    }

    // Delete from end
    public Email delete() {
        Node deleted = this.head;
        this.head = this.head.next;
        this.count--;

        return deleted.data;
    }

    // Delete specific email
    public Email delete(int id) {
        Node temp = this.head, prev = null;

        // if mail is head
        if (this.head.data.getID() == id) {
            this.head = this.head.next;
            this.count--;
            return temp.data;
        }

        // if other
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;

            if (temp.data.getID() == id) {
                prev.next = temp.next;
                this.count--;
                return temp.data;
            }
        }

        // if not exist
        return null;
    }

    public void showAll(boolean flag) {
        Node temp = head;

        for (int i = 0; i < this.count; i++) {
            if (flag == temp.data.getFlag()) {
                System.out.print(temp.data); // Email class toString()
            }

            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        String output = "";
        Node temp = this.head;

        if (temp == null) { // if folder is empty
            return "Folder is empty!\n";
        }

        System.out.printf("%s   %-25s%-40s %s    %s%n", "Email", "Subject", "Body", "Time", "Read");

        for (int i = 0; i < this.count; i++) {
            Email email = temp.data;
            String message = email.getMessage();
            String trimmedMessage = message.substring(0, 37).concat("...");
            output += String.format("%d    %-25s%-40s %d %s\n", email.getID(), email.getSubject(), trimmedMessage,
                    email.getTime(), email.getFlag() ? "Yes" : "No");

            temp = temp.next;
        }
        return output;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

}