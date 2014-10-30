package sample.prob;

import java.util.Arrays;

/**
 *
 * @author Dhval
 */
public class Judge {

    Float[] ratings;
    Float difficulty;

    public Judge(Float[] ratings, Float difficulty) {
        this.ratings = ratings;
        this.difficulty = difficulty;
    }

    public Float[] getRatings() {
        return ratings;
    }

    public Float getDifficulty() {
        return difficulty;
    }

    public Float findScore() {
        Float score = 0f;
        // find ratings, excluding minimum and maximum
        Arrays.sort(ratings);
        for (int i = 1; i < ratings.length - 1; i++) {
            score += ratings[i] * difficulty;
        }
        return score;
    }
}
