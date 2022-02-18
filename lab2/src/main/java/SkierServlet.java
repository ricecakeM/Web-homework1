import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "SkierServlet", value = "/SkierServlet")
public class SkierServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/plain");
        String urlPath = req.getPathInfo();
        res.getWriter().write(urlPath);

        // check we have a URL!
        if (urlPath == null || urlPath.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.getWriter().write("missing parameters");
            return;
        }
//        res.getWriter().write(urlPath);
        String[] urlParts = urlPath.split("/");
        // and now validate url path and return the response status code
        if (!isUrlValid(urlParts)) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.getWriter().write("It does not work! " + Arrays.toString(urlParts));
        } else {
            res.setStatus(HttpServletResponse.SC_OK);
            res.getWriter().write("It works!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String urlPath = request.getPathInfo();
        Gson gson = new Gson();
        // check we have a URL
        if (urlPath == null || urlPath.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            Message responseMsg = new Message("URL not found");
            out.write(gson.toJson(responseMsg));
            return;
        }

        String[] urlParts = urlPath.split("/");
        // and now validate url path and return the response status code
        if (!isUrlValid(urlParts)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            Message responseMsg = new Message("Invalid URL");
            out.write(gson.toJson(responseMsg));
        } else {
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
        out.flush();
    }

    private boolean isUrlValid(String[] urlParts) {
        // validate the post request url path according to the API spec
        // urlPath= "/skiers/{resortID}/seasons/{seasonID}/days/{dayID}/skiers/{skierID}"
        // urlParts = [, resorts, 1, seasons, 1, days, 1, skiers, 1]
        if (urlParts.length == 8) {
            for (int i = 1; i < urlParts.length; i += 2){
                try {
                    Integer.parseInt(urlParts[i]);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return urlParts[2].equals("seasons")
                    && urlParts[4].equals("days")
                    && urlParts[6].equals("skiers")
                    && Integer.parseInt(urlParts[7]) <= 100000;
        }
        return false;
    }
}
