<!DOCTYPE html>
<html>
    <head>
        <title>Keyboards</title>
    </head>
    <body>
        <%@ page import="java.util.ArrayList, com.classes.*" %>
        <h1>Keyboards</h1>
        <%
            ServletContext context = request.getServletContext();
            ArrayList<Keyboard> keyboards = (ArrayList<Keyboard>)context.getAttribute("keyboards");
        %>
        <% if (keyboards.size() == 0) { %>
            <p> No keyboards available </p>
        <% } else { %>
            <ul>
                <% for(int i = 0; i < keyboards.size(); i++){ %>
                        <li> <%= keyboards.get(i).toString() %>
                        <a href=<%="/keyboard?id=" + keyboards.get(i).getId() %>>More info</a>
                        <a href=<%="/keyboard?id=" + keyboards.get(i).getId() + "&edit=1" %>>Edit</a>
                        </li>

                <% } %>
            </ul>
        <% } %>
        <form method="POST" action="keyboard" autocomplete="off">
            <p> Enter keyboard name </p>
            <input type="text" name="kName">
            <br>
            <p> Enter manufacturer name </p>
            <input type="text" name="mName">
            <br>
            <p> Enter year of construction </p>
            <input type="number" name="year" value="1977">
            <br>
            <input type="checkbox" id="ergonomic" name="ergonomic" value="ergonomic">
            <label for="ergonomic">Ergonomic</label><br>
            <input type="checkbox" id="backlight" name="backlight" value="backlight">
            <label for="backlight">Backlight</label><br>
            <label for="layout">Choose a layout:</label>
            <select id="layout" name="layout">
              <option value="QWERTY">QWERTY</option>
              <option value="AZERTY">AZERTY</option>
              <option value="Dvorak">Dvorak</option>
            </select>
            <br>
            <button type="submit">Submit</button>
        </form>
        <p>Back to the <a href="index.html"> home</a></p>
    </body>
</html>