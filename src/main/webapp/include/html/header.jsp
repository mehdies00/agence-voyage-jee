<%@ page import="Model.entity.Client" %>
<%
    // On récupère l'utilisateur stocké en session par la Servlet
    Client user = (Client) session.getAttribute("user");
%>

<nav class="navbar">
    <div class="navbar-container">

        <div class="navbar__start">
            <a href="index.jsp" class="navbar__logo">logo</a>
            <ul class="navbar__menu">
                <li class="navbar__list">
                    <a href="index.jsp" class="navbar__link">Home</a>
                </li>
            </ul>
        </div>

        <div class="navbar__end">
            <div class="navbar__search">
                <i class="fa-solid fa-magnifying-glass navbar__search-icon"></i>
                <input type="text" class="navbar__search-input" placeholder="Explore by destination">
            </div>

            <div class="navbar__auth">
                <% if (user != null) { %>
                    <div class="navbar__user-profile">
                        <span class="user-name">Hello, <strong><%= user.getName() %></strong></span>
<a href="${pageContext.request.contextPath}/Logout.lg" class="button button--logout">Logout</a>                    </div>
                <% } else { %>
                    <a href="./LoginForm.jsp" class="button button--login">Log in</a>
                    <a href="./SigInForm.jsp" class="button button--signup">Sign Up</a>
                <% } %>
            </div>
        </div>

    </div>
</nav>