package prob;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
/**
 * Version 1
 * @author Dhval
 *
 */
public class TestUniquePerson {

	// lastName, firstName, middleName, dateofBirth, ssn, sid, fbi, otns
	static String[] person1 = { "smIth", "john", "m", "20/20/2020", "785856256",
			"SID646", "FBI", "OTN123", "OTN124" };
	static String[] person2 = { "smIth", "", "m", "20/20/2020", "785856256",
			"SID646", "FBI", "OTN123", "OTN124" };
	static String[] person3 = { "", "", "m", "20/20/2020", "785856256",
			"SID646", "FBI", "OTN124", "OTN123" };
	static String[] person4 = { "smIth", "john", "m", "20/20/2020", "785856256",
			"SID646", "FBI", "OTN123", "OTN124" };
	static String[] person5 = { "smIth", "john", "m", "20/20/2020", "785856256",
			"SID646", "FBI", "OTN124", "OTN123" };
	static String[] person6 = { "smIth", "john", "m", "20/20/2020", "785856256",
			"SID646", "FBI", "OTN123", "OTN124" };
	static String[] person7 = { "smIth", "john", "m", "20/20/2020", "785856256",
			"SID646", "FBI", "OTN124", "OTN123" };
	static String[] person8 = { "smIth", "john", "m", "20/20/2020", "785856256",
			"SID646", "FBI"};
	static String[] person9 = { "smIth", "john", "m", "20/20/2020", "785856256",
			"SID646", "FBI", "OTN123", "OTN124" };
	static String[] person10 = { "smIth", "john", "m", "20/20/2020", "785856256",
			"SID646" };

	static String[][] DATA = { person1, person2, person3, person4, person5, person6,
			person7, person8, person9, person10 };

	public static UniquePerson.Person toPerson(String[] person) {
		if (person == null || person.length == 0)
			return null;
		String lastName = (person.length > 0) ? person[0] : null;
		String firstName = (person.length > 1) ? person[1] : null;
		String middleName = (person.length > 2) ? person[2] : null;
		String dateofBirth = (person.length > 3) ? person[3] : null;
		String ssn = (person.length > 4) ? person[4] : null;
		String sid = (person.length > 5) ? person[5] : null;
		String fbi = (person.length > 6) ? person[6] : null;
		Set<String> otns = new TreeSet<String>();
		for (int i = 7; i < person.length; i++) {
			otns.add(person[i]);
		}
		return new UniquePerson.Person(lastName, firstName, middleName, dateofBirth, ssn,
				sid, fbi, otns);
	}

	public static void main(String[] s) {
		Set<UniquePerson.Person> persons = new HashSet<UniquePerson.Person>();
		System.out.println("----- INPUT -----");
		for (int i = 0; i < DATA.length; i++) {
			UniquePerson.Person person = toPerson(DATA[i]);
			boolean result = persons.add(person);
			// System.out.println(i + "----- INSERT -----" + result + " SIZE " +
			// persons.size());
			System.out.println(person);
		}

		System.out.println("----- SET -----");

		for (UniquePerson.Person p : persons) {
			System.out.println(p);
		}

		UniquePerson.Person[] uniques = UniquePerson.Person.merge(persons);

		System.out.println("----- Final Result -----");

		for (UniquePerson.Person p : uniques) {
			if (p != null)
				System.out.println(p);
		}

	}
}
