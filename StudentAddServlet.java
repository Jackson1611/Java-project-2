package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data_access.ConnectionParameters;
import data_access.DbUtils;
import data_access.StudentDAO;
import model.Status;
import model.Student;

@WebServlet("/addStudent")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String streetaddress = request.getParameter("streetaddress");
		String postcode = request.getParameter("postcode");
		String postoffice = request.getParameter("postoffice");

		Student student = new Student(id, firstname, lastname, streetaddress, postcode, postoffice);
		StudentDAO studentDAO = new StudentDAO();
		int error = studentDAO.insertStudent(student);
		Gson gson = new Gson();
		String errorCode = gson.toJson(new Status(error));
		out.print(errorCode);

	}

}