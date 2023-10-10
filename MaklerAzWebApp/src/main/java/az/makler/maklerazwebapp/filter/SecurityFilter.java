/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package az.makler.maklerazwebapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* @author sofiya.mammadova */
@WebFilter(filterName="SecurityFilter", urlPatterns = {"*"})
//* - t.e vse, chto zakanchivaetsa na .jsp
public class SecurityFilter implements Filter{
//init i destroy est vo vseh klassah
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { 
  }

    @Override
    //v doFiltere iznachalno propisano ServletRequest request
    //a mi rabotaem s HttpServletRequest request(iz ElanControllera doGet method)
    //t.e. mi rabotaem s HTTPS protokolom
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //error sehifesine response gonderirik
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if(!req.getRequestURI().contains("/login") && req.getSession().getAttribute("loggedInUser") == null){
           //v controllere mi budem proverat user'a
           //esli user vozvrashaetsa, t.e v baze est user s etim email i passwordom
           //to sdelaem setAttribute kak v elanControllere
           //zdes getSession- eto novaya sessiya
           //esli v url net /login, i esli loggedinuser vozvrashaet null, to redirect login
            res.sendRedirect("login");
        } else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
   }
    
}
