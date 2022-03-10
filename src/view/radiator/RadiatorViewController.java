package view.radiator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class RadiatorViewController
{
  @FXML Label powerState;
  @FXML ProgressBar powerStateBar;

  private RadiatorViewModel viewModel;

  public void init(RadiatorViewModel radiatorViewModel)
  {
    viewModel = radiatorViewModel;

    powerState.setText("0");

    powerStateBar.progressProperty().bind(viewModel.doubleProperty());
    powerState.textProperty().bindBidirectional(viewModel.stringProperty());
  }

  public void onTurnUpButton(ActionEvent actionEvent)
  {
    viewModel.turnUp();
  }

  public void onTurnDownButton(ActionEvent actionEvent)
  {
    viewModel.turnDown();
  }
}
