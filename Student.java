package model;

public class Student {
	private int id;
	private String firstname;
	private String lastname;
	private String streetaddress;
	private String postcode;
	private String postoffice;

	public Student(int id, String firstname, String lastname, String streetaddress, String postcode,
			String postoffice) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.postcode = postcode;
		this.postoffice = postoffice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstame() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getAddress() {
		return streetaddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getPostOffice() {
		return postoffice;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String toString() {
		return id + ":" + firstname + " " + lastname + ", " + streetaddress + ", " + postcode + " " + postoffice;
	}
}
