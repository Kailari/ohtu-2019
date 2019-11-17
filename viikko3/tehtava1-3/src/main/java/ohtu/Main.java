package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        System.out.println(String.format("Players from FIN %s\n", new Date()));
        Arrays.stream(players)
              .filter(p -> p.getNationality().equalsIgnoreCase("FIN"))
              .sorted(Comparator.comparingInt(Player::getPoints).reversed())
              .forEach(Main::printPlayer);
    }

    private static void printPlayer(Player player) {
        System.out.println(String.format(
                "%-20s %s  %2d + %2d = %2d ",
                player.getName(),
                player.getTeam(),
                player.getGoals(),
                player.getAssists(),
                player.getPoints()
        ));
    }

}
