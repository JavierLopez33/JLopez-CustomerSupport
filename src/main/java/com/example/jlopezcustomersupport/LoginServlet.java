package com.example.jlopezcustomersupport;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value="/login")
public class LoginServlet extends HttpServlet {
    public  static final Map<String, String> userDB = new Hashtable<>();

    static {
        userDB.put("JSmith", "password1");
        userDB.put("BSmith", "password2");
        userDB.put("JDoe", "password3");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        // if logout exists > log user out
        if(request.getParameter("logout")!= null){
         session.invalidate();// invalidate logs user out
         response.sendRedirect("login");
         return;
        }
        // check if logged in and redirect to main servlet
        else if(session.getAttribute("username") != null){
            response.sendRedirect("ticket");
            return;
        }
        // not logged in, go to login page, initial login page
        request.setAttribute("loginFailed", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        // check if logged in
        if(session.getAttribute("username") != null){
            response.sendRedirect("ticket");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // check bad values can't log in
        if(username == null || password == null || !LoginServlet.userDB.containsKey(username) || !password.equals(LoginServlet.userDB.get(username))){
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request,response);
        }
        // login successful
        else {
            session.setAttribute("username", username);
            request.changeSessionId(); // protects against session fixation attacks
            response.sendRedirect("ticket");
        }

    }

}
