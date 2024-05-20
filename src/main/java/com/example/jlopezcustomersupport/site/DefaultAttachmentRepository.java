package com.example.jlopezcustomersupport.site;

import com.example.jlopezcustomersupport.entities.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultAttachmentRepository extends GenericJpaRepository<Long, Attachment> implements AttachmentRepository{

    @Override
    public Attachment getByTicketId(Long ticketID) {
        try {
            return this.entityManager.createQuery("SELECT i FROM Attachment i WHERE i.ticketId = :id", Attachment.class).setParameter("id", ticketID).getSingleResult();
        }
        catch(Exception e) {
            return null;
        }
    }
}

