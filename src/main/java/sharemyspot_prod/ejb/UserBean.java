/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharemyspot_prod.ejb;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sharemyspot_prod.jpa.User;

/**
 *
 * @author Alexander Becker: 10.3 - 11.3 Bean erstellt mit allen vorhandenen Methoden außer changePassword,weil ingesamt eine Synchronisation der aktuellen Klasse mit dieser Methode nicht mit GitHub geklappt hat. 
 * @editor Bastian Schabbach: 11.3 wegen technischen Problemen bei Alexander B. füge Bastian 
 * die Methode changePassword hinzu und löschte aus kommentierte Methoden  ,
 * Cristian Geiger: 12.3 extends wurde von Cristian ergänzt und die Klasse darauf hin angepasst 
 * edited Bastian Schabbach 19.03 Methoden findUser ergänzt
 * edited Schabbach /21.03.2018/ Registration methode an Entityklasse angepasst
 * edited Becker: 1.04.2018 Deutsche Bezeichnungen auf englische Begriffe abgeändert.
 * edited Becker: 05.04.18 In der @RolesAllowed Notationen der berechtigte Gruppenname an die veränderte Server-Configuration von Groß zu Kleinschreibung angepasst. Dies wurde auch in den anderen Beans durchgeführt.
 * edited Geiger: 04.04.18 Entfernung der Vererbung der EntityBean und damit auch des Konstruktors für das Abfangen des Elternkonstruktors. 
 * Da die notwendigen Methoden z.B. delete in der Klasse überschrieben wurden und man auf die find-Methode der EntityBean über den Import und dessen Objekt drauf zu greifen kann. 
 */
/**
    * Die Userbean bietet verschiedene Methoden, um als Benutzer am Benutzerprofil zu bearbeiten 
*/
  
@Stateless
public class UserBean{  
            
    @PersistenceContext 
    EntityManager em;
     
    @Resource
    EJBContext ctx;
    
    @RolesAllowed("sharemyspot-app-user")
    public void delete(User user){ // Methode zum löschen eines Benutzers
       this.em.remove(user);
    }
    
    @RolesAllowed("sharemyspot-app-user")
    public User update(User user){ // Methode zum Aktualisieren eines Benutzersprofils
       return em.merge(user);
    }
     
     /**
      * Die registration-Methode wird für die Registrierung des Benutzers mit dessen Personaldaten genutzt.
      * In der Methode wird zuerst geprüft, ob der Benutzer schon existiert, wenn ja, wird eine Benachrichtigung(SignupException)
      * dem Anwender angezeigt. Wenn der Benutzername noch nicht im System vorhanden ist,
      * werden die folgenden Parameter an den User-Konstruktor weitergegeben. 
      * Dadurch wird ein neuer User erstellt.
      * 
      * @param username
      * @param password
      * @param password2
      * @param lastName
      * @param firstName
      * @param place
      * @param plz
      * @param road
      * @param roadNumber
      * @param phoneNumber
      * @param email
      * @throws sharemyspot.ejb.UserBean.UsernameException
      * @throws sharemyspot.ejb.UserBean.PasswordException
    
      */
     public void registration(String username,String password,String password2,String lastName,String firstName,String place,String plz,String road, String roadNumber,String phoneNumber,String email)
             throws UsernameException, PasswordException{
         
         if(em.find(User.class,username)!= null){
             throw new UsernameException("Benutzer ist bereits vergeben.".replace("$B", username));
         }
         if(!password.equals(password2)) {
             throw new PasswordException("Die Passwörter stimmen nicht überein.");
         }
         
         User user= new User(username, password, lastName, firstName, place, plz, road, roadNumber, phoneNumber, email);
         user.addToGroup("sharemyspot-app-user");
         em.persist(user);
     }

     
     public User getCurrentUser(){
         return this.em.find(User.class, this.ctx.getCallerPrincipal().getName());
     }
     
     public User findUser(String user){
         return (User)em.createQuery("SELECT u FROM User u WHERE u.username = :user")
                 .setParameter("user", user)
                 .getResultList();
     }
     
     /**
     * Password ändern, wenn man eingeloggt ist
     * @param user
     * @param oldPassword
     * @param newPassword
     * @throws sharemyspot_prod.ejb.UserBean.InvalidCredentialsException
     */
     @RolesAllowed("sharemyspot-app-user")
    public void changePassword(User user, String oldPassword, String newPassword) throws InvalidCredentialsException {
        if (user == null || !user.checkPassword(oldPassword)) {
            throw new InvalidCredentialsException("Benutzername oder Passwort sind falsch.");
        }

        user.setPassword(newPassword);
    }
    
     /**
      * Password ändern, wenn man noch nicht eingeloggt ist.
      * @param username
      * @param email 
      */
     
     /**
      * selbstgeschriebene SignupException erbt von Exception und 
      * dient als Ausnahme für die Fehlermeldung beim Registrieren 
      */
     public class UsernameException extends Exception{
     
         public UsernameException(String message){
             super(message);
         }
     }
     
     public class PasswordException extends Exception{
         
         public PasswordException(String message){
             super(message);
         }
     }
     
     public class InvalidCredentialsException extends Exception {

        public InvalidCredentialsException(String message) {
            super(message);
        }
    }
}
