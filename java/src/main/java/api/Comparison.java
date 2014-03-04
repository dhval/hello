package api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comparison {

	class Cat implements Comparable<Cat> {
		public Cat(int age) {
			super();
			this.age = age;
		}

		private int age = 0;

		public int compareTo(Cat o) {
			return this.age - o.age;
		}

		public String toString() {
			return Integer.toString(age);
		}
	}

	public static void main(String[] args) {
		Comparison self = new Comparison();

		List<Cat> cats = new ArrayList<Cat>();
		cats.add(self.new Cat(1));
		cats.add(self.new Cat(4));
		cats.add(self.new Cat(3));
		cats.add(self.new Cat(2));

		Collections.sort(cats);

		System.out.println(cats);

	}

}
