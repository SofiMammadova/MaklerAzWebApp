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
import javax.servlet.http.HttpServletResponse;

/* @author sofiya.mammadova */
@WebFilter(filterName="JspFilter", urlPatterns = {"*.jsp"})
//* - t.e vse, chto zakanchivaetsa na .jsp
public class JspFilter implements Filter{
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
        HttpServletResponse res = (HttpServletResponse) response; 
        res.sendRedirect("error?msg=not found");
    }

    @Override
    public void destroy() {
   }
    
}
