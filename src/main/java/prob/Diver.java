package sample.prob;

import java.util.List;

/**
 *
 * @author Dhval
 */
public class Diver implements Comparable<Diver> {

    private String name;
    private List<Judge> judges;

    public Diver(String name, List<Judge> judges) {
        this.name = name;
        this.judges = judges;
    }

    public void addJudge(Judge judge) {
        if (judges != null) {
            judges.add(judge);
        }
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public Float totalScore() {
        Float total = 0f;
        for (Judge judge : judges) {
            total += judge.findScore();
        }
        return total;
    }

    public int compareTo(Diver o) {
        return o.totalScore().compareTo(this.totalScore());
    }

    /**
     * Pretty print records as per requirement. 
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        for (Judge judge : judges) {
            sb.append("\t").append(judge.findScore());
        }
        sb.append("\t").append(totalScore());
        return sb.toString();
    }

}
