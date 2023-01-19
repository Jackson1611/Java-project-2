package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data_access.StudentDAO;
import model.Student;


@WebServlet("/jsontest")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		Student student1 = new Student(11, "loc", "nguyen", "1", "2", "3");
		Student student2 = new Student(12, "loc", "nguyen", "3", "2", "1");

		Gson gson = new Gson();
		String jsonString1 = gson.toJson(student1);
		String jsonString2 = gson.toJson(student2);


		out.println(jsonString1);
		out.println(jsonString2);
	}

}
