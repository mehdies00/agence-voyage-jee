document.addEventListener('DOMContentLoaded', () => {
    const loginCard = document.getElementById('loginCard');
    const registerCard = document.getElementById('registerCard');
    const showRegisterBtn = document.getElementById('showRegister');
    const showLoginBtn = document.getElementById('showLogin');
	const googleBtn = document.getElementById('googleBtn');
	    const facebookBtn = document.getElementById('facebookBtn');
	/*
    if (showRegisterBtn) {
        showRegisterBtn.addEventListener('click', () => {
            loginCard.classList.add('hidden');
            registerCard.classList.remove('hidden');
        });
    }

    if (showLoginBtn) {
        showLoginBtn.addEventListener('click', () => {
            registerCard.classList.add('hidden');
            loginCard.classList.remove('hidden');
        });
    }
*/
    const loginForm = document.getElementById('loginCard');
    const loginEmail = document.getElementById('email');
    const loginPass = document.getElementById('password');

    const toggleBtn = document.getElementById('togglePasswordBtn');
    const eyeOpen = document.querySelector('.login__eye--open');
    const eyeClose = document.querySelector('.login__eye--close');

    if (toggleBtn && loginPass) {
        toggleBtn.addEventListener('click', (e) => {
            e.preventDefault();
            const isPassword = loginPass.type === 'password';
            if (isPassword) {
                loginPass.type = 'text';
                eyeOpen.style.display = 'none';
                eyeClose.style.display = 'block';
            } else {
                loginPass.type = 'password';
                eyeOpen.style.display = 'block';
                eyeClose.style.display = 'none';
            }
            loginPass.focus();
        });
    }

    /*if (loginForm) {
        loginForm.addEventListener('submit', (e) => {
            e.preventDefault();
            let isValid = true;

            if (loginEmail.value.trim() === '') {
                setError(loginEmail, "L'email est requis.");
                isValid = false;
            } else {
                setSuccess(loginEmail);
            }

            if (loginPass.value.trim() === '') {
                setError(loginPass, "Le mot de passe est requis.");
                isValid = false;
            } else {
                setSuccess(loginPass);
            }

            if (isValid) {
                alert("Connexion réussie ! (Simulation)");
            }
        });
    }*/

    const registerForm = document.getElementById('registerCard');
    const regUsername = document.getElementById('reg-username');
    const regEmail = document.getElementById('reg-email');
    const regPass = document.getElementById('reg-password');
    const regPassConfirm = document.getElementById('reg-password-confirm');

    /*if (registerForm) {
        registerForm.addEventListener('submit', (e) => {
            e.preventDefault();
            let isValid = true;

            if (regUsername.value.trim() === '') {
                setError(regUsername, "Le nom d'utilisateur est requis.");
                isValid = false;
            } else if (regUsername.value.trim().length < 3) {
                setError(regUsername, "3 caractères minimum.");
                isValid = false;
            } else {
                setSuccess(regUsername);
            }

            if (regEmail.value.trim() === '') {
                setError(regEmail, "L'email est requis.");
                isValid = false;
            } else if (!isEmailValid(regEmail.value.trim())) {
                setError(regEmail, "Email invalide.");
                isValid = false;
            } else {
                setSuccess(regEmail);
            }

            if (regPass.value.trim() === '') {
                setError(regPass, "Le mot de passe est requis.");
                isValid = false;
            } else if (regPass.value.trim().length < 6) {
                setError(regPass, "6 caractères minimum.");
                isValid = false;
            } else {
                setSuccess(regPass);
            }

            if (regPassConfirm.value.trim() === '') {
                setError(regPassConfirm, "Confirmez votre mot de passe.");
                isValid = false;
            } else if (regPassConfirm.value.trim() !== regPass.value.trim()) {
                setError(regPassConfirm, "Les mots de passe ne correspondent pas.");
                isValid = false;
            } else {
                setSuccess(regPassConfirm);
            }

            if (isValid) {
                alert("Inscription réussie ! (Simulation)");
            }
        });
    }*/

    function setError(input, message) {
        const group = input.parentElement;
        const errorDisplay = group.querySelector('.login__error-message');

        input.classList.add('input-error');

        if (errorDisplay) {
            errorDisplay.innerText = message;
            errorDisplay.classList.add('show');
        }
    }

    function setSuccess(input) {
        const group = input.parentElement;
        const errorDisplay = group.querySelector('.login__error-message');

        input.classList.remove('input-error');

        if (errorDisplay) {
            errorDisplay.classList.remove('show');
        }
    }

    function isEmailValid(email) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }
	// Dans ton fichier form.js
	if (googleBtn) {
	        googleBtn.addEventListener('click', () => {
	            // Redirection vers la servlet SocialLogin
	            window.location.href = '${pageContext.request.contextPath}/SocialLogin.lg';
 	        });
	    }

	    if (facebookBtn) {
	        facebookBtn.addEventListener('click', () => {
	            window.location.href = '${pageContext.request.contextPath}/SocialLogin.lg';
 
			});
	    }
});
