package prob.amzn;

import java.util.*;

/**
  Given k sorted arrays each of length n, construct a single merged and sorted array.
  https://www.evernote.com/l/AAsbwGCMIr1Kl6p-pDAWWqQ-6dzUuA6zhSs
**/
public class MergeArray {

private static final int k = 7;
private static int SIZE = 0;
private static Integer[][] ARRAY = new Integer[k][];
private static final Random random = new Random();

public static Integer[] merge() {
  Integer[] result = new Integer[SIZE];
  // Create Heap (PQ) of size k
  PriorityQueue<DataNode> pq = new PriorityQueue<DataNode>(k);
  // Initialize PQ
  for (int i=0; i<k; i++) {
    DataNode node = new DataNode(i, 0, ARRAY[i][0]);
    pq.offer(node);
  }
  // Keep polling PQ, filling next value with same array.
  for(int i=0; i<SIZE; i++) {
    DataNode node = pq.poll();
    System.out.print(node);
    result[i] = node.value;
    if (node.index + 1 < ARRAY[node.key].length)    {
      node = new DataNode(node.key, node.index + 1, ARRAY[node.key][node.index + 1]);
      pq.offer(node);
    }
  }
  return result;
}


public static void main(String[] s) {
  Integer[] result = merge();
  System.out.println(Arrays.toString(result));
}

// Generate Test Data
static {
  for (int i = 0; i < k; i++) {
    int ranInt = 10 + random.nextInt(20);
    ARRAY[i] = prob.Utility.randArray(ranInt);
    Arrays.sort(ARRAY[i]);
    SIZE += ARRAY[i].length;
    System.out.println("k=" + i + "---" + Arrays.toString(ARRAY[i]));
  }
}

}

// Helper class to hold data for PQ Node.
class DataNode implements Comparable<DataNode> {
  Integer value;
  // Array
  Integer key;
  // Index in Array
  Integer index;

  public DataNode(Integer k, Integer i, Integer v) {
    value = v;
    key = k;
    index = i;
  }

  public int compareTo(DataNode node) {
    return this.value - node.value;
  }

  public String toString() {
    return String.format("key<%s>, value<%s> \t", key, value);
  }
}