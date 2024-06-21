<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestion des utilisateurs</title>
	<link rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css" />" />
  	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>	
	<div class="col-md-12">
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab1info">
                        
                       		<div class="container">
							    <div class="row col-md-12 col-md-offset-2 custyle">
							    <table class="table table-striped custab">
								    <thead>
								    	<a href="<c:url value="/ajoutUtilisateur" /> " class="btn btn-danger btn-xs">Add user</a>
								        <tr>
								        	<th>Firstname</th>
								            <th>Lastname</th>
								            <th>Mail</th>
								            <th>Adress</th>
								            <th class="text-center">Actions</th>
								        </tr>
								    </thead>
								    <tbody>
								    	<c:forEach var="utilisateur" items="${requestScope.utilisateurs }">
								            <tr>
								                <td>${utilisateur.prenom}</td>
								                <td>${utilisateur.nom}</td>
								                <td>${utilisateur.mailUtilisateur}</td>
								                <td>${utilisateur.adresseUtilisateur}</td>
								                <td class="text-center">
								                	<a class='btn btn-info btn-xs' href="<c:url value="/modificationUtilisateur"> <c:param name="login" value="${utilisateur.mailUtilisateur }"/> </c:url>"><span class="glyphicon glyphicon-edit"></span> Edit</a> 
								                	<a href="<c:url value="/suppressionUtilisateur"> <c:param name="login" value="${utilisateur.mailUtilisateur }"/> </c:url>" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-remove"></span> Delete</a>
								                	<a class='btn btn-info btn-xs' href="<c:url value="/detailsUtilisateur"> <c:param name="login" value="${utilisateur.mailUtilisateur }"/> </c:url>"><span class="glyphicon glyphicon-eye"></span> View</a>
							                	</td>
								            </tr>
								        </c:forEach>
								    </tbody>
							    </table>
							    </div>
							</div>
                        	
                        </div>
                       	
                    </div>
                </div>
        </div>
	
	<script src="<c:url value="/inc/jquery.min.js" /> "></script>
    <script src="<c:url value="/inc/bootstrap.min.js" /> "></script>
    <script src="<c:url value="/inc/theme.js" /> " ></script>
	
</body>
</html>