package view.radiator;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.radiatormediator.RadiatorModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RadiatorViewModel implements PropertyChangeListener
{
  private StringProperty stringProperty;
  private DoubleProperty doubleProperty;
  private RadiatorModel radiatorModel;

  public RadiatorViewModel(RadiatorModel radiatorModel)
  {
    this.radiatorModel = radiatorModel;
    stringProperty = new SimpleStringProperty("0");
    doubleProperty = new SimpleDoubleProperty(0);
    radiatorModel.addPropertyChangeListener(this);
  }

  public void turnUp(){
    radiatorModel.turnUp();
  }

  public void turnDown(){
    radiatorModel.turnDown();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()->{
      int state = (int) evt.getNewValue();

      stringProperty.setValue(state + "");

      if (state == 0)
        doubleProperty.setValue(0);
      else if (state == 1)
        doubleProperty.setValue(0.33);
      else if (state == 2)
        doubleProperty.setValue(0.66);
      else if (state == 3)
        doubleProperty.setValue(1);
    });
  }

  public DoubleProperty doubleProperty()
  {
    return doubleProperty;
  }

  public StringProperty stringProperty()
  {
    return stringProperty;
  }
}
