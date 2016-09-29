import java.util.ArrayList;
import java.util.Scanner;

public class PersonManager {
	private ArrayList<Person> persons;
	
	public PersonManager() {
		super();
		persons = new ArrayList<Person>();
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}
	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}
	
	/*
	 * Person Manager Methods
	 */
	
	/**
	 * Registers a new person to the system.
	 * @param newPerson	The new person that's being added to the system.
	 * @return 				True if the operation was successful, else false.
	 */
	public Boolean addPerson(Person newPerson) {
		for (Person person : persons) {
			if (person.getUid().equals(newPerson.getUid())) {
				return false;
			}
		}
		return persons.add(newPerson);
	}	
	
	public void listEveryone() {
		if (persons.size() > 0) {
			for (Person person : persons) {
				System.out.println(person.toString());
			}	
		} else {
			System.out.println("There're no persons registered.");
		}
	}
	
	public Person getPersonByUid(String uid) {
		for (Person person : persons) {
			if (person.getUid().equals(uid)) {
				return person;
			}
		}
		return null;
	}

	/*//Currently not in use.
	public Person getPersonByName(String name) {
		for (Person person : persons) {
			if (person.getName().equals(name)) {
				return person;
			}
		}
		return null;
	}*/
	
	public Person choosePersonByName(String name, Scanner input) {
		ArrayList<Person> possiblePersons = getPersonsByName(name);
		
		Person choosenPerson = null;
		
		if (possiblePersons.size() > 0) {
			if (possiblePersons.size() > 1) {
				System.out.println("The search brought " + possiblePersons.size() + " results.");
				for (Person person : possiblePersons) {
					System.out.println("Person #" + (possiblePersons.indexOf(person)+1) + ": " + person.toString());
				}
				System.out.println("Type the index of the person that you want to see more detailed information, or any other key if you want to return to the main menu.");
				
				try {
					input = new Scanner(System.in);
					Integer option = input.nextInt();
					if (option >= 1 && option <= possiblePersons.size()) {
						choosenPerson = possiblePersons.get(option-1);
					}
				} catch (Exception e) {
					//do nothing, just let the program continue to the main menu
					return null;
				}
			} else {
				System.out.println("The search brought 1 result.");
				choosenPerson = possiblePersons.get(0);
			}
		}
		return choosenPerson;
	}

	public ArrayList<Person> getPersonsByName(String name) {
		ArrayList<Person> personList = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getName().contains(name)) {
				personList.add(person);
			}
		}
		return personList;
	}
}
