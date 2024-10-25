package webclient.beans;

import webclient.beans.Service.CDService;
import webclient.entites.CD;
import webclient.entites.Emprunt;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserController extends HttpServlet {

    @Inject
    private CDService cdService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) action = "";

        switch (action) {
            case "/list":
                List<CD> availableCDs = cdService.listAvailableCDs();
                request.setAttribute("availableCDs", availableCDs);
                request.getRequestDispatcher("/WEB-INF/views/listCDs.jsp").forward(request, response);
                break;
            case "/borrowed":
                Long userId = (Long) request.getSession().getAttribute("userId");
                List<Emprunt> userEmprunts = cdService.getUserEmprunts(userId);
                request.setAttribute("userEmprunts", userEmprunts);
                request.getRequestDispatcher("/WEB-INF/views/userBorrowedCDs.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/user/list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (action == null) action = "";

        switch (action) {
            case "/borrow":
                Long cdId = Long.valueOf(request.getParameter("cdId"));
                cdService.borrowCD(cdId, userId);
                response.sendRedirect(request.getContextPath() + "/user/list");
                break;
            case "/return":
                cdId = Long.valueOf(request.getParameter("cdId"));
                cdService.returnCD(cdId);
                response.sendRedirect(request.getContextPath() + "/user/borrowed");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/user/list");
                break;
        }
    }
}
