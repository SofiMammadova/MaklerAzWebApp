<%-- 
    Document   : elanDetails
    Created on : Jun 30, 2023, 9:51:22 AM
    Author     : sofiya.mammadova
--%>

<%@page import="az.makler.entity.Elan"%>
<%@page import="az.makler.dao.inter.ElanDaoInter"%>
<%@page import="az.makler.config.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"><!-- copy from getbootstrap.com -->
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>
        <%
            /*
        ElanDaoInter edao = Context.instanceElanDao();

        if(request.getParameter("save") != null){ //t.e esli value=save
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
        //update budet rabotat po id
        //mi peredaem opredelennoe id i ego update'nem
            
        Elan elan = edao.getELanById(id); //peredadim id
        elan.setUnvan(unvan);//mojno budet updatenut unvan
        elan.setSahe(sahe);//sahe updatenut
        edao.update(elan);
        } //if request.param save 
        Elan e = edao.getELanById(2);
             */   // zakommentili, t.k dobavili etot kusok v elanDetailsController v metod POST (SAVE = POST METHOD)
/*ElanDaoInter edao = Context.instanceElanDao(); 
Elan e = edao.getELanById(2); //dlya vizualizacii ostavim poka eto */

//chtobi avtomatizirovat process peredachi ID:
//t.k elanDetailsControllerde GET methodunda 
//tapdigimiz set eledik "elan"'a
//poetomu chtobi poluchit ego zdes ispolzuem get "elan"a
//getAttribute("elan") vozvrashaet object, poetomu vnachale pishem (Elan)
            Elan e = (Elan) request.getAttribute("elan");
        %>   


        <form id="save-container" action="elandetails" method="POST"> <%--action = click na button submit --%>
            <%--zdes takje pomenali action="elanDetails.jsp" na action="elanDetails" --%>
            <input type="hidden" name="id" value="<%=e.getElanId()%>" /> <%-- dla update'a; id-eto integer --%>
            <div class="mb-3">
                <label  class="form-label">Unvan</label>
                <input type="text" class="form-control" name="unvan" value="<%=e.getUnvan()%>"/>
            </div>
            <div class="mb-3">
                <label  class="form-label">Sahe</label>
                <input type="number" class="form-control" name="sahe" value="<%=e.getSahe()%>" />
            </div>
            <div class="mb-3">
                <label  class="form-label">Tip</label>
                <input type="text" class="form-control" name="tip" value="<%=e.getTip()%>" />
            </div>
            <input type="hidden" name="action" value="update"/>
            <input type="submit" class="btn btn-info" name="save" value="Save"/>

        </form>


    </body>
</html>
