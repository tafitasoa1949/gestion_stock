<%@ page import="java.sql.*" %>
<%@ page import="java.util.Vector" %>
<%@ page import="sgbd.*,data.*" %>
<%
    Connection co = new Connexion().getconnection();
    Vector<Stock> list = new DAO().get_AllStock(co);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Gestion de stock d'entreprise</title>
  <meta content="" name="description">
  <meta content="" name="keywords">
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
        <header id="header" class="header fixed-top d-flex align-items-center">

                <div class="d-flex align-items-center justify-content-between">
                    <a href="#" class="logo d-flex align-items-center">
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
                        <a href="Fabrication.jsp" class="active">
                            <i class="bi bi-circle"></i><span>Fabrication</span>
                        </a>
                    </li>
                    <li>
                        <a href="MoyennePondere.jsp" class="active">
                            <i class="bi bi-circle"></i><span>Prix Moyenne Ponderee</span>
                        </a>
                    </li>
                    <li>
                        <a href="Vente.jsp" >
                            <i class="bi bi-circle"></i><span>Vente</span>
                        </a>
                    </li>
                    </ul>
                  </li><!-- End Forms Nav -->
              </aside><!-- End Sidebar-->
            
              <main id="main" class="main">
            
                <div class="pagetitle">
                  <h1>Stock Restant</h1>
                </div><!-- End Page Title -->
            
                <section class="section">
                <form>
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="card">
                        <div class="card-body">
                                      <h5 class="card-title">Details</h5>
                                      <!-- Active Table -->
                                      <table class="table">
                                        <thead>
                                          <tr>
                                            <th class="table-active" scope="col">Produit</th>
                                            <th class="table-active" scope="col">Quantite</th>
                                            <th class="table-active" scope="col">Prix</th>
                                          </tr>
                                        </thead>
                                        <tbody>
                                           <% for(int i=0 ; i < list.size() ; i++) {%>
                                          <tr>
                                              <td><%= list.get(i).getProduit() %></td>
                                              <td><%= list.get(i).getQuantite() %></td>
                                              <td><%= list.get(i).getPrix()%></td>
                                          </tr>
                                          <% } %>
                                        </tbody>
                                      </table>
                                      <!-- End Active Table -->
                        
                                    </div>
                                  </div>
                            </div>
                            
                          </form><!-- End General Form Elements -->
                </section>
            
              </main><!-- End #main -->
            
              <!-- ======= Footer ======= -->
              <footer id="footer" class="footer">
                <div class="copyright">
                  &copy; Copyright <strong><span>2022</span></strong>
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