package dk.floorballkamp.ws;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Logger;

@WebServlet("/echo/*")
public class EchoServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EchoServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        LOG.info(String.valueOf(httpServletRequest.getRequestURL()));
        httpServletResponse.setContentType("text/plain; charset=utf-8");
        PrintWriter echo = new PrintWriter(httpServletResponse.getOutputStream(), true, StandardCharsets.UTF_8);
        echo.print(httpServletRequest.getRequestURL());
        echo.println(httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "");
        echo.println();
        echo.println(httpServletRequest.getRequestURI());
        echo.println();
        echo.println(httpServletRequest.getContextPath());
        echo.println(httpServletRequest.getServletPath());
        echo.println(httpServletRequest.getPathInfo());
        httpServletRequest.getParameterMap().forEach((key, values) -> Arrays.stream(values).forEach(value -> echo.println(key + "=" + value)));
    }
}
