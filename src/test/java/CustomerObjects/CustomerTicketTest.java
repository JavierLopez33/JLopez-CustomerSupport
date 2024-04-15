package CustomerObjects;

import org.junit.jupiter.api.Test;

public class CustomerTicketTest {
    Attachment attachOne = new Attachment();
    Ticket ticket = new Ticket("John Doe", "Unable to make payment", "I am having issues submitting my payment",attachOne);

    @Test
    void addAttachmentTest() {
        attachOne.setName("Screenshot");
        attachOne.setContents(new byte[]{});
        ticket.setAttachment(attachOne);
    }
}
