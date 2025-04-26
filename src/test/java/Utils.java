import org.junit.Test;

import java.util.stream.IntStream;

public class Utils {

    @Test
    public void minut() {
        System.out.println("<div class=\"row\">");
        buttons(0, 20, "time_minute_", false);
        System.out.println("</div>");
    }

    @Test
    public void sekund() {
        System.out.println("<div class=\"row\">");
        buttons(0, 60, "time_second_", true);
        System.out.println("</div>");
    }

    private static void buttons(int from, int to, String id, boolean close) {
        IntStream.range(from, to)
                .boxed()
                .map(i -> "\t<div class=\"col s3 m2 l1\"><a id=\"" + id + String.format("%02d", i) + "\" data-time=\"" + i + "\" href=\"#\" class=\"btn-floating btn-large purple darken-2"+(close ? " modal-close":"")+"\">" + String.format("%02d", i) + "</a></div>")
                .forEach(System.out::println);
    }

}
