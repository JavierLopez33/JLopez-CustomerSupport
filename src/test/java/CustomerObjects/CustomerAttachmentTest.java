package CustomerObjects;
import com.example.jlopezcustomersupport.entities.Attachment;
import org.junit.jupiter.api.Test;

public class CustomerAttachmentTest {
    Attachment attachOne = new Attachment();

    @Test
    void createAttachment() {
        attachOne.setName("Screenshot");
        attachOne.setContents(new byte[]{});
    }

}
