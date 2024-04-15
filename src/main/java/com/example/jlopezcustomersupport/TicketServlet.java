package com.example.jlopezcustomersupport;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import CustomerObjects.Ticket;
import CustomerObjects.Attachment;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ticket", value = "/ticket")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20971520L, maxRequestSize = 41_943040L)
public class TicketServlet extends HttpServlet {
    private volatile int TICKET_ID = 1;
    private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {
            listTickets(request, response);
            return;
        }

        switch (action) {
            case "view":
                viewTicket(request, response);
                break;
            case "createTicket":
                showTicketForm(request, response);
                break;
            case "download":
                downloadAttachment(request, response);
                break;
            default:
                listTickets(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            listTickets(request, response);
            return;
        }

        switch (action) {
            case "create":
                createTicket(request, response);
                break;
            default:
                response.sendRedirect("ticket");
                break;
        }
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // heading and link to create ticket
        out.println("<html><body><h2>Ticket List</h2>");
        out.println("<a href=\"ticket?action=createTicket\">Create Ticket</a><br><br>");

        //list tickets
        if (ticketDB.size() == 0) {
            out.println("There are no tickets yet...");
        } else {
            for (int id: ticketDB.keySet()){
                Ticket ticket = ticketDB.get(id);
                out.println("Ticket #" + id);
                out.println(": <a href=\"ticket?action=view&ticketID=" +id +"\">");
                out.println(ticket.getSubject() + "</a><br>");
            }
        }

        out.println("</body></html>");
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(request.getParameter("customerName"));
        ticket.setSubject(request.getParameter("subject"));
        ticket.setBody(request.getParameter("body"));

        Part file = request.getPart("attachment1");
        if(file!= null){
            Attachment attachment = this.processAttachment(file);
            if(attachment != null){
                ticket.setAttachment(attachment);
            }
        }

        int id;
        synchronized (this){
            id = this.TICKET_ID++;
            ticketDB.put(id,ticket);
        }

        response.sendRedirect("ticket?action=view&ticketID=" + id);
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = request.getParameter("ticketID");


        Ticket ticket = getTicket(idString, response);

        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Ticket Information: </h2>");
        out.println("<h3> Thank you, " + ticket.getCustomerName()+" for submitting a ticket.</h3>");
        out.println("<p> Subject: " + ticket.getSubject()+"</p>");
        out.println("<p> Issue: " + ticket.getBody()+"</p>");
        if(ticket.hasAttachment()){
            out.println("<a href=\"ticket?action=download&ticketID=" + idString + "&attachment=" + ticket.getAttachment().getName() + "\">" + ticket.getAttachment().getName() + "</a><br><br>");
        }
        out.println("<a href=\"ticket\"> Return to ticket list</a>");
        out.println("</body></html>");

    }


    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = request.getParameter("ticketID");


        Ticket ticket = getTicket(idString, response);

        String name = request.getParameter("attachment");
        if(name == null){
            response.sendRedirect("ticket?action=view&ticketID=" + idString);
        }

        Attachment attachment = ticket.getAttachment();
        if (attachment == null){
            response.sendRedirect("ticket?action=view&ticketID=" + idString);
            return;
        }

        response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        ServletOutputStream out = response.getOutputStream();
        out.write(attachment.getContents());
    }


    private Attachment processAttachment(Part file) throws IOException {
        InputStream in = file.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // processing binary data to bytes
        int read;
        final byte[] bytes = new byte[1024];
        while((read = in.read(bytes)) != -1){
        out.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(file.getSubmittedFileName());
        attachment.setContents(out.toByteArray());

        return attachment;
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Ticket Submission Form</h1>");
        out.println("<form method=\"POST\" action=\"ticket\"  enctype=\"multipart/form-data\">");
        out.println("<input type=\"hidden\" name=\"action\" value=\"create\">");
        out.println("<label for=\"customerName\">Name:</label><br>");
        out.println("<input type=\"text\" name=\"customerName\" required><br><br>");
        out.println("<label for=\"subject\">Subject:</label><br>");
        out.println("<input type=\"text\" name=\"subject\" required><br><br>");
        out.println("<label for=\"body\">Ticket Information:</label><br>");
        out.println("<textarea id=\"body\" name=\"body\" rows=\"10\" cols=\"100\" required></textarea><br><br>");
        out.println("<label for=\"attachment\">Attachments:</label><br>");
        out.println("<input type=\"file\" name=\"attachment1\" multiple><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form></body></html>");
    }

    private Ticket getTicket(String idString, HttpServletResponse response)throws ServletException, IOException{
        if(idString == null || idString.length() == 0 ){
            response.sendRedirect("ticket");
        }

        // find ticket in DB or return null and redirect to main page
        try{
            int id = Integer.parseInt(idString);
            Ticket ticket = ticketDB.get(id);
            if(ticket == null){
                response.sendRedirect("ticket");
                return null;
            }
            return ticket;

        }catch (Exception e){
            response.sendRedirect("ticket");
            return null;
        }
    }
}