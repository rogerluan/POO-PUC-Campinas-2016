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
	public void addPerson(Person newPerson) throws PersonException{
		for (Person person : persons) {
			if (person.getUid().equals(newPerson.getUid())) {
				throw new PersonException("This " + person.getClass().getName() + " is already registered in our records.\n");
			}
		}
		persons.add(newPerson);
	}	
	
	public void listEveryone() throws PersonNotFoundException {
		if (persons.size() > 0) {
			for (Person person : persons) {
				System.out.println(person.toString());
			}	
		} else {
			throw new PersonNotFoundException("There's nobody registered in the database.");
		}
	}
	
	public Person getPersonByUid(String uid) throws PersonNotFoundException {
		if (persons.size() == 0) {
			throw new PersonNotFoundException("There's nobody registered in the database.");
		}
		for (Person person : persons) {
			if (person.getUid().equals(uid)) {
				return person;
			}
		}
		throw new PersonNotFoundException("There's nobody registered with the given unique identifier.");
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
	
	public Person choosePersonByName(String name, Scanner input) throws PersonNotFoundException {
		ArrayList<Person> possiblePersons = getPersonsByName(name);
		
		if (possiblePersons.size() > 1) {
			System.out.println("The search brought " + possiblePersons.size() + " results.");
			for (Person person : possiblePersons) {
				System.out.println("Person #" + (possiblePersons.indexOf(person)+1) + ": " + person.toString());
			}
			System.out.println("Type the index of the person that you want to see more detailed information, or any other key if you want to return to the main menu.");
			Integer option = ManagerHelper.chooseIndex(input, possiblePersons.size());
			return possiblePersons.get(option-1);
		} else {
			System.out.println("The search brought 1 result.");
			return possiblePersons.get(0);
		}
	}

	public ArrayList<Person> getPersonsByName(String name) throws PersonNotFoundException {
		ArrayList<Person> personList = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getName().contains(name)) {
				personList.add(person);
			}
		}
		if (personList.size() == 0) {
			throw new PersonNotFoundException("There's nobody with the given name.");
		}
		return personList;
	}
}
