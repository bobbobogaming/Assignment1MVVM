package mediator.radiatormediator;

import model.radiator.Radiator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RadiatorModelManagerRedacted implements RadiatorModel // redacted
{
  private Radiator radiator;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public RadiatorModelManagerRedacted()
  {
    radiator = new Radiator();
  }

  @Override public void turnUp()
  {
    radiator.turnUp();
    if (radiator.getPower()<3)
      support.firePropertyChange("Turn up",radiator.getPower()-1,radiator.getPower());
  }

  @Override public void turnDown()
  {
    radiator.turnDown();
    if (radiator.getPower()>0)
      support.firePropertyChange("Turn down",radiator.getPower()+1,radiator.getPower());
  }

  @Override public int getPower()
  {
    return radiator.getPower();
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }
}
