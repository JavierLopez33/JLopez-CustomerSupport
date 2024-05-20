package com.example.jlopezcustomersupport.site;

import com.example.jlopezcustomersupport.entities.Attachment;
import org.springframework.stereotype.Repository;


public interface AttachmentRepository extends GenericRepository<Long, Attachment>{

    Attachment getByTicketId(Long ticketId);
}
