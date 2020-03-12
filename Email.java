/**
 * email
 */
public class Email {

    private String subject, message;
    private int ID, time;
    private boolean flag;

    public Email(String subject, String message, int ID, int time) {
        this.subject = subject;
        this.message = message;
        this.ID = ID;
        this.time = time;
        this.flag = false;
    }

    /**
     * @return the iD
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @return the flag
     */
    public boolean getFlag() {
        return flag;
    }

    /**
     * @param iD the iD to set
     */
    public void setID(int iD) {
        ID = iD;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String output = String.format("Email id: %d\nSubject: %s\nBody: %s\nTime received: %d\nStatus: %s\n", this.ID,
                this.subject, this.message, this.time, this.flag ? "Read" : "Not Read");
        return output;
    }

}