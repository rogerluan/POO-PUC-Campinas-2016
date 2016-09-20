import java.util.ArrayList;

public class StudentManager {
	private ArrayList<Student> students;

	/*
	 * Getters
	 */
	public ArrayList<Student> getStudents() {
		return students;
	}

	/*
	 * Setters
	 */
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	/*
	 * Student Manager Methods
	 */
	
	/**
	 * Registers a new student to the system.
	 * @param newStudent	The new student that's being added to the system.
	 * @return 				True if the operation was successful, else false.
	 */
	public Boolean addStudent(Student newStudent) {
		for (Student student : students) {
			if (student.getRA().equals(newStudent.getRA())) {
				return false;
			}
		}
		return students.add(newStudent);
	}	
	
	public void listAllStudents() {
		for (Student student : students) {
			System.out.println(student.toString());
		}
	}
}
