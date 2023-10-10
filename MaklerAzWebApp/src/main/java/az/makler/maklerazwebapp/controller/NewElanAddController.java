/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package az.makler.maklerazwebapp.controller;

import az.makler.config.Context;
import az.makler.dao.inter.ElanDaoInter;
import az.makler.dao.inter.UsersDaoInter;
import az.makler.entity.Elan;
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
@WebServlet(name = "NewElanAddController", urlPatterns = {"/addelan"})
public class NewElanAddController extends HttpServlet {

    private ElanDaoInter edao = Context.instanceElanDao();  

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("addid"));
        String unvan = request.getParameter("addunvan"); 
        int sahe = Integer.valueOf(request.getParameter("addsahe"));        
        String tip = request.getParameter("addtip");
        
        Elan e = new Elan();

            e.setElanId(id);
            e.setUnvan(unvan);//mojno budet updatenut unvan
            e.setSahe(sahe);//sahe updatenut
            e.setTip(tip);
            
            edao.insert(e);
            
        
        
        response.sendRedirect("elan");
    }
    
}
