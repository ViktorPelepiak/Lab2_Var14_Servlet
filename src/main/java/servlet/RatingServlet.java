package servlet;

import dto.RatingAddDto;
import exception.WrongDataException;
import service.GroupService;
import service.RatingService;
import service.impl.GroupServiceImpl;
import service.impl.RatingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {
    private final RatingService ratingService;
    private final GroupService groupService;

    public RatingServlet() {
        ratingService = new RatingServiceImpl();
        groupService = new GroupServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("groups", groupService.getAll());
            req.setAttribute("ratings", ratingService.getRatingsForAllStudents());
            req.getRequestDispatcher("ratingList.jsp").forward(req, resp);
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
        } catch (WrongDataException e) {
//            e.printStackTrace();
            req.setAttribute("errorType", "WrongDataException");
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } catch (NullPointerException npe) {
//            e.printStackTrace();
            req.setAttribute("errorType", "NullPointer Exception");
            req.setAttribute("errorMessage", npe.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RatingAddDto ratingAddDto = new RatingAddDto()
                    .setFirstName(req.getParameter("firstName"))
                    .setLastName(req.getParameter("lastName"))
                    .setFatherName(req.getParameter("fatherName"))
                    .setGroupId(Long.parseLong(req.getParameter("groupSelect")))
                    .setSub1(Byte.parseByte(req.getParameter("sub1")))
                    .setSub2(Byte.parseByte(req.getParameter("sub2")))
                    .setSub3(Byte.parseByte(req.getParameter("sub3")));
            ratingService.save(ratingAddDto);
            doGet(req, resp);
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
        } catch (WrongDataException e) {
//            e.printStackTrace();
            req.setAttribute("errorType", "WrongDataException");
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
