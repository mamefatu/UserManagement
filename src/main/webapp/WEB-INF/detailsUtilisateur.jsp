<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User management</title>
		<link rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css" />" />
	  	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
	</head>
	<body>	
		<div class="container">
	
			<div class="resume">
			    <header class="page-header">
			    	<h1 class="">User Informations</h1>
			  	</header>
			<div class="row">
			  <div class="col-xs-12 col-sm-12 col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">
			
			        <p>
			          <span class="text-primary">Lastname:</span> ${utilisateur.nom }<br/>
			          <span class="text-primary">Firstname:</span> ${utilisateur.prenom }<br/>
			          <span class="text-primary">Mail:</span> ${utilisateur.mailUtilisateur}<br/>
			          <span class="text-primary">Adress:</span> ${utilisateur.adresseUtilisateur }<br/>
			        </p>
			        
			      <br/>
			     	<a class='btn btn-warning btn-xs' href="<c:url value="/accueil"> </c:url>"><span class="glyphicon glyphicon-back"></span> Back</a>
			    
			
			  </div>
			</div>
			    
			</div>
	
		</div>
		
		<script src="<c:url value="/inc/jquery.min.js" /> "></script>
	    <script src="<c:url value="/inc/js/bootstrap.min.js" /> "></script>
	    <script src="<c:url value="/inc/theme.js" /> " ></script>
		
	</body>
</html>