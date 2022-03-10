package view.main;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import view.radiator.RadiatorViewController;
import view.radiator.RadiatorViewModel;
import view.temperature.TemperatureViewController;
import view.temperature.TemperatureViewModel;

public class TabViewController
{
  @FXML private Tab tab1;
  @FXML private Tab tab2;
  @FXML private TemperatureViewController temperatureViewController;
  @FXML private RadiatorViewController radiatorViewController;
  public void init(TemperatureViewModel temperatureViewModel, RadiatorViewModel radiatorViewModel){
    temperatureViewController.init(temperatureViewModel);
    radiatorViewController.init(radiatorViewModel);
  }
}
