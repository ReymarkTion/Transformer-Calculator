package core_1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Main.Main;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class core1_controller implements Initializable {

	private ObservableList<String> unitList = FXCollections
			.observableArrayList("centimeter", "meter", "millimeter", "inches");
	
	@FXML private ImageView fig_1;
	@FXML ComboBox<String> unit_box;
	
	@FXML CheckBox flux_check;
	@FXML TextField flux_field;
	
	//@FXML CheckBox coreLength_check;
	//@FXML TextField coreLength_field;
	
	@FXML CheckBox current_check;
	@FXML TextField current_field;
	
	//@FXML CheckBox depth_check;
	@FXML TextField depth_field;
	
	//@FXML CheckBox cs_area_check;
	//@FXML TextField cs_area_field;
	
	@FXML TextField num_turn_field;
	@FXML TextField permeability_field;
	
	@FXML JFXButton calc_button;
	@FXML JFXButton mainV_button;
	
	//  length
	@FXML TextField l_1;
	@FXML TextField l_2;
	@FXML TextField l_3;
	@FXML TextField l_4;
	@FXML TextField l_5;
	@FXML TextField l_6;
	
	private String unit;
	public static String __unit = "m";
	
	@FXML BorderPane core_layout;
	@FXML BorderPane calc_layout;
	
	//---------------------
	@FXML VBox menu_pane;
	@FXML HBox menu_bl;
	@FXML AnchorPane menu_a;
	@FXML SplitPane sp;
	//---------------------
	
	@FXML JFXButton hide_menu;
	//private boolean hide_y_n = false;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fig_1.setImage(new Image("/figures/figure 1.png"));
		unit_box.setItems(unitList);
		buttonActions();
		check_Actions();
		
		Main.getMainWindow().maximizedProperty().addListener( e -> {
			if(Main.getMainWindow().isMaximized()) {
				swipeTransition(calc_layout, 250);
			} else {
				swipeTransition(calc_layout, 0);
			}
		});
		
		Main.getMainLayout().setRight(null);
	}
	
	
	
	private void buttonActions() {
		
		calc_button.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if(addData()) {
				move_core_right();
				BorderPane layout = null;
				try { layout = Main.getCalculation_1_Layout();
				} catch (IOException e1) {	e1.printStackTrace();	}
				calc_layout.setCenter(layout);
				fadeTrans(calc_layout);
				if(Main.getMainWindow().isMaximized()) {
					swipeTransition(calc_layout, 250);
				} else {
					swipeTransition(calc_layout, 0);
				}
			}
		});
		
		mainV_button.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			//fadeTrans(calc_layout);
			move_core_left();
		});
		
		
		hide_menu.setOnAction(e -> {
			/*if(hide_y_n == false) {
				System.out.println("Hello");
				menu_pane.setPrefWidth(-100);
				menu_bl.setPrefWidth(-100);
				menu_a.setPrefWidth(-100);
				hide_y_n = true;
			/*} else {
				menu_pane.setPrefWidth(150);
				menu_bl.setPrefWidth(150);
				menu_a.setPrefWidth(150);
				hide_y_n = false;
			}*/
		});
		

	}
	
	private void move_core_left() {
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.millis(500));
		pt.setOnFinished(e -> {
			swipeTransition(core_layout, 0);
		});
		pt.play();
	}
	
	private void move_core_right() {
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.millis(500));
		pt.setOnFinished(e -> {
			swipeTransition(core_layout, 1300);
		});
		pt.play();
	}
	
	private void check_Actions() {
		
		flux_check.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if(flux_check.isSelected()) {  	flux_field.setDisable( false);
			} else {  						flux_field.setDisable( true );  }
		});
		
		/*depth_check.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if(depth_check.isSelected()) {  	depth_field.setDisable( false);
			} else {  						depth_field.setDisable( true );  }
		}); */
		
		current_check.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if(current_check.isSelected()) {  	current_field.setDisable( false);
			} else {  						current_field.setDisable( true );  }
		});
		
		/*cs_area_check.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if(cs_area_check.isSelected()) {  	
				cs_area_field.setDisable( false);
			} else {  						
				cs_area_field.setDisable( true );
			}
		}); */
		
		/*coreLength_check.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if(coreLength_check.isSelected()) {  	
				coreLength_field.setDisable( false);
				l_1.setDisable(true);
				l_2.setDisable(true);
				l_3.setDisable(true);
				l_4.setDisable(true);
				l_5.setDisable(true);
				l_6.setDisable(true);
			} else {  
				coreLength_field.setDisable( true );
				l_1.setDisable(false);
				l_2.setDisable(false);
				l_3.setDisable(false);
				l_4.setDisable(false);
				l_5.setDisable(false);
				l_6.setDisable(false);
			}
		}); */
		
	}
	
	private boolean addData() {
		Main.newCoreData();
		unit = unit_box.getValue();
		
		try {
			
			Main.getCore_data().insert(Float.parseFloat(permeability_field.getText()), "permeability");
			Main.getCore_data().insert(Float.parseFloat(num_turn_field.getText()), "number of turns");	
			Main.getCore_data().insert(convert(depth_field.getText()), "depth"); // nf
			
			//if(!coreLength_check.isSelected()) {
			Main.getCore_data().insert(convert(l_1.getText()), "l1");
			Main.getCore_data().insert(convert(l_2.getText()), "l2");
			Main.getCore_data().insert(convert(l_3.getText()), "l3");
			Main.getCore_data().insert(convert(l_4.getText()), "l4");
			Main.getCore_data().insert(convert(l_5.getText()), "l5");
			Main.getCore_data().insert(convert(l_6.getText()), "l6");
			//}
			
			
			if(flux_check.isSelected()) {
				Main.getCore_data().insert(Float.parseFloat(flux_field.getText()), "flux");
			}
			
			/*if(coreLength_check.isSelected()) {
				Main.getCore1_data().insert(convert(coreLength_field.getText()), "core length");
			}*/
			
			if(current_check.isSelected()) {
				Main.getCore_data().insert(Float.parseFloat(current_field.getText()), "current");
			}
			
			/*if(depth_check.isSelected()) {
				Main.getCore1_data().insert(convert(depth_field.getText()), "depth");
			} */
			
			/*if(cs_area_check.isSelected()) {
				
				Main.getCore1_data().insert(convert(cs_area_field.getText()), "cs area");
			}*/
			//System.out.println("Hello");
		} catch(Exception e) { 
			alert();
			System.out.println("Error!");
			return false;
		}
		// Main.Main.getCore1_data().display();
		// System.out.println("data: "+Main.Main.getCore1_data().getData("core length"));
		return true;
	}
	
	private float convert(String value) {
		try {
			switch(unit) {
			case "centimeter":
				__unit = "cm";
				return Float.parseFloat(value)/100;
			case "meter":
				__unit = "m";
				return Float.parseFloat(value);
			case "millimeter":
				__unit = "mm";
				return (float) (Float.parseFloat(value) * 0.001);
			case "inches":
				__unit = "in";
				return (float) (Float.parseFloat(value)/39.3701);
			default:
				return 0;
			}
		} catch(Exception e) {	return Float.parseFloat(value);	}
	}
	
	private void alert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Error");
		alert.setHeaderText("Not Enough Data");
		alert.setContentText("Please fill up everything required!");
		alert.showAndWait();
	}
	
	public void swipeTransition(Node node, int pos) {
		TranslateTransition swipeTransition = new TranslateTransition();
		swipeTransition.setNode(node);
		swipeTransition.setDuration(Duration.millis(300));
		swipeTransition.setToX(pos);
		node.setOpacity(1.0);
		swipeTransition.play();
	}
	
	private static void fadeTrans(Node e) {
		FadeTransition x = new FadeTransition(new Duration(2000), e);
		x.setFromValue(0);
		x.setToValue(100);
		x.setCycleCount(1);
		x.setInterpolator(Interpolator.LINEAR);
		x.play();
	}
	
	/*private DropShadow getDropShadow(int i) {
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(10.0);
		dropShadow.setOffsetX(i);
		dropShadow.setOffsetY(i);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
		return dropShadow;
	} */
}




/*

Stage ntn = new Stage();
ntn.setTitle("Result");
ntn.initStyle(StageStyle.UTILITY);
ntn.initModality(Modality.APPLICATION_MODAL);
ntn.initOwner(Main.getMainWindow());
BorderPane layout = null;
try { layout = Main.getCalculation_1_Layout();
} catch (IOException e1) {	e1.printStackTrace();	}
Scene scene = new Scene(layout);
ntn.setScene(scene);
ntn.show(); */




