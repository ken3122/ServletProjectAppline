package ru.appline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Map;

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
//import ru.appline.logic.User;


@WebServlet(urlPatterns="/get")
public class ServletList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Model model = Model.getInstance();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ServletList() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));

		if(id == 0) {
			pw.print(gson.toJson(model.getFromList()));
		} else if(model.containsKey(id)) {
				pw.print( gson.toJson(model.getFromList().get(id)) );
		} else {
				pw.print(gson.toJson(new ErrorMessage("�������� id. ������������ �� ������.")));
		}
		  //end doGet
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

		  
		  int id = jobj.get("id").getAsInt();
		  
		  response.setContentType("application/json;charset=utf-8");
		  PrintWriter pw = response.getWriter();
		  
			if(id == 0) {
				pw.print(gson.toJson(model.getFromList()));
			} else if(model.containsKey(id)) {
					pw.print( gson.toJson(model.getFromList().get(id)) );
			} else {
					pw.print(gson.toJson(new ErrorMessage("�������� id. ������������ �� ������.")));
			}
		
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter pw = response.getWriter();
//		
//		int id = Integer.parseInt(request.getParameter("id"));
//		
//		if(id == 0) {
//			pw.print("<html>" + 
//						"<h3>��������� ������������:</h3><br/>" +
//						"ID ������������: " + 
//						"<ul>");
//		
//			
//			for(Map.Entry<Integer, User> entry : model.getFromList().entrySet()) {
//				pw.print("<li>" + entry.getKey() + "</li>" + 
//						"<ul>" + 
//						"<li>���: " + entry.getValue().getName() + "</li>" + 
//						"<li>�������: " + entry.getValue().getSurname() + "</li>" +
//						"<li>��������: " + entry.getValue().getSalary() + "</li>" +
//						"</ul>");
//			}
//			pw.print("</ul>" +
//					"<a href=\"index.jsp\">�����</a>" +
//					"</html>");
//		} else if(id > 0) {
//			if(id > model.getFromList().size()) {
//				pw.print("<html>" +
//						"<h3>������ ������������ ���.</h3>" +
//						"<a href=\"index.jsp\">�����</a>" +
//						"</html>");
//			} else {
//			pw.print("<html>" +
//					"<h3>����������� ������������</h3>" +
//					"<br/>" +
//					"���: " + model.getFromList().get(id).getName() + "<br/>" +
//					"�������:" + model.getFromList().get(id).getSurname() + "<br/>" +
//					"��������:" + model.getFromList().get(id).getSalary() + "<br/>" +
//					"<a href=\"index.jsp\">�����</a>" +
//					"</html>");
//			}
//		} else {
//				pw.print("<html>" +
//						"<h3>id ������ ���� ������ ����!</h3>" +
//						"<a href=\"index.jsp\">�����</a>" +
//						"</html>");
//		}
//	}	




}
