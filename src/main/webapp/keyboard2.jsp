<%@ page import="com.classes.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Keyboard <%= request.getAttribute("id") %></title>
    </head>
    <body>
        <%  Keyboard keyboard = (Keyboard) request.getAttribute("keyboard");
            String id = (String) request.getAttribute("id"); %>
        <h1>Keyboard <%= request.getAttribute("id") %></h1>
        <ul>
            <li>Name: <%= keyboard.getName() %></li>
            <li>Manufacturer: <%=  keyboard.getManufacturer() %></li>
            <li>Year: <%= keyboard.getYear() %></li>
            <li>Ergonomic: <%= keyboard.isErgonomic() %></li>
            <li>Backlight: <%= keyboard.isBacklight() %></li>
            <li>Layout: <%=  keyboard.getLayout() %></li>
            <li> commenti:
                <ul>
                    <% if( keyboard.getCommenti().size() ==  0 ) { %>
                        <li> No comments </li>
                    <% } else { %>
                        <% for(int i = 0; i < keyboard.getCommenti().size();  i++) { %>
                            <li> <%= keyboard.getCommenti().get(i) %> </li>
                        <% } %>
                    <% } %>
                </ul>
            </li>
        </ul>
        <form method="POST" action= <%= "keyboard?id=" + id %> autocomplete="off">
            <p> Add comment:</p>
            <input type="text" name="comment">
            <button type="submit">Submit</button>
        </form>
        <p>Back to the <a href="index.html"> home</a></p>
        <p>Back to the <a href="keyboard"> Keyboard page</a></p>
    </body>
</html>