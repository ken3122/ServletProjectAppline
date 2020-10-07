package ru.appline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import ru.appline.logic.ErrorMessage;
import ru.appline.logic.Model;
import ru.appline.logic.User;


@WebServlet("/update")
public class ServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Model model = Model.getInstance();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ServletUpdate() {
    }

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
		  
		  StringBuffer jb = new StringBuffer();
		  String line;
		  try {
			  BufferedReader reader = request.getReader();
			  while((line = reader.readLine())!= null) {
				  jb.append(line);
			  }
		  } catch (Exception e) {
			  System.out.println("Error");
		  }
		  
		  JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);	  
		  
		  int id = jobj.get("id").getAsInt();
		  String name = jobj.get("name").getAsString();
		  String surname = jobj.get("surname").getAsString();
		  Double salary = jobj.get("salary").getAsDouble();
		  User user = new User(name, surname, salary);
		  
		  response.setContentType("application/json;charset=utf-8");
		  PrintWriter pw = response.getWriter();
		  
		  if(model.containsKey(id)) {
			  model.add(user, id);
			  pw.print(gson.toJson(model.getFromList()));
		  } else {
			  pw.print(gson.toJson(new ErrorMessage("�������� id. ������������ �� ������.")));
		  }


		  
	}

}
