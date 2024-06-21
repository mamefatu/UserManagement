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
	<div class="container" >
	    <div class="row">
	    	<div class="col-md-10 col-md-offset-2">
	    		<div class="panel panel-default">
				  	<div class="panel-heading">
				    	<h3 class="panel-title">Add User : <span class="${empty form.erreurs ? 'text-success' : 'text-danger'} text-center">${form.resultat}</span></h3>
				 	</div>
		      					 
				  	<div class="panel-body text-warning">
				    	<form action="<c:url value="/ajoutUtilisateur" />" method="post" >
	                    <fieldset>
				    	  	<div class="form-group">
					    		<span>
					    			<label for="prenom">Firstname<span class="text-danger">*</span> <span class="text-danger">${form.erreurs.prenom }</span></label>
					    			<input class="form-control" placeholder="" id="prenom" name="prenom" type="text" value="<c:out value="${utilisateur.prenom }" /> ">				    			
					    		</span>
					    		<span>
					    			<label for="nom">Lastname: <span class="text-danger">*</span> <span class="text-danger">${form.erreurs.nom }</span></label>
					    			<input class="form-control" placeholder="" id="nom" name="nom" type="text" value="<c:out value="${utilisateur.nom }" /> ">				    			
					    		</span>
				    	  		<span>
					    	  		<label for="login">Mail: <span class="text-danger">*</span> <span class="text-danger">${form.erreurs.login }</span></label>
					    		    <input class="form-control" placeholder="" value="<c:out value="${utilisateur.mailUtilisateur }" /> " id="login" name="login" type="text">
					    		</span>
					    		<span>	
					    			<label for="motdepasse">Password: <span class="text-danger">*</span> <span class="text-danger">${form.erreurs.motDePasse }</span></label>
					    			<input class="form-control" placeholder="" id="motdepasse" name="motdepasse" type="password" value="">				    			
				    			</span>
					    		<span>
					    			<label for="confirmation">Confirm: <span class="text-danger">*</span></label>
					    			<input class="form-control" placeholder="" id="confirmation" name="confirmation" type="password" value="">
					    		</span>
					    		<span>
					    			<label for="adresse">Adress: <span class="text-danger">*</span> <span class="text-danger">${form.erreurs.adresse }</span></label>
					    			<input class="form-control" placeholder="" id="adresse" name="adresse" type="text" value="<c:out value="${utilisateur.adresseUtilisateur }" /> ">
					    		</span>
					    		
					    	</div>
				    		<input class="btn btn-lg btn-warning btn-block " type="submit" value="Ajouter">
				    	</fieldset>
				      	</form>
				      	<br/>
			     		<a class='btn btn-danger btn-xs' href="<c:url value="/accueil"> </c:url>"><span class="glyphicon glyphicon-back"></span> Back</a>
	                   </div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="<c:url value="/inc/jquery.min.js" /> "></script>
    <script src="<c:url value="/inc/bootstrap.min.js" /> "></script>
    <script src="<c:url value="/inc/theme.js" /> " ></script>
	<script> 
		$(document).ready(function(){
 			$('select[name="contactAnterieur"]').change(function(){
  				$('textarea[name="descriptionContact"]').prop('disabled',this.value != 0 ?false:true);
 			});
		});
	</script>
</body>
</html>