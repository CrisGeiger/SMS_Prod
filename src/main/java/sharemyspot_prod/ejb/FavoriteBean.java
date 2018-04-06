/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharemyspot_prod.ejb;

import java.util.List;
import javax.ejb.Stateless;
import sharemyspot_prod.jpa.Favorite;
import sharemyspot_prod.jpa.Spot;
import sharemyspot_prod.jpa.User;

/**
 *
 * @author cgeiger1
 * Javaklasse um Änderungen an Favoiten vorzunehmen
 */
@Stateless
public class FavoriteBean extends EntityBean<Favorite, Long> {
    public FavoriteBean() {
        super(Favorite.class);
    }
    
    public List<Favorite> getFavorites(String username) {
        return em.createQuery("SELECT f FROM Favorite f WHERE f.user.username = :username")
                .setParameter("username", username)
                .getResultList();
    }
    
//hier fehlt noch eine Methode, in der ein Favoriten-Objekt vom Benutzer angelegt wird
    public Favorite addToFavorit(User user, Spot spot) {
        Favorite fav = new Favorite(spot, user);
        return fav;
    }
    
}
