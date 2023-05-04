import java.io.*;
import java.util.ArrayList;
import com.classes.Keyboard;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/keyboard")
public class KeyboardServlet extends HttpServlet {
	private static final ArrayList<Keyboard> keyboards = deserializeDB();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	         throws IOException, ServletException {
		getServletContext().setAttribute("keyboards", keyboards);
		request.setAttribute("keyboards", keyboards);
		String id = request.getParameter("id");
		String edit = request.getParameter("edit");
		if(id == null){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/keyboard.jsp");
			dispatcher.forward(request, response);
		}else{
			RequestDispatcher dispatcher;
			if(edit == null){
				 dispatcher = request.getRequestDispatcher("/keyboard2.jsp");
			}else{
				 dispatcher = request.getRequestDispatcher("/keyboardEdit.jsp");
			}
			request.setAttribute("keyboard", getById(Integer.parseInt(id)));
			request.setAttribute("id", id);
			dispatcher.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		String id = request.getParameter("id");
		String edit = request.getParameter("edit");
		if(id == null){
			String kName = request.getParameter("kName");
			String mName = request.getParameter("mName");
			int year = Integer.parseInt(request.getParameter("year"));
			boolean ergonomic = request.getParameter("ergonomic") != null;
			boolean backlight = request.getParameter("backlight") != null;
			String layout = request.getParameter("layout");
			Keyboard k = new Keyboard();
			k.setAll(kName, mName, year, ergonomic, backlight, layout);
			ArrayList<Keyboard> keyboards = (ArrayList<Keyboard>) getServletContext().getAttribute("keyboards");
			keyboards.add(k);
			writeKeyboardsToDb();
			request.getRequestDispatcher("/keyboard.jsp").forward(request, response);
		}else{
			if(edit == null){
				String comment = request.getParameter("comment");
				String sanitizedComment = comment.replaceAll("\\\\", "");
				Keyboard k = getById(Integer.parseInt(id));
				if (k == null) throw new AssertionError();
				k.addCommenti(sanitizedComment);
				request.setAttribute("keyboard", k);
			}else{
				Keyboard k = updateKeyboard(request);
				request.setAttribute("keyboard", k);
			}
			request.setAttribute("id", id);
			//response.sendRedirect("/keyboard2.jsp");
			request.getRequestDispatcher("/keyboard2.jsp").forward(request, response);
		}

	}

	private Keyboard getById(int id){
		for (Keyboard keyboard : keyboards) {
			if (keyboard.getId() == id) return keyboard;
		}
		return null;
	}

	private void writeKeyboardsToDb(){
		try {
			FileOutputStream fileOut = new FileOutputStream("keyboards.db");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for (Keyboard k : keyboards) {
				out.writeObject(k);
			}
			out.close();
			fileOut.close();
		} catch (IOException e) {
			System.out.println("impossible to write file in db");
			e.printStackTrace();
		}
	}

	private static ArrayList<Keyboard> deserializeDB(){
		ArrayList<Keyboard> keyboards1 = new ArrayList<Keyboard>();
		Keyboard.setIdBase(numberOfItems());
		try {
			FileInputStream fileIn = new FileInputStream("keyboards.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			while (true) {
				Keyboard k = (Keyboard) in.readObject();
				keyboards1.add(k);
			}
		} catch (EOFException e) {
			System.out.println("Database importato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyboards1;
	}

	private Keyboard updateKeyboard(HttpServletRequest request){
		String id = request.getParameter("id");
		Keyboard k = getById(Integer.parseInt(id));
		assert k != null;
		String kName =request.getParameter("kName");
		String mName = request.getParameter("mName");
		int year = Integer.parseInt(request.getParameter("year"));
		boolean ergonomic = request.getParameter("ergonomic") != null;
		boolean backlight = request.getParameter("backlight") != null;
		String layout = request.getParameter("layout");
		try{
			k.setAll(kName, mName, year, ergonomic, backlight, layout);
		}catch(Exception e ){
			System.out.println("No keyboard added");
		}
		writeKeyboardsToDb();
		return k;
	}

	private static int numberOfItems(){
		int lines = 0;
		try{
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("keyboards.db"));
			while (reader.readObject() != null) lines++;
			reader.close();
		}catch(Exception e ){
			e.printStackTrace();
		}
		return lines;
	}

}
