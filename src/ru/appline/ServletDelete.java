package ru.appline;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.appline.logic.ErrorMessage;
import ru.appline.logic.Model;

/**
 * Servlet implementation class ServletDelete
 */
@WebServlet("/delete")
public class ServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	Model model = Model.getInstance();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ServletDelete() {
    }


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		if(model.containsKey(id)) {
			model.remove(id);
			pw.print(gson.toJson(model.getFromList()));
		}  else {
				pw.print(gson.toJson(new ErrorMessage("Неверное id. Пользователь не найден.")));
		}

	  
//		pw.print( gson.toJson(model.getFromList()) );		
		
	}
}
