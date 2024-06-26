package com.example.jlopezcustomersupport.site;

import com.example.jlopezcustomersupport.entities.Attachment;

import java.io.Serializable;

public class Ticket implements Serializable {

    private long id;
    private String customerName;
    private String subject;
    private String body;
    private Attachment attachment;


    public Ticket() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return attachment != null && attachment.getName().length() > 0 && attachment.getContents().length>0;
    }
}
