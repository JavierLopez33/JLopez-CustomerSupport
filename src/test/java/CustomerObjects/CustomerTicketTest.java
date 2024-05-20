package CustomerObjects;

import com.example.jlopezcustomersupport.entities.Attachment;
import com.example.jlopezcustomersupport.site.Ticket;
import org.junit.jupiter.api.Test;

public class CustomerTicketTest {
    Attachment attachOne = new Attachment();

    @Test
    void addAttachmentTest() {
        attachOne.setName("Screenshot");
        attachOne.setContents(new byte[]{});
    }
}
