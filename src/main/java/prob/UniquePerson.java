package prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Version 1
 * 
 * @author Dhval
 * 
 */
public class UniquePerson {
	public static class Person {

		private String lastName;
		private String firstName;
		private String middleName;
		private String dateofBirth;
		private String ssn;
		private String sid;
		private String fbi;
		private Set<String> otns = new TreeSet<String>();

		public Person(String lastName, String firstName, String middleName,
				String dateofBirth, String ssn, String sid, String fbi, Set<String> otns) {

			this.lastName = lastName;
			this.firstName = firstName;
			this.middleName = middleName;
			this.dateofBirth = dateofBirth;
			this.ssn = ssn;
			this.sid = sid;
			this.fbi = fbi;
			this.otns = otns;

		}

		private static boolean isEmpty(String s) {
			if (s == null || s.trim().equals(""))
				return true;
			else
				return false;
		}

		private static boolean similar(Person p1, Person p2) {
			if (p1 == null || p2 == null)
				return false;
			String[] person1 = { p1.lastName, p1.firstName, p1.middleName,
					p1.dateofBirth, p1.ssn, p1.sid, p1.fbi };
			String[] person2 = { p2.lastName, p2.firstName, p2.middleName,
					p2.dateofBirth, p2.ssn, p2.sid, p2.fbi };
			List<String> list1 = new ArrayList<String>();
			List<String> list2 = new ArrayList<String>();
			list1.addAll(Arrays.asList(person1));
			list2.addAll(Arrays.asList(person2));
			if (p1.otns != null && !p1.otns.isEmpty()) {
				list1.addAll(p1.otns);
			}
			if (p2.otns != null && !p2.otns.isEmpty()) {
				list2.addAll(p2.otns);
			}
			if (list1.size() != list2.size()) {
				return false;
			}
			int count = 0;

			for (int i = 0; i < list1.size() && count <= 1; i++) {
				String s1 = list1.get(i);
				String s2 = list2.get(i);
				if (isEmpty(s1) && isEmpty(s2))
					return false;
				else if (isEmpty(s1) || isEmpty(s2))
					count++;
				else if (!s1.equalsIgnoreCase(s2))
					return false;
			}
			return (count <= 1) ? true : false;
		}

		private static Person merge(Person p1, Person p2) {
			if (p1 == null && p2 == null)
				return p1;
			if (p1 != null && p2 == null)
				return p1;
			if (p1 == null && p2 != null)
				return p2;
			if (p1.lastName == null)
				p1.lastName = p2.lastName;
			if (p1.firstName == null)
				p1.firstName = p2.firstName;
			if (p1.middleName == null)
				p1.middleName = p2.middleName;
			if (p1.dateofBirth == null)
				p1.dateofBirth = p2.dateofBirth;
			if (p1.ssn == null)
				p1.ssn = p2.ssn;
			if (p1.sid == null)
				p1.sid = p2.sid;
			if (p1.fbi == null)
				p1.fbi = p2.fbi;
			if (p1.otns == null || p1.otns.isEmpty())
				p1.otns = p2.otns;
			return p1;
		}

		public static Person[] merge(Set<Person> set) {
			if (set == null)
				return null;
			if (set.size() < 2)
				return set.toArray(new Person[set.size()]);
			Person[] person = set.toArray(new Person[set.size()]);
			// merge similar objects
			for (int i = person.length - 1; i > 0; i--) {
				Person tmpI = person[i];
				if (tmpI == null)
					continue;
				for (int j = 0; j < i; j++) {
					Person tmpJ = person[j];
					if (tmpJ == null)
						continue;
					if (similar(tmpI, tmpJ)) {
						// merge two person data into I
						person[i] = merge(tmpI, tmpJ);
						// delete person J
						person[j] = null;
					}
				}
			}
			// collect unique person
			List<Person> list = new ArrayList<Person>();
			for (Person p : person) {
				if (p != null)
					list.add(p);
			}
			return list.toArray(new Person[list.size()]);
		}

		public void addOTN(String otn) {
			otns.add(otn);
		}

		private boolean comp(String s1, String s2) {
			if (isEmpty(s1) && isEmpty(s2))
				return true;
			if (isEmpty(s1) || isEmpty(s2))
				return false;
			return s1.equalsIgnoreCase(s2);
		}

		private int compHash(int h, int p, String s) {
			if (s != null) {
				h = h * p + s.toUpperCase().hashCode();
			}
			return h;
		}

		public int hashCode() {

			int h = 31;
			h = compHash(h, 5, lastName);
			h = compHash(h, 7, firstName);
			h = compHash(h, 17, middleName);
			h = compHash(h, 23, dateofBirth);
			h = compHash(h, 29, ssn);
			h = compHash(h, 31, sid);
			h = compHash(h, 11, fbi);
			return h;
		}

		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof Person)) {
				return false;
			}
			Person otherPerson = (Person) obj;

			if (!comp(otherPerson.lastName, this.lastName))
				return false;
			if (!comp(otherPerson.firstName, this.firstName))
				return false;
			if (!comp(otherPerson.middleName, this.middleName))
				return false;
			if (!comp(otherPerson.dateofBirth, this.dateofBirth))
				return false;
			if (!comp(otherPerson.ssn, this.ssn))
				return false;
			if (!comp(otherPerson.sid, this.sid))
				return false;
			if (!comp(otherPerson.fbi, this.fbi))
				return false;
			Set<String> ot1 = otherPerson.otns;
			Set<String> ot2 = this.otns;
			if ((ot1 == null || ot1.isEmpty()) && (ot2 == null || ot2.isEmpty())) {
				return true;
			}
			if ((ot1 == null || ot1.isEmpty()) || (ot2 == null || ot2.isEmpty())) {
				return false;
			}
			String otn1 = "", otn2 = "";
			for (String s1 : ot1) {
				otn1 += s1;
			}
			for (String s1 : ot2) {
				otn2 += s1;
			}
			return comp(otn1, otn2);
		}

		@Override
		public String toString() {
			return "Person [lastName=" + lastName + ", firstName=" + firstName
					+ ", middleName=" + middleName + ", dateofBirth=" + dateofBirth
					+ ", ssn=" + ssn + ", sid=" + sid + ", fbi=" + fbi + ", otns=" + otns
					+ "] ID# " + hashCode();
		}

	}
}
