<%@ page import="com.classes.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Keyboard <%= request.getAttribute("id") %> Edit</title>
    </head>
    <body>
        <%  Keyboard keyboard = (Keyboard) request.getAttribute("keyboard");
            String id = (String) request.getAttribute("id"); %>
        <h1>Edit keyboard <%= request.getAttribute("id") %> </h1>
        <form method="post" action=<%= "/keyboard?edit=1&id=" +id %> autocomplete="off">
            <p> Keyboard name: </p>
            <input type="text" name="kName" value= <%= ""+keyboard.getName() %> >
            <p> Manufacturer name: </p>
            <input type="text" name="mName" value= <%= ""+keyboard.getManufacturer() %>>
            <p> Year of construction: </p>
            <input type="number" name="year" value= <%= keyboard.getYear() %>><br>
            <input type="checkbox" id="ergonomic" name="ergonomic" value="ergonomic"
                <% if(keyboard.isErgonomic()){ %>
                    checked
                <% } %>>
            <label for="ergonomic">Ergonomic</label><br>
            <input type="checkbox" id="backlight" name="backlight" value="backlight"
                <% if(keyboard.isBacklight()){ %>
                    checked
                <% } %>>
            <label for="backlight">Backlight</label><br>
            <label for="layout">Choose a layout:</label>
            <select id="layout" name="layout">
              <option value="QWERTY"
                <% if(keyboard.getLayout().equals("QWERTY")) { %>
                    selected
                <% } %>
              >QWERTY</option>
              <option value="AZERTY"
                <% if(keyboard.getLayout().equals("AZERTY")) { %>
                    selected
                <% } %>
              >AZERTY</option>
              <option value="Dvorak"
                <% if(keyboard.getLayout().equals("Dvorak")) { %>
                    selected
                <% } %>
              >Dvorak</option>
            </select>
            <button type="submit">Submit changes</button>
        </form>
        <p>Back to the <a href="index.html"> home</a></p>
        <p>Back to the <a href="keyboard"> Keyboard page</a></p>
    </body>
</html>