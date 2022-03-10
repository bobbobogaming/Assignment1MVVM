package mediator.radiatormediator;

import util.PropertyChangeSubject;

public interface RadiatorModel extends PropertyChangeSubject
{
  void turnUp();
  void turnDown();
  int getPower();
}
