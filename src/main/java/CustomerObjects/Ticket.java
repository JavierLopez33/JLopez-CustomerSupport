package CustomerObjects;

public class Ticket {
    private String customerName;
    private String subject;
    private String body;
    private Attachment attachment;


    public Ticket() {
        super();
    }

    public Ticket(String customerName, String subject, String body, Attachment attachment) {
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachment = attachment;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public boolean hasAttachment() {
        return attachment.getName().length() > 0 && attachment.getContents().length>0;
    }
}
