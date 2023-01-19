package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.util.List;
import data_access.ConnectionParameters;
import data_access.DbUtils;
import model.Student;

public class StudentDAO {

	public StudentDAO() {
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}

	private static Connection openConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
				ConnectionParameters.password);
	}
	/* Get all student */

	public static List<Student> getAllStudents() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id,firstname,lastname, streetaddress, postcode,postoffice FROM Student";
			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				studentList.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));
			}
		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getStudent() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		return studentList;
	}

	/* Get student by given id */
	public Student getStudentByID(int studentId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Student student = null;

		try {
			connection = openConnection();

			String sqlText = "SELECT * FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);

			resultSet = preparedStatement.executeQuery();
			boolean rowFound = false;
			while (resultSet.next()) {
				rowFound = true;

				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				student = new Student(id, firstname, lastname, streetaddress, postcode, postoffice);

			}
			if (rowFound == false) {
				System.out.println("Unknown student id (" + studentId + ")");

			}

		} catch (SQLException sqle) {
			System.out.println("[ERROR] StudentDAO: getStudentByID() failed. " + sqle.getMessage() + "\n");
			student = null;
		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return student;

	}

	public int insertStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "INSERT INTO Student (id,firstname,lastname, streetaddress, postcode,postoffice) VALUES (?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstame());
			preparedStatement.setString(3, student.getLastname());
			preparedStatement.setString(4, student.getAddress());
			preparedStatement.setString(5, student.getPostcode());
			preparedStatement.setString(6, student.getPostOffice());

			preparedStatement.executeUpdate();
			errorCode = 0;

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				errorCode = 1;
			} else {
				System.out.println("\n[ERROR] StudentDAO: insertStudent() failed. " + sqle.getMessage() + "\n");
				errorCode = -1;
			}

		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}

	public String getAllStudentsJSON() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id,firstname,lastname, streetaddress, postcode,postoffice FROM Student";
			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				studentList.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));
			}
		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getStudent() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		Gson gson = new Gson();
		String jsonString = gson.toJson(studentList);
		return jsonString;

	}

	public int deleteStudent(int studentId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "DELETE FROM Student WHERE id = ?";
			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);

			int deleted = preparedStatement.executeUpdate();
			if (deleted == 0) {
				System.out.println("Nothing deleted. Unknown student id (" + studentId + ")");
				errorCode = 1;
			} else {
				System.out.println("Student deleted!");
				errorCode = 0;
			}
		} catch (SQLException sqle) {

			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
			errorCode = 1;
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}
		return errorCode;
	}

	public int updateStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "UPDATE Student SET firstname = ?,lastname = ?, streetaddress = ?, postcode = ?,postoffice = ? WHERE id=?";

			preparedStatement = connection.prepareStatement(sqlText);

			preparedStatement.setString(1, student.getFirstame());
			preparedStatement.setString(2, student.getLastname());
			preparedStatement.setString(3, student.getAddress());
			preparedStatement.setString(4, student.getPostcode());
			preparedStatement.setString(5, student.getPostOffice());
			preparedStatement.setInt(6, student.getId());

			int updated = preparedStatement.executeUpdate();
			if (updated == 0) {
				System.out.println("Nothing deleted. Unknown student id (" + student.getId() + ")!");
				errorCode = 1;
			} else {
				System.out.println("Student data updated!");
				errorCode = 0;
			}
		} catch (SQLException sqle) {

			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
			errorCode = 1;
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}
		return errorCode;
	}
}
