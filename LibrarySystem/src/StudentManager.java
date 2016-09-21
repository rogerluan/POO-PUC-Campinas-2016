import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
	private ArrayList<Student> students;
	
	public StudentManager() {
		super();
		students = new ArrayList<Student>();
	}

	public ArrayList<Student> getStudents() {
		return students;
	}
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
		if (students.size() > 0) {
			for (Student student : students) {
				System.out.println(student.toString());
			}	
		} else {
			System.out.println("There're no students registered.");
		}
	}
	
	public Student getStudentByRA(String RA) {
		for (Student student : students) {
			if (student.getRA().equals(RA)) {
				return student;
			}
		}
		return null;
	}

	/*//Currently not in use.
	public Student getStudentByName(String name) {
		for (Student student : students) {
			if (student.getName().equals(name)) {
				return student;
			}
		}
		return null;
	}*/
	
	public Student chooseStudentByName(String name, Scanner input) {
		ArrayList<Student> possibleStudents = getStudentsByName(name);
		
		Student choosenStudent = null;
		
		if (possibleStudents.size() > 0) {
			if (possibleStudents.size() > 1) {
				System.out.println("The search brought " + possibleStudents.size() + " results.");
				for (Student student : possibleStudents) {
					System.out.println("Student #" + (possibleStudents.indexOf(student)+1) + ": " + student.toString());
				}
				System.out.println("Type the index of the student that you want to see more detailed information, or any other key if you want to return to the main menu.");
				
				try {
					input = new Scanner(System.in);
					Integer option = input.nextInt();
					if (option >= 1 && option <= possibleStudents.size()) {
						choosenStudent = possibleStudents.get(option-1);
					}
				} catch (Exception e) {
					//do nothing, just let the program continue to the main menu
					return null;
				}
			} else {
				System.out.println("The search brought 1 result.");
				choosenStudent = possibleStudents.get(0);
			}
		}
		return choosenStudent;
	}

	public ArrayList<Student> getStudentsByName(String name) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		for (Student student : students) {
			if (student.getName().contains(name)) {
				studentList.add(student);
			}
		}
		return studentList;
	}
}
