<%@page import="java.util.Vector"%>
<%@ page import="java.sql.*" %>
<%@page import="data.*"%>
<%@page import="sgbd.Connexion"%>
<%@page import="java.sql.Connection"%>
<%
    Connection co = new Connexion().getconnection();
    Vector<Vector<Object>> list = new Vente().getStock_production(co);
    boolean validation;
    boolean nomvalide = false;
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Achat de produit</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: NiceAdmin - v2.2.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
        <a href="index.jsp" class="logo d-flex align-items-center">
        <img src="assets/img/logo.png" alt="">
        <span class="d-none d-lg-block">Gestion de Stock</span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->
  </header><!-- End Header -->

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">
      <li class="nav-item">
        <a class="nav-link " data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-journal-text"></i><span>Options</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="forms-nav" class="nav-content collapse show" data-bs-parent="#sidebar-nav">
          <li>
              <a href="Achat.jsp" class="active">
              <i class="bi bi-circle"></i><span>Achat</span>
            </a>
          </li>
          <li>
              <a href="Fabrication.jsp">
              <i class="bi bi-circle"></i><span>Fabrication</span>
            </a>
          </li>
          <li>
              <a href="MoyennePondere.jsp">
              <i class="bi bi-circle"></i><span>Prix Moyenne Ponderee</span>
            </a>
          </li>
          <li>
               <a href="Vente.jsp">
                   <i class="bi bi-circle"></i><span>Vente</span>
               </a>
          </li>
        </ul>
      </li><!-- End Forms Nav -->
  </aside><!-- End Sidebar-->

        <main id="main" class="main">
            
                <div class="pagetitle">
                  <h1>Liberation de stock</h1>
                </div><!-- End Page Title -->
            
                <section class="section">
                  <div class="row">
                    <!-- comment -->
                    <div class="col-lg-12">
                       <div class="card">
                           <div class="card-body">
                               <h5 class="card-title">Stock de production</h5>
                               <table class="table" class="col-lg-8">
                                    <thead>
                                        <tr>
                                            <th scope="col">Produit</th>
                                            <th scope="col">Quantite</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for(int i=0 ; i< list.size() ; i++){ %>
                                         <tr class="table-dark">
                                            <th scope="row"><%= list.get(i).get(0) %></th>
                                            <td><%= list.get(i).get(1) %></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table> 
                            </div>
                        </div>
                    </div>
                  </div>       
                </section>
                <section class="section">
                  <div class="row">
                    <!-- comment -->
                    <div class="col-lg-12">
                       <div class="card">
                           <div class="card-body">
                               <h5 class="card-title">Commande</h5>
                               <form class="row g-3" method="get" action="http://localhost:8080/GestionStock/VenteServlet">
                                <div class="col-md-6">
                                    <input type="text" name="nom" class="form-control" placeholder="Nom de matiere">
                                </div>
                                <div class="col-md-6">
                                    <input type="number" name="quantite" class="form-control" placeholder="Quantite">
                                </div>
                                <div class="col-md-12">
                                    <input type="date" name="daty" class="form-control" >
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Valider</button>
                                    <button type="reset" class="btn btn-secondary">Annuler</button>    
                                </div>
                                <%  if(request.getAttribute("validation") != null){
                                        validation = (boolean)request.getAttribute("validation");
                                        if( validation == nomvalide ){ %>                                    
                                        <div class="text-center">
                                            <h5 style="color: red" class="card-title">Stock insuffisant</h5>
                                        </div> 
                                        <% } %>
                                <% } %>
                                <% String message = (String)request.getAttribute("message");
                                    if(message != null){ %>                                    
                                    <div class="text-center">
                                        <h5 style="color: red" class="card-title">Erreur : Completer touts les cases,stpl </h5>
                                    </div>
                                <% } %>
                                </form>
                            </div>
                        </div>
                    </div>
                  </div>       
                </section>
              </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>2022</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
      Designed by <a href="https://bootstrapmade.com/">Tafitasoa Tanjonirina</a>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.min.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

</body>

</html>