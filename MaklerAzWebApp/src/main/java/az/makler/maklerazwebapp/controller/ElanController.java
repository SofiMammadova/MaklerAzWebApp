/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package az.makler.maklerazwebapp.controller;

import az.makler.config.Context;
import az.makler.dao.inter.ElanDaoInter;
import az.makler.entity.Elan;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author sofiya.mammadova
 */
@WebServlet(name = "ElanController", urlPatterns = {"/elan"})
public class ElanController extends HttpServlet {
//zdes nam nujni tolko GET i POST
    
    private ElanDaoInter edao = Context.instanceElanDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String unvan = request.getParameter("unvan"); //name'i unvan olani alacaq
        //getParameter vozvrashaet tolko String
        Integer sahe = null; //t.k sahe int, a getParam vozvr String, delaem takuyu rabotu
        String saheStr = request.getParameter("sahe");
        if (saheStr != null && !saheStr.trim().isEmpty()) {
            sahe = Integer.valueOf(saheStr); //prisvaivaem sahe peremennuyu saheStr
        }
        String tip = request.getParameter("tip");
        
        List<Elan> list = edao.getAllElan(unvan, sahe, tip);
        request.setAttribute("list", list);
        //potom vernet nas v elan.jsp
        request.getRequestDispatcher("elan.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
