/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadspeedtest.Werkzeuge;

/**
 *
 * @author Steve
 */
public interface Observable {
    public void notifyObservers();
    public void addObserver(Observer o);
}
