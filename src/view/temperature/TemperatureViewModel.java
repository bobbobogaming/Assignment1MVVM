package view.temperature;


import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.temperatoremediator.TemperatureModel;
import model.temperature.Temperature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements PropertyChangeListener
{
  private DoubleProperty[] t1Temperatures;
  private DoubleProperty[] t2Temperatures;
  private StringProperty t0;
  private StringProperty t1Warning;
  private StringProperty t2Warning;
  private TemperatureModel model;

  public void defineMinMax(int min, int max){
    model.defineMinMax(min, max);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (evt.getPropertyName().equalsIgnoreCase("new temp")){
        if (((Temperature)evt.getNewValue()).getId().equals("t1")){
          Temperature temp = model.getLastTemperature("t1");
          for (int i = 0; i < t1Temperatures.length-1; i++)
          {
            t1Temperatures[i].setValue(t1Temperatures[i+1].getValue());
          }
          t1Temperatures[t1Temperatures.length-1].setValue(temp.getTemp());
        } else if (((Temperature)evt.getNewValue()).getId().equals("t2")){
          Temperature temp2 = model.getLastTemperature("t2");
          for (int j = 0; j < t2Temperatures.length-1; j++)
          {
            t2Temperatures[j].setValue(t2Temperatures[j+1].getValue());
          }
          t2Temperatures[t2Temperatures.length-1].setValue(temp2.getTemp());
        } else if (((Temperature)evt.getNewValue()).getId().equals("t0"))
          t0.setValue(String.format("t0: %5.5f",model.getLastTemperature("t0").getTemp()));
      }

      if (evt.getPropertyName().equalsIgnoreCase("max")){
        if (((Temperature)evt.getNewValue()).getId().equals("t1")){
          t1Warning.setValue("Warning: is over Max");
        }else if (((Temperature)evt.getNewValue()).getId().equals("t2")){
          t2Warning.setValue("Warning: is over Max");
        }
      } else if (evt.getPropertyName().equalsIgnoreCase("min")){
        if (((Temperature)evt.getNewValue()).getId().equals("t1")){
          t1Warning.setValue("Warning: is below Min");
        }else if (((Temperature)evt.getNewValue()).getId().equals("t2")){
          t2Warning.setValue("Warning: is below Min");
        }
      } else if (evt.getPropertyName().equalsIgnoreCase("normalize")){
        if (((Temperature)evt.getNewValue()).getId().equals("t1")){
          t1Warning.setValue("Is nominal");
        }else if (((Temperature)evt.getNewValue()).getId().equals("t2")){
          t2Warning.setValue("Is nominal");
        }
      }
    });
  }

  public TemperatureViewModel(TemperatureModel model)
  {
    this.model = model;
    t1Temperatures = new DoubleProperty[10];
    for (int i = 0; i < t1Temperatures.length; i++)
    {
      t1Temperatures[i] = new SimpleDoubleProperty();
    }

    t2Temperatures = new DoubleProperty[10];
    for (int j = 0; j < t2Temperatures.length; j++)
    {
      t2Temperatures[j] = new SimpleDoubleProperty();
    }

    t0 = new SimpleStringProperty();

    t1Warning = new SimpleStringProperty();
    t2Warning = new SimpleStringProperty();

    model.addPropertyChangeListener(this);
  }

  public DoubleProperty[] t1TemperatureProperties()
  {
    return t1Temperatures;
  }

  public DoubleProperty[] t2TemperatureProperties()
  {
    return t2Temperatures;
  }

  public StringProperty t0Property()
  {
    return t0;
  }

  public StringProperty t1WarningProperty()
  {
    return t1Warning;
  }

  public StringProperty t2WarningProperty()
  {
    return t2Warning;
  }
}
