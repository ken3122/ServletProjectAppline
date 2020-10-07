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

import ru.appline.logic.Model;
import ru.appline.logic.Result;


@WebServlet("/calc")
public class ServletCalc extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Model model = Model.getInstance();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ServletCalc() {
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		  
		  double leftArg = jobj.get("a").getAsDouble();
		  double rightArg = jobj.get("b").getAsDouble();
		  char mathOp = jobj.get("math").getAsString().charAt(0);
		  
		  response.setContentType("application/json;charset=utf-8");
		  PrintWriter pw = response.getWriter();
	  
		  pw.print(gson.toJson(new Result(leftArg, rightArg, mathOp)));
	}

}
