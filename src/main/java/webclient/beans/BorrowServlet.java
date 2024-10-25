package webclient.beans;

import webclient.beans.Service.CDService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
    @EJB
    private CDService cdService;  // Service EJB pour gérer les emprunts

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long cdId = Long.parseLong(request.getParameter("cdId"));
        Long userId = (Long) request.getSession().getAttribute("userId");


        // Appel de la méthode d'emprunt du CD
        boolean success = cdService.borrowCD(cdId,userId);

        if (success) {
            request.setAttribute("message", "CD emprunté avec succès.");
        } else {
            request.setAttribute("message", "Échec de l'emprunt du CD.");
        }

        // Redirection vers la page d'accueil ou rafraîchissement de la page
        request.getRequestDispatcher("borrowcd.jsp").forward(request, response);
    }
}
