/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package az.makler.maklerazwebapp.controller;

import az.makler.config.Context;
import az.makler.dao.inter.UsersDaoInter;
import az.makler.entity.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sofiya.mammadova
 */
@WebServlet(name = "LoginControllerNew", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
public UsersDaoInter udao = Context.instanceUsersDao();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Users u = udao.getUserByEmailAndPassword(email, password);
        request.getSession().setAttribute("loggedInUser", udao);
        response.sendRedirect("elan");
    }


}
