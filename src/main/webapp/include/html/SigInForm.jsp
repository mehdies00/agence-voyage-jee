<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion - Voyage App</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/include/css/form.css"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/include/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/include/css/VarStyle.css">
</head>
  
<body>
  
<section class="login">
<!--
    <form action="${pageContext.request.contextPath}/LogIn.lg" method="post" class="login__form hidden" id="loginCard">
        <h2 class="login__heading" style="margin-bottom: 2rem;">
            Log in to view this page
        </h2>

        <div class="login__social">
            <button type="button" class="social-btn social-btn--facebook">
                <svg class="social-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M14 13.5h2.5l1-4H14v-2c0-1.03 0-2 2-2h1.5V2.14c-.326-.043-1.557-.14-2.857-.14-3.338 0-5.467 2.05-5.467 5.57V10H6.5v3.5H9.2v9H14v-9z"/>
                </svg>
                <span>Continue with Facebook</span>
            </button>
            <button type="button" class="social-btn social-btn--google">
                <svg class="social-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/>
                    <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/>
                    <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"/>
                    <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/>
                </svg>
                <span>Continue with Google</span>
            </button>
        </div>

        <div class="login__separator">
            <span class="login__separator--text">OR</span>
        </div>

        <div class="login__group">
            <input type="email" name="email" id="email" class="login__input" placeholder=" ">
            <label for="email" class="login__label">Email</label>
            <span class="login__error-message">Email invalide</span>
        </div>

        <div class="login__group">
            <input type="password" name="pass" id="password" class="login__input" placeholder=" ">
            <label for="password" class="login__label">Password</label>
            <span class="login__error-message">Mot de passe requis</span>
            <div class="login__toggle-btn" id="togglePasswordBtn">
                <svg class="login__eye login__eye--open" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"><path d="M12 2C17.5228 2 22 6.47715 22 12C22 17.5228 17.5228 22 12 22C6.47715 22 2 17.5228 2 12C2 6.47715 6.47715 2 12 2ZM12 4C7.58172 4 4 7.58172 4 12C4 16.4183 7.58172 20 12 20C16.4183 20 20 16.4183 20 12C20 7.58172 16.4183 4 12 4ZM12 7C14.7614 7 17 9.23858 17 12C17 14.7614 14.7614 17 12 17C9.23858 17 7 14.7614 7 12C7 11.4872 7.07719 10.9925 7.22057 10.5268C7.61175 11.3954 8.48527 12 9.5 12C10.8807 12 12 10.8807 12 9.5C12 8.48527 11.3954 7.61175 10.5269 7.21995C10.9925 7.07719 11.4872 7 12 7Z"></path></svg>
                <svg class="login__eye login__eye--close" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"><path d="M9.34268 18.7819L7.41083 18.2642L8.1983 15.3254C7.00919 14.8874 5.91661 14.2498 4.96116 13.4534L2.80783 15.6067L1.39362 14.1925L3.54695 12.0392C2.35581 10.6103 1.52014 8.87466 1.17578 6.96818L3.14386 6.61035C3.90289 10.8126 7.57931 14 12.0002 14.0001C16.4211 14.0001 20.0976 10.8126 20.8566 6.61035L22.8247 6.96818C22.4803 8.87466 21.6446 10.6103 20.4535 12.0392L22.6068 14.1925L21.1926 15.6067L19.0393 13.4534C18.0838 14.2498 16.991 14.8874 15.8021 15.3254L16.5896 18.2642L14.6578 18.7819L13.87 15.8418C13.2623 15.9459 12.6376 16.0001 12.0002 16.0001C11.3629 16.0001 10.7381 15.9459 10.1305 15.8418L9.34268 18.7819Z"></path></svg>
            </div>
        </div>

        <button type="submit" class="login__button">Log in</button>

        <div class="login__switch">
            Don't have an account?
            <strong id="showRegister">Sign up</strong>
        </div>
    </form>
  -->
    <form action="${pageContext.request.contextPath}/SignIn.lg" method="post" class="login__form " id="registerCard">
        <h2 class="login__heading">Create Account</h2>

        <div class="login__group">
            <input required type="text" name="username" id="reg-username" class="login__input" placeholder=" ">
            <label for="reg-username" class="login__label">Username</label>
            <span class="login__error-message">Nom d'utilisateur requis</span>
        </div>

        <div class="login__group">
            <input required type="email" name="email" id="reg-email" class="login__input" placeholder=" ">
            <label for="reg-email" class="login__label">Email</label>
            <span class="login__error-message">Email invalide</span>
        </div>

        <div class="login__group">
            <input required type="password" name="pass" id="reg-password" class="login__input" placeholder=" ">
            <label for="reg-password" class="login__label">Password</label>
            <span class="login__error-message">Mot de passe requis</span>
        </div>

        <div class="login__group">
            <input required type="password" id="reg-password-confirm" class="login__input" placeholder=" ">
            <label for="reg-password-confirm" class="login__label">Confirm Password</label>
            <span class="login__error-message">Les mots de passe ne correspondent pas</span>
        </div>

        <button type="submit" class="login__button">Sign Up</button>

        <div class="login__switch">
            Already have an account?
            <a href="./LoginForm.jsp" id="showLogin">Log in</a>            
        </div>
    </form>

</section>

   

<script src="${pageContext.request.contextPath}/include/js/form.js"></script>
</body>
</html>
