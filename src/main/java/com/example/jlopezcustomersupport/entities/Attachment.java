package com.example.jlopezcustomersupport.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "attachments")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L; // unique id for serializable version
    private long id;
    private long ticketId;
    private String name;
    private byte[] contents;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
    public Attachment(){
      super();
    };

    // Getter for name
    @Basic
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for contents
    @Lob
    public byte[] getContents() {
        return contents;
    }

    // Setter for contents
    public void setContents(byte[] contents) {
        this.contents = contents;
    }


}

