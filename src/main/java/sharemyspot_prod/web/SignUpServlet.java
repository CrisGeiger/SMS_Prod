/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharemyspot_prod.web;

/**
 *
 * @author Schabbach
 * Änderung 15.03.18: Becker: Bezeichnungen von ort,anschrift,
 * telefon,vorname,nachname zu englischen Begriffen umgewandelt 
 * edited Schabbach /21.03.2018/ Konstruktoren und Registration an Entity und Bean-Klasse angepasst
 */

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sharemyspot_prod.ejb.UserBean;
import sharemyspot_prod.ejb.ValidationBean;
import sharemyspot_prod.jpa.User;

/**
 * Servlet für die Registrierungsseite. Hier kann sich ein neuer Benutzer
 * registrieren. Anschließend wird der auf die Startseite weitergeleitet.
 */
@WebServlet(urlPatterns = {"/signup/"})
public class SignUpServlet extends HttpServlet {
    
    @EJB
    ValidationBean validationBean;
            
    @EJB
    UserBean userBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login/signup.jsp");
        dispatcher.forward(request, response);
        
        // Alte Formulardaten aus der Session entfernen
        HttpSession session = request.getSession();
        session.removeAttribute("signup_form");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Formulareingaben auslesen
        request.setCharacterEncoding("utf-8");
        
        String username = request.getParameter("signup_username");
        String password1 = request.getParameter("signup_password1");
        String password2 = request.getParameter("signup_password2");
        String lastName = request.getParameter("signup_lastname");
        String firstName = request.getParameter("signup_firstname");
        String road = request.getParameter("signup_road");
        String roadnumber = request.getParameter("signup_roadnumber");
        String plz = request.getParameter("signup_plz");
        String place = request.getParameter("signup_place");
        String phoneNumber = request.getParameter("signup_phonenumber");
        String email = request.getParameter("signup_email");
        
        
        // Eingaben prüfen
        User user = new User(username, password1, lastName, firstName, place, plz, road, roadnumber, phoneNumber, email);
        List<String> errors = this.validationBean.validate(user);
        this.validationBean.validate(user.getPassword(), errors);
        
        if (password1 != null && password2 != null && !password1.equals(password2)) {
            errors.add("Die beiden Passwörter stimmen nicht überein.");
        }
        
        // Neuen Benutzer anlegen
        if (errors.isEmpty()) {
            try {
                this.userBean.registration(username, password1, password2, lastName, firstName, place, plz, road, roadnumber, phoneNumber, email);
            } catch (UserBean.UsernameException ex) {
                errors.add(ex.getMessage());
            }
            catch (UserBean.PasswordException ex) {
                errors.add(ex.getMessage());
            }
        }
        
        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            request.login(username, password1);
            response.sendRedirect(WebUtils.appUrl(request, "/app/spots"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);
            
            HttpSession session = request.getSession();
            session.setAttribute("signup_form", formValues);
            
            response.sendRedirect(request.getRequestURI());
        }
    }
    
}