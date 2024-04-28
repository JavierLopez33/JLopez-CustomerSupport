package CustomerObjects;

public class Attachment {
    private String name;
    private byte[] contents;

    public Attachment(){
      super();
    };

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for contents
    public byte[] getContents() {
        return contents;
    }

    // Setter for contents
    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}

