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
                System.out.print(temp.data);
            }

            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        String output = "";
        Node temp = head;


        System.out.println("Email   Subject     Body    Time    Read");

        for (int i = 0; i < this.count; i++) {
            Email email = temp.data;
            output += String.format("%d %.25s  %40s  %d  %s\n", email.getID(), email.getSubject(), email.getMessage(),
                    email.getTime(), email.getFlag() ? "Yes" : "No");

            temp = temp.next;
        }
        return output;
    }

}