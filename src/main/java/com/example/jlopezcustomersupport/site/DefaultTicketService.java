package com.example.jlopezcustomersupport.site;

import java.util.ArrayList;
import java.util.List;

import com.example.jlopezcustomersupport.entities.Attachment;
import com.example.jlopezcustomersupport.entities.TicketEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DefaultTicketService implements TicketService{

    @Inject TicketRepository ticketRepo;
    @Inject AttachmentRepository attachmentRepo;

    @Override
    //@Transactional
    public List<Ticket> getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        ticketRepo.getAll().forEach(e -> list.add(this.convert(e)));
        return list;
    }

    @Override
    public Ticket getTicket(long id) {
        TicketEntity entity = ticketRepo.get(id);
        return entity == null ? null : this.convert(entity);
    }

    private Ticket convert(TicketEntity entity) {
        Ticket ticket = new Ticket();
        ticket.setId(entity.getId());
        ticket.setCustomerName(entity.getCustomerName());
        ticket.setSubject(entity.getSubject());
        ticket.setBody(entity.getBody());
        ticket.setAttachment(attachmentRepo.getByTicketId(entity.getId()));

        return ticket;
    }


    @Override
    @Transactional
    public void save(Ticket ticket) {
        // convert from ticket to entity
        TicketEntity entity = new TicketEntity();
        entity.setId(ticket.getId());
        entity.setCustomerName(ticket.getCustomerName());
        entity.setSubject(ticket.getSubject());
        entity.setBody(ticket.getBody());

        if (ticket.getId() < 1) {
            // add to the repo
            ticketRepo.add(entity);
            ticket.setId(entity.getId());

            // add image
            if (ticket.hasAttachment()) {
                ticket.getAttachment().setTicketId(entity.getId());
                attachmentRepo.add(ticket.getAttachment());
            }
        } else {
            // if id exists edit ticket and update in DB
            ticketRepo.update(entity);
        }
    }

    @Override
    @Transactional
    public void deleteTicket(long id) {
        ticketRepo.deleteById(id);
    }
}
