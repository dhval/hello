/*
Assumption: The lists a and b which are the input parameters contain distinct integers (objects of the Integer class).
The integers are unique within each list. The function getIntersection returns a list which has the integers that appear in both lists.
Eg: List a -->  4,5,8,9,10 List b -->  1,3,4,9,11 Output List --> 4,9
 */
package prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dxm6974
 */
public class IntersectionFinder {

    // a simple (and slow) approach.
    public List<Integer> getIntersection1(List<Integer> a, List<Integer> b) {
        List<Integer> match = new ArrayList<Integer>();
        Iterator<Integer> i = a.iterator();
        while (i.hasNext()) {
            int newelement = (int) i.next();
            if (b.contains(newelement))
                match.add(newelement);
        }
        return match;
    }
    // based on merging and sorting two lists.

    public List<Integer> getIntersection2(List<Integer> a, List<Integer> b) {
        List<Integer> c = new ArrayList<Integer>();
        // sort both lists asc order
        Collections.sort(a);
        Collections.sort(b);
        int listA = 0;
        int listB = 0;
        int listC = 0;
        while (listA < a.size() && listB < b.size()) {
            int elementA = a.get(listA);
            int elementB = b.get(listB);
            if (elementA == elementB) {
                listA++;
                listB++;
                if (!c.contains(elementA)) {
                    c.add(elementA);
                    listC++;
                }
            } else if (elementA < elementB)
                listA++;
            else
                listB++;

        }
        return c;
    }

    public static void main(String[] args) {
        Integer[] a = {2, 9, 0, 6, 7, 4, 23};
        Integer[] b = {9, 3, 4, 23, 1, 8, 0};
        System.out.println(new IntersectionFinder().getIntersection1(Arrays.asList(a), Arrays.asList(b)));
        System.out.println(new IntersectionFinder().getIntersection2(Arrays.asList(a), Arrays.asList(b)));
    }
}
