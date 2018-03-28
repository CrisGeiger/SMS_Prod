/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import sharemyspot_prod.ejb.FavoriteBean;
import sharemyspot_prod.ejb.UserBean;
import sharemyspot_prod.jpa.Favorite;
import sharemyspot_prod.jpa.Spot;
import sharemyspot_prod.jpa.User;

/**
 *
 * @author Budda
 */
@WebServlet(name = "FavoriteListServlet", urlPatterns = {"/Favorites"})
public class FavoriteListServlet extends HttpServlet{
    
    @EJB
    FavoriteBean favoriteBean;
    
    @EJB
    UserBean userBean;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        User user = this.userBean.getCurrentUser();
        List<Spot> spots = null;
        
        List<Favorite> favorites = this.favoriteBean.getFavorits(user.getUsername());
        for(Favorite s :favorites){
            spots.add(s.getSpot());
        }
        request.setAttribute("favoritelist",spots);
        
        request.getRequestDispatcher("/WEB-INF/app/Favorites.jsp").forward(request, response);
    }
}
