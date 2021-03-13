package servlet;

import service.RatingService;
import service.impl.RatingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/statistic")
public class StatisticServlet extends HttpServlet {
    private final RatingService ratingService;

    public StatisticServlet() {
        ratingService = new RatingServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("statistic", ratingService.getStatisticForAllSubjects());
            req.getRequestDispatcher("statistic.jsp").forward(req, resp);
        } catch (SQLException e) {
//            e.printStackTrace();
            req.setAttribute("errorType", "SQLException");
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            req.setAttribute("errorType", "ClassNotFoundException");
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } catch (NullPointerException npe) {
//            e.printStackTrace();
            req.setAttribute("errorType", "NullPointer Exception");
            req.setAttribute("errorMessage", npe.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
