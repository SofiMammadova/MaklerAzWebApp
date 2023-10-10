/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package az.makler.maklerazwebapp.controller;

import az.makler.config.Context;
import az.makler.dao.inter.ElanDaoInter;
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
@WebServlet(name = "ElanDetailsController", urlPatterns = {"/elandetails"})
public class ElanDetailsController extends HttpServlet {

    /*t.k eto nijesleduyusheye otnositsa i k GET i k POST methods
        vinesem eto v qlobalku*/
    private ElanDaoInter edao = Context.instanceElanDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    /*t.k eto nijesleduyusheye otnositsa i k GET i k POST methods
        vinesem eto v qlobalku
        ElanDaoInter edao = Context.instanceElanDao(); 
         */
        try {
    /*Elan e = edao.getELanById(2); //eto uje ne nujno, tak kak mi avtomatiziruem eto
        //vmesto 2-ki budem otpravlat nuzhnuyu id dla update'a */
            Integer id = null; //dla update
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                id = Integer.valueOf(idStr);//prisvaivaem sahe peremennuyu saheStr
            } else {
                //esli v peredavaemoy nami ID est oshibka, t.e ona null
                //ili je pri trime vvedennoy nami id est oshibka
                //i perevesti eyo v integer ne poluchayetsa
                //to exception atacaq, chunki bize parameter gele bilmedi
                throw new IllegalArgumentException("ID is wrong");
            }
            //esli id v normalnom vide. t.e ne null
            //i smoqli perevesti vvedennuyu nami id(string) v id(integer)
            //toqda bu ID-de olan elani gedib tapacaq
            Elan e = edao.getELanById(id);

            //u nas sformirovalos id(int)
            //no nado proverit, est takaya idshka v db ili net
            if (e == null) {
                throw new IllegalArgumentException("Couldn't find this id in the system");
            }
            //sozdaem attribute pod nazvaniem elan (eto key)
            //i emu delaem set e (a eto ego value)
            //tam,qde budet nujno, vizovem get i vozmem e
            request.setAttribute("elan", e);

            //posle set'a napravlayem (dispatcher):
            request.getRequestDispatcher("elanDetails.jsp").forward(request, response);
        } catch (Exception ex) { //dlya errora
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //SAVE
        /*t.k eto nijesleduyusheye otnositsa i k GET i k POST methods
        vinesem eto v qlobalku
        ElanDaoInter edao = Context.instanceElanDao(); 
         */

        //if(request.getParameter("save") != null){ //t.e esli value=save
        //zakommentirovali verxniy if, t.k mi napramuyu otpravlaem na UPDATE
        Integer id = null; //dla update
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.trim().isEmpty()) {
            id = Integer.valueOf(idStr); //prisvaivaem sahe peremennuyu saheStr
        }
        String unvan = request.getParameter("unvan"); //name'i unvan olani alacaq
        //getParameter vozvrashaet tolko String
        Integer sahe = null; //t.k sahe int, a getParam vozvr String, delaem takuyu rabotu
        String saheStr = request.getParameter("sahe");
        if (saheStr != null && !saheStr.trim().isEmpty()) {
            sahe = Integer.valueOf(saheStr); //prisvaivaem sahe peremennuyu saheStr
        }
        
        String tip = request.getParameter("tip");
        //update budet rabotat po id
        //mi peredaem opredelennoe id i ego update'nem

        Elan elan = edao.getELanById(id); //peredadim id
        String action = request.getParameter("action");
        //action parametrini alacaq
        //eger onun value'su update'e beraberdirse
        if(action.equals("update")){
           elan.setUnvan(unvan);//mojno budet updatenut unvan
           elan.setSahe(sahe);//sahe updatenut
           elan.setTip(tip);
           edao.update(elan);
        //} //if request.param save 
        } //update
        else if(action.equals("delete")){ //delete
            edao.delete(id);
        }
        //posle toqo kak on sdelal set novoqo unvana ili sahe opredelennoqo id
        //nuzhno perenapravit stranicu, else otkroetsa prosto beloe okno
        response.sendRedirect("elan");
        
    }
    
}
