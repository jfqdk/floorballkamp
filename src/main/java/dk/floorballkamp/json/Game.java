package dk.floorballkamp.json;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

import java.util.function.Supplier;

public class Game implements Supplier<JsonObjectBuilder> {

    private int homeScore;

    private int awayScore;

    private Team homeTeam;

    private Team awayTeam;

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public JsonObjectBuilder get() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("homeScore", homeScore);
        builder.add("awayScore", awayScore);
        builder.add("homeTeam", homeTeam.get());
        builder.add("awayTeam", awayTeam.get());
        return builder;
    }

    public void addScoreToHomeTeam() {
        homeScore++;
    }

    public void addScoreToAwayTeam() {
        awayScore++;
    }
}
