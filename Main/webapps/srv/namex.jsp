        
<!DOCTYPE html>
<html>
    <head>
        <meta charset=utf8>
    </head><body>
<div>
    

<% if(request.getSession().getAttribute("logged_in_user") == null ) { %>
    You are not logged in. Please Sign Up or Login.
<% } else { %>
    <!-- in HTML, we can expand a session variable with the {} notation -->
    You are logged in as: ${logged_in_user}
<% } %>
</div>
</body>
</html>
