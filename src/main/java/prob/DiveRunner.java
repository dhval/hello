/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.prob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Dhval
 */
public class DiveRunner {

    private static final int DIVE_COUNT = 3;
    private static final String INPUT_FILE = "divers.txt";
    private static final String FILE_DELIMITER_STRING = " ";

    public static void displayResult(List<Diver> divers) {
        // sort divers based on their score.
        Collections.sort(divers);
        for (Diver diver : divers) {
            System.out.println(diver);
        }
    }

    public static Map<String, Diver> readData() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(INPUT_FILE)));
        String line = br.readLine();
        Integer diverCount = Integer.parseInt(line);
        int count = 0;
        Map<String, Diver> records = new HashMap<String, Diver>();
        while ((line = br.readLine()) != null && count++ < (diverCount * DIVE_COUNT)) {
            String[] columns = line.split(FILE_DELIMITER_STRING);
            if (columns.length != 11) {
                System.out.println("Invalid Row, each row must have 11 inputs.");
                continue;
            }
            String driver = columns[0];
            Float difficulty = Float.parseFloat(columns[1]);
            Float ratings[] = new Float[9];
            for (int col = 2; col < columns.length; col++) {
                ratings[col - 2] = Float.parseFloat(columns[col]);
            }
            Judge judge = new Judge(ratings, difficulty);
            if (records.containsKey(driver)) {
                Diver diver = records.get(driver);
                diver.addJudge(judge);
            } else {
                List<Judge> judges = new ArrayList<Judge>();
                judges.add(judge);
                Diver diver = new Diver(driver, judges);
                records.put(driver, diver);
            }
        }
        if (line != null || count != (diverCount * DIVE_COUNT)) {
            System.out.println("Premature termination, make sure driver count is three times dives.");
        }
        return records;
    }

    public static void main(String[] s) throws Exception {
        Map<String, Diver> records = readData();
        displayResult(new ArrayList<Diver>(records.values()));
    }

}
