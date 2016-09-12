package api;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.*;

public class RandomGen {
    
    static final Pattern CONSECUTIVE_NUM = Pattern.compile("(\\d)\\1");
    static int RANDON_MAX = 1000000;
    static int RANDON_MIN = 100000;
    
    private boolean isValid(int number) {
        String num = String.valueOf(number);
        Matcher matcher = CONSECUTIVE_NUM.matcher(num);
        return !matcher.find();
    }
    
    private int getRandom() {
        int num = ThreadLocalRandom.current().nextInt(RANDON_MIN, RANDON_MAX);
        while (!isValid(num)) {
            num = ThreadLocalRandom.current().nextInt(RANDON_MIN, RANDON_MAX);
        }
        return num;
    }
    
    public static void main(String[] args) {
        RandomGen generator = new RandomGen();
        System.out.println("Is Valid#" + generator.isValid(203273));
        System.out.println("New Random Num# " + generator.getRandom());
        
    }
    
    
}