/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package az.makler.maklerazwebapp.controller;

import az.makler.config.Context;
import az.makler.dao.inter.UsersDaoInter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sofiya.mammadova
 */
@WebServlet(name = "LogOutController", urlPatterns = {"/logout"})
public class LogOutController extends HttpServlet {

 private UsersDaoInter udao2 = Context.instanceUsersDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    request.getRequestDispatcher("user.jsp").include(request, response);
    request.getSession().invalidate();
    response.sendRedirect("login.jsp");
    }



}
