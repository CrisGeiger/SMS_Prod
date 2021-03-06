/*
 * Zu Risiken und Nebenwirkungen lesen Sie die Packunsgbeilage und fragen Sie ihren Arzt oder Apotheker
 */
package sharemyspot_prod.web;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sharemyspot_prod.ejb.SpotBean;
import sharemyspot_prod.ejb.UserBean;
import sharemyspot_prod.jpa.Spot;
import sharemyspot_prod.jpa.SpotStatus;

/**
 *Servlet-Klasse zum anzeigen von eigens angelegten Parkplätzen
 * @author cgeiger1
 */
@WebServlet(urlPatterns = {"/app/meinespots"})
public class UserSpotServlet extends HttpServlet {
    
    @EJB
    private SpotBean sb;
    
    @EJB
    private UserBean ub;
    
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        List<Spot> ownSpots = sb.findByUsername(this.ub.getCurrentUser().getUsername());
        request.setAttribute("ownSpots", ownSpots);
        
        request.getRequestDispatcher("/WEB-INF/app/userspot_list.jsp").forward(request, response);
    }
}
