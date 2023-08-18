package controller;

import java.beans.PropertyChangeListener;

/**
 * Contains the property change enabled property names and methods.
 *
 * @author Reilly Middlebrooks
 * @version Summer 2023
 */
public interface PropertyChangeEnabledTriviaMazeControls extends TriviaMazeControls {
    /**
     * A property name for the location.
     */
    String PROPERTY_LOCATION_CHANGE = " THIS IS FOR A PLAYER CHANGING LOCATIONS";

    /**
     * A property name for the room.
     */
    String PROPERTY_ROOM_CHANGE = " THIS IS FOR A PLAYER CHANGING ROOMS";

    /**
     * A property name for the door status.
     */
    String PROPERTY_DOOR_STATUS = " THIS IS FOR WHEN A PLAYER ANSWER CHANGES THE DOOR STATUS";

    /**
     * A property name for the questions.
     */
    String PROPERTY_NEW_QUESTION = " THIS IS FOR WHEN A PLAYER CHOOSES A NEW DOOR";


    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for
     * all properties. The same listener object may be added more than once, and will be
     * called as many times as it is added. If listener is null, no exception is thrown and
     * no action is taken.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);


    /**
     * Add a PropertyChangeListener for a specific property. The listener will be invoked only
     * when a call on firePropertyChange names that specific property. The same listener object
     * may be added more than once. For each property, the listener will be invoked the number
     * of times it was added for that property. If propertyName or listener is null, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a
     * PropertyChangeListener that was registered for all properties. If listener was added
     * more than once to the same event source, it will be notified one less time after being
     * removed. If listener is null, or was never added, no exception is thrown and no action
     * is taken.
     *
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener for a specific property. If listener was added more than
     * once to the same event source for the specified property, it will be notified one less
     * time after being removed. If propertyName is null, no exception is thrown and no action
     * is taken. If listener is null, or was never added for the specified property, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);
}

