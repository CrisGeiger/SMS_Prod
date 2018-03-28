/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharemyspot_prod.jpa;

/**
 *
 * @author cgeiger1
 * Einfache Enum-Klasse um die verschiedenen Status eines Spots abzubilden
 */

//überarbeitet am 14.3. von Cristian Geiger: Reserved in Booked umgeschrieben und dritte Alternative Not available hinzugefügt

public enum SpotStatus {
    FREE, BOOKED, NOT_AVAILABLE;
    
    public String getLabel() {
        switch (this) {
            case FREE:
                return "frei";
            case BOOKED:
                return "reserviert";
            case NOT_AVAILABLE:
                return "nicht verfügbar";
            default:
                return this.toString();
        }
    }
}
