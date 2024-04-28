package CustomerObjects;

import java.io.*;

import com.example.jlopezcustomersupport.site.SessionListUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//@WebServlet(name = "sessionListServlet", value="/sessions")
public class SessionListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") == null){
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("numSessions", SessionListUtil.getNumOfSessions());
        request.setAttribute("sessionList", SessionListUtil.getAllSessions());

        request.getRequestDispatcher("/WEB-INF/jsp/view/sessions.jsp").forward(request,response);
    }

    // called doGet to redo the same logic in-case doPost is called
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
