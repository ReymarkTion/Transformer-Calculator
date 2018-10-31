package Main;

import java.io.IOException;

import coreData.coreData;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	private static BorderPane mainLayout, layout_1, layout_2, layout_3;
	private static StackPane main;
	private static Stage mainWindow; 
	private static coreData core;
	public static boolean anim_flag;
	
	public Main() {
		core = new coreData();
		anim_flag = false;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		mainWindow = primaryStage;
		mainWindow.setTitle("Energy Conversion");
		main = new StackPane();
		showMainLayout();
		Scene scene = new Scene(main);
		mainWindow.setScene(scene);
		mainWindow.show();
	}
	
	public static void showMainLayout() throws IOException {
		mainLayout = FXMLLoader.load(Main.class.getResource("mainLayout.fxml"));
		fadeTrans(mainLayout);
		main.getChildren().clear();
		main.getChildren().add(mainLayout);
	}
	
	//Core 1 layouts
	public static void showFigure_1_Layout() throws IOException {
		layout_1 = FXMLLoader.load(Main.class.getResource("/core_1/core1Layout.fxml"));
		mainLayout.setCenter(layout_1);
		fadeTrans(layout_1);
	}
	
	public static BorderPane getCalculation_1_Layout() throws IOException {
		return FXMLLoader.load(Main.class.getResource("/core_1/calc_layout.fxml"));
	}
	//----------------------------------------------------------------------------------
	
	//Core 2 layouts
	public static void showFigure_2_Layout() throws IOException {
		layout_2 = FXMLLoader.load(Main.class.getResource("/core_2/core2Layout.fxml"));
		mainLayout.setCenter(layout_2);
		fadeTrans(layout_2);
	}
	
	public static BorderPane getCalculation_2_Layout() throws IOException {
		return FXMLLoader.load(Main.class.getResource("/core_2/calc_layout.fxml"));
	}
	//----------------------------------------------------------------------------------
	
	//Core 3 layouts
	public static void showFigure_3_Layout() throws IOException {
		layout_3 = FXMLLoader.load(Main.class.getResource("/core_3/core3Layout.fxml"));
		mainLayout.setCenter(layout_3);
		fadeTrans(layout_3);
	}
	
	public static BorderPane getCalculation_3_Layout() throws IOException {
		return FXMLLoader.load(Main.class.getResource("/core_3/calc_layout.fxml"));
	}
	//----------------------------------------------------------------------------------
	
	public static float getM_o() {
		return (float) (4 * Math.PI * Math.pow(10, -7));
	}
	
	
	public static coreData getCore_data() {
		return core;
	}
	
	public static void newCoreData() {
		core = new coreData();
	}
	
	public static Stage getMainWindow() {
		return mainWindow;
	}
	
	public static BorderPane getMainLayout() {
		return mainLayout;
	}
	
	// fade transition
	private static void fadeTrans(Node e) {
		FadeTransition x = new FadeTransition(new Duration(500), e);
		x.setFromValue(0);
		x.setToValue(100);
		x.setCycleCount(1);
		x.setInterpolator(Interpolator.EASE_OUT);
		x.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
