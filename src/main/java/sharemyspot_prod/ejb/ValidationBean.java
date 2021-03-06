/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharemyspot_prod.ejb;

/**
 *
 * @author cgeiger1
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 * Kleine EJB, die dafür genutzt werden kann, die Werte einer Entitiy zu
 * validieren, bevor diese gespeichert wird. Zwar validiert der Entity Manager
 * die Bean beim Speichern ebenfalls, da das aber erst am Ende der Transaktion
 * erfolgt, ist es schwer, rechtzeitig darauf zu reagieren.
 */
@Stateless
public class ValidationBean {
    
    @Resource
    Validator validator;
    
    /**
     * Wertet die "Bean Validation" Annotationen des übergebenen Objekts aus
     * (@Min, @Max, @Size, @NotNull, …) und gib eine Liste der dabei gefundenen
     * Fehler zurück.
     * 
     * @param <T>
     * @param object Zu überprüfendes Objekt
     * @return Liste mit Fehlermeldungen
     */
    public <T> List<String> validate(T object) {
        List<String> messages = new ArrayList<>();
        return this.validate(object, messages);
    }
    
    /**
     * Wertet die "Bean Validation" Annotationes des übergebenen Objekts aus
     * und stellt die gefundenen Meldungen in messages.
     * 
     * @param <T>
     * @param object Zu überprüfendes Objekt
     * @param messages List mit Fehlermeldungen
     * @return Selbe Liste wie messages
     */
    public <T> List<String> validate(T object, List<String> messages) {
        Set<ConstraintViolation<T>> violations = this.validator.validate(object);
        
        for (ConstraintViolation v: violations) {
            messages.add(v.getMessage());
        }
               
        return messages;
    }
}