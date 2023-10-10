<%-- 
    Document   : elan
    Created on : Jun 28, 2023, 8:33:41 PM
    Author     : sofiya.mammadova
--%>

<%@page import="java.util.List"%>
<%@page import="az.makler.dao.inter.ElanDaoInter"%>
<%@page import="az.makler.config.Context"%>
<%@page import="az.makler.config.Context"%>
<%@page import="az.makler.entity.Elan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"><!-- copy from getbootstrap.com -->
        <link rel="stylesheet" href="assets/css/style.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <%--pop-up script--%>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>    

        <%--getElementById dobavlaem script iz assets>js--%>
        <script src="assets/js/script.js"></script>
    </head>
    <body>

        <form action="login" method="GET">
            <input type="hidden" name="id" value="logout"/>
            <button type="submit" name="action" value="logout" class="myButton">
                <i class="fa-solid fa-arrow-right-from-bracket fa-beat-fade" style="color: #ff0000; position: absolute; top: 10px; right: 20px; height: 60px; width: 60px;" title="Log out"></i>
            </button>
        </form> 


        <%
            /*          zakommentirovali, t.k dobavili etu chast v elanController  
         String unvan = request.getParameter("unvan"); //name'i unvan olani alacaq
         //getParameter vozvrashaet tolko String
         Integer sahe = null; //t.k sahe int, a getParam vozvr String, delaem takuyu rabotu
         String saheStr = request.getParameter("sahe");
         if (saheStr != null && !saheStr.trim().isEmpty()) {
             sahe = Integer.valueOf(saheStr); //prisvaivaem sahe peremennuyu saheStr
         }
         ElanDaoInter edao = Context.instanceElanDao();
         List<Elan> list = edao.getAllElan(unvan, sahe); */
            //napishem MVC part
            List<Elan> list = (List<Elan>) request.getAttribute("list");

        %>


        <form id="search-container" action="elan" method="GET"> <%--action = click na button submit --%>
            <%--POSTluq ishimiz yoxdu, poetomu action="elan.jsp" menaem na action="elan"--%>
            <div class="mb-3">
                <label  class="form-label">Unvan</label>
                <input type="text" class="form-control" name="unvan" />               
            </div>
            <div class="mb-3">
                <label  class="form-label">Sahe</label>
                <input type="number" class="form-control" name="sahe"/>
            </div>
            <div class="mb-3">
                <label  class="form-label">Tip</label>
                <input type="text" class="form-control" name="tip"/>
            </div>
            <input type="submit" class="btn btn-primary" name="search" value="Search"/>
        </form>

<div class="add">
        <form id="insert-container" action="addelan" method="POST"> <%--action = click na button submit --%>
            <%--POSTluq ishimiz yoxdu, poetomu action="elan.jsp" menaem na action="elan"--%>
            <div class="mb-3">
                <label  class="form-label">ID</label>
                <input type="number" class="form-control" name="addid" />               
            </div>
            <div class="mb-3">
                <label  class="form-label">Unvan</label>
                <input type="text" class="form-control" name="addunvan" />               
            </div>
            <div class="mb-3">
                <label  class="form-label">Sahe</label>
                <input type="number" class="form-control" name="addsahe"/>
            </div>
            <div class="mb-3">
                <label  class="form-label">Tip</label>
                <input type="text" class="form-control" name="addtip" />
            </div>
            <input type="hidden" name="action" value="add"/>
            <input type="submit" class="btn btn-info" name="add" value="Add"/>
        </form>
</div>


        <table class="table table-striped text-center">
            <thead class = "col">
                <tr>
                    <th class="col-1">ID</th>
                    <th class="col-6">Unvan</th> <%--col-6, t.k description samiy obshirniy--%>
                    <th class="col">Tip</th>
                    <th class="col">Sahe</th>

                    <th class="col-1" colspan="2">&nbsp;</th> <%--eto dlya operations, colspan-->on budet soderjat 2 columns, chtob ne bilo obrivania--%>
                        <%--chtobi nad delete i update pri zoom'e liniya ne obrivalas--%>
                </tr>
            </thead>

            <tbody>
                <% for (Elan e : list) {%>  <%--  foreach iz Java --%> 
                <tr>
                    <th><%=e.getElanId()%></th>
                    <td><%=e.getUnvan()%></td>
                    <td><%=e.getTip()%></td>
                    <td><%=e.getSahe()%></td>

                    <%--  <td>Delete</td>   --%>
                    <td>
                        <input type="hidden" name="id" value="<%=e.getElanId()%>" />
                        <button onclick="setDeleteId(<%=e.getElanId()%>)" type="submit" data-bs-toggle="modal" data-bs-target="#exampleModal" class="myButton">
                            <i class="fa-regular fa-trash-can fa-bounce" style="color: #e20808;" title="Delete"></i>
                        </button>
                        <%--<button class = "myButton"><i class="fa-solid fa-delete-left" style="color: #f2020e;"></i></button> --%>
                    </td>
                    <%--<td>Update</td> --%>
                    <td>
                        <form action="elandetails" method="GET">
                            <%-- PO ID METODOM GET BUDET NAXODIT NUJNIY ELAN dlya update'a--%>
                            <input type="hidden" name="id" value="<%=e.getElanId()%>"/>
                            <button type="submit" name="action" value="update" class="myButton">
                                <i class="fa-sharp fa-solid fa-pencil fa-beat-fade" title="Update"></i>
                            </button>
                        </form>                   
                    </td>

                </tr>
                <%}%>  <%--  skobka ot foreach zakrivaem zdes--%> 
            </tbody>

        </table>
        <!-- Button trigger modal POP-UP -->


        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Are you sure?</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <form action="elandetails" method="POST">
                            <%--value="<%=e.getElanId()%>"/> TA NE MOJEM NAPISAT, T.K VNE SKOBOK NAXODIMSA, POETOMU PISHEM TAK:--%>
                            <input type="hidden" name="id" id="idForDelete" /> <%--syuda dolzhni otpravlat VALUE --%>
                            <input type="hidden" name="action" value="delete" />
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>   
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
