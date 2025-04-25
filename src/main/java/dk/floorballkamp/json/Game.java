package dk.floorballkamp.json;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class Game implements Supplier<JsonObjectBuilder> {

    private AtomicInteger homeScore = new AtomicInteger();

    private AtomicInteger awayScore = new AtomicInteger();

    private Team homeTeam;

    private Team awayTeam;

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public JsonObjectBuilder get() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("homeScore", homeScore.get());
        builder.add("awayScore", awayScore.get());
        builder.add("homeTeam", homeTeam.get());
        builder.add("awayTeam", awayTeam.get());
        return builder;
    }

    public void addScoreToHomeTeam() {
        homeScore.incrementAndGet();
    }

    public void addScoreToAwayTeam() {
        awayScore.incrementAndGet();
    }

    public void subtractScoreFromHomeTeam() {
        homeScore.decrementAndGet();
    }

    public void subtractScoreFromAwayTeam() {
        awayScore.decrementAndGet();
    }
}
