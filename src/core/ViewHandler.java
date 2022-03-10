package core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.main.TabViewController;
import view.radiator.RadiatorViewModel;

import java.io.IOException;

public class ViewHandler
{
  private Scene currentScene;
  private Stage currentStage;
  private ViewModelFactory viewModelFactory;
  private RadiatorViewModel radiatorViewModel;

  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    currentStage = stage;
    this.viewModelFactory = viewModelFactory;

  }

  public void start() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/main/TabsView.fxml"));
    Parent root = loader.load();
    TabViewController tabViewController = loader.getController();
    tabViewController.init(viewModelFactory.getTemperatureViewModel(),viewModelFactory.getRadiatorViewModel());
    currentStage.setTitle("Tab view");

    currentScene = new Scene(root);
    currentStage.setScene(currentScene);
    currentStage.show();
  }
}
