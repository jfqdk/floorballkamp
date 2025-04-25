package dk.floorballkamp.ws;

import dk.floorballkamp.json.Game;
import dk.floorballkamp.json.Team;
import jakarta.json.Json;
import jakarta.json.JsonWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/g/*")
public class GameServlet extends HttpServlet {

    private final Game game = new Game();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String pathInfo = httpServletRequest.getPathInfo();
        if (pathInfo == null || pathInfo.trim().isEmpty()) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        httpServletResponse.setContentType("application/json");
        game.setHomeTeam(new Team("Hjemmeholdet"));
        game.setAwayTeam(new Team("Udeholdet"));
        JsonWriter writer = Json.createWriter(httpServletResponse.getOutputStream());
        writer.writeObject(game.get().build());
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String pathInfo = httpServletRequest.getPathInfo();
        if (pathInfo == null || pathInfo.trim().isEmpty()) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        switch (pathInfo) {
            case "/homeScoreAdd": {
                game.addScoreToHomeTeam();
                break;
            }
            case "/awayScoreAdd": {
                game.addScoreToAwayTeam();
                break;
            }
            case "/homeScoreSubtract": {
                game.subtractScoreFromHomeTeam();
                break;
            }
            case "/awayScoreSubtract": {
                game.subtractScoreFromAwayTeam();
                break;
            }
            default:
                httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
        }
        httpServletResponse.setContentType("application/json");
        game.setHomeTeam(new Team("Hjemmeholdet"));
        game.setAwayTeam(new Team("Udeholdet"));
        JsonWriter writer = Json.createWriter(httpServletResponse.getOutputStream());
        writer.writeObject(game.get().build());
    }
}
