<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion - Voyage App</title>
    <link rel="stylesheet" href="../css/form.css"> 
    <link rel="stylesheet" href="../css/VarStyle.css"> 
           <link rel="stylesheet" href="../css/NavBar.css">
</head>
  
<body>
	 <jsp:include page="header.jsp"></jsp:include>
	
	
	<div class="formContainer">
		<div class="LoginLinkSection">
		<h2>Already have an account ?</h2>
		<a href="login.html">Login</a>
		</div>
			<form action="/agence-de-voyage/UserRegSeverlet" method="post" class="LoginForm">
				
				<div class="inputStyle">
					<label for="name">name</label>
					<input id="name" type="text" placeholder="name">
				</div>
				
				<div class="inputStyle">
					<label for="email">E-mail</label>
					<input  id="email" type="text" placeholder="email">
				</div>
				
				<div class="inputStyle">
					<label for="pwd">Password</label>
					<input  id="pwd" type="password" placeholder="Password">
				</div>
				
				<div class="inputStyle">
					<label for="phone">Phone Number</label>
					<input id="phone" type="text" placeholder="phone">
				</div>
						
				
				<div class="radioSection">
				<label class="genderTitle">Gender</label>
				<div  class="radioStyle">
				<input id=male type="radio" name="genre" value=m>
				<label for=male>Male</label>
				</div>
				<div  class="radioStyle">
				<input id=female type="radio"  name="genre" value=f>
				<label for="female">Female</label>
				</div>
				</div>
				<Button> Create Account</Button>
			</form>
	</div>
</body>
</html>
