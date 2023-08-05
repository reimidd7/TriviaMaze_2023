package view;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEnabledTriviaMazeControls extends TriviaMazeControls {
    String PROPERTY_LOCATION_CHANGE = " THIS IS FOR A PLAYER CHANGING LOCATIONS";

    String PROPERTY_DOOR_CHANGE = " THIS IS FOR WHEN A PLAYER SELECTS A DOOR";

    String PROPERTY_UNLOCK_CHANGE = " THIS IS FOR WHEN A PLAYER UNLOCKS A DOOR";

    String PROPERTY_LOCKED_CHANGE = " THIS IS FOR WHEN A DOOR PERMANENTLY LOCKS";

    public void addPropertyChangeListener(PropertyChangeListener theListener);

    public void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    public void removePropertyChangeListener(PropertyChangeListener theListener);

    public void removePropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);
}
