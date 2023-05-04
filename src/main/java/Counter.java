import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/counter")
public class Counter extends HttpServlet {
    int counter = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException
    {
        counter++;

        StringBuffer buf = new StringBuffer();
        buf.append("<!DOCTYPE html>\n");
        buf.append("<html>\n<head>\n<title>Counter (Java Servlet Example)</title>\n</head>\n<body>\n");
        buf.append("<h1>Counter (Java Servlet Example)</h1>\n");
        buf.append("<p>This is a counter: " + counter);
        buf.append("</p>\n");
        buf.append("<p>Back to the <a href=\"index.html\"> home</a> or <a href=\"counter\">reload</a> to update the counter.</p>");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter().append(buf.toString());
    }
}
