package model.radiator;

import mediator.radiatormediator.RadiatorModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Radiator implements RadiatorModel
{
  private RadiatorState currentState;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public Radiator()
  {
    currentState = new OffState();
  }

  public void turnUp(){
    currentState.turnUp(this);
  }

  public void turnDown(){
    currentState.turnDown(this);
  }

  public int getPower(){
    return currentState.getPower();
  }

  void setPowerState(RadiatorState state){
    int oldPower = getPower();
    currentState = state;
    support.firePropertyChange("State chance",oldPower,getPower());
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
