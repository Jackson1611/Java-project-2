package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data_access.StudentDAO;
import model.Status;

/**
 * Servlet implementation class StudentDeleteServlet
 */
@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		StudentDAO studentDAO = new StudentDAO();
		int errorCode = studentDAO.deleteStudent(id);
		Gson gson = new Gson();
		
		String json = gson.toJson(new Status(errorCode)); 
		out.print(json);
	}

}
