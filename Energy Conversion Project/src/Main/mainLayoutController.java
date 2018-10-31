package Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *     *******************************
 *     *                             *
 *     *  @author Reymark Molo Tion  *
 *     *                             *
 *     *******************************
 */
public class mainLayoutController implements Initializable {

	@FXML private ImageView fig_1;
	@FXML private ImageView fig_2;
	@FXML private ImageView fig_3;
	
	@FXML private JFXButton fig_1_button;
	@FXML private JFXButton fig_2_button;
	@FXML private JFXButton fig_3_button;
	@FXML private JFXButton back;
	
	@FXML private AnchorPane welcomeUsr;
	@FXML private AnchorPane info;
	@FXML private ImageView cpe_logo;
	
	private boolean dropShadows = false;
	private int i;
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(dropShadows == false) {
			fig_1.setEffect(getDropShadow());
			fig_2.setEffect(getDropShadow());
			fig_3.setEffect(getDropShadow());
			dropShadows = true;
		}
		fig_1.setImage(new Image("/figures/figure 1.png"));
		fig_2.setImage(new Image("/figures/figure 2.png"));
		fig_3.setImage(new Image("/figures/figure 3.png"));
		figure_fx(fig_1_button ,fig_1);
		figure_fx(fig_2_button ,fig_2);
		figure_fx(fig_3_button ,fig_3);
		buttonActions();
		
		if(Main.anim_flag == false) {
			animation();
			Main.anim_flag = true;
		}
		
	}
	
	private void animation() {
		// animation
				swipeTransition_toX(welcomeUsr, 200);
				fadeTrans(welcomeUsr);
				PauseTransition pt0 = new PauseTransition();
				pt0.setDuration(Duration.millis(100));
				pt0.setOnFinished(e -> {
					welcomeUsr.setVisible(true);	
				});
				pt0.play();
				
				int duration = 500;
				for(i = 0; i < 5; i++) {
					PauseTransition pt = new PauseTransition();
					pt.setDuration(Duration.millis(duration));
					pt.setOnFinished(e -> {
						welcomeUsr.setEffect(getDropShadow(i));
					});
					pt.play();
					duration += 300;
				}
				
				PauseTransition ptinf_0 = new PauseTransition();
				ptinf_0.setDuration(Duration.millis(2000));
				ptinf_0.setOnFinished(e -> {
					swipeTransition_toX(welcomeUsr, 135);
				});
				ptinf_0.play();
				
				PauseTransition ptinf_2 = new PauseTransition();
				ptinf_2.setDuration(Duration.millis(3000));
				ptinf_2.setOnFinished(e -> {
					info.setVisible(true);
					swipeTransition_toY(info, 70);
					fadeTrans(cpe_logo);
					PauseTransition pt = new PauseTransition();
					pt.setDuration(Duration.millis(100));
					pt.setOnFinished(e2 -> {
						cpe_logo.setVisible(true);	
					});
					pt.play();
				});
				ptinf_2.play();
				
				PauseTransition ptinf_3 = new PauseTransition();
				ptinf_3.setDuration(Duration.millis(5200));
				ptinf_3.setOnFinished(e -> {
					swipeTransition_toY(info, 0);
				});
				ptinf_3.play();
				
				PauseTransition ptinf_4 = new PauseTransition();
				ptinf_4.setDuration(Duration.millis(5500));
				ptinf_4.setOnFinished(e -> {
					info.setVisible(false);
				});
				ptinf_4.play();
				
				PauseTransition pt1 = new PauseTransition();
				pt1.setDuration(Duration.millis(6000));
				pt1.setOnFinished(e -> {
					swipeTransition_toX(welcomeUsr, 65);
				});
				pt1.play();
				
				
				PauseTransition pt2 = new PauseTransition();
				pt2.setDuration(Duration.millis(7000));
				pt2.setOnFinished(e -> {
					swipeTransition_toX(welcomeUsr, 1000);
					
				});
				pt2.play();
	}
	
	private void figure_fx(JFXButton fig_button, ImageView figure) {
		String style = "dashed";
		fig_button.addEventHandler(MouseEvent.MOUSE_MOVED, (ev) -> {
			fig_button.setStyle("-fx-border-style: "+style+"; -fx-background-radius: 0");
			figure.setEffect(getInnerShadow());
		});
		fig_button.addEventHandler(MouseEvent.MOUSE_EXITED, (ev) -> {
			fig_button.setStyle("-fx-background-radius: 0");
			figure.setEffect(getDropShadow());
		});
	}
	
	private void buttonActions() {
		
		back.setOnMouseClicked(e -> {
			try {
				Main.showMainLayout();
			} catch (IOException e1) { e1.printStackTrace(); }
		});
		
		fig_1_button.setOnMouseClicked(e -> {
			try {
				Main.showFigure_1_Layout();
			} catch (IOException e1) { e1.printStackTrace(); }
		});;
		fig_2_button.setOnMouseClicked(e -> {
			try {
				Main.showFigure_2_Layout();
			} catch (IOException e1) { e1.printStackTrace(); }
		});;
		fig_3_button.setOnMouseClicked(e -> {
			try {
				Main.showFigure_3_Layout();
			} catch (IOException e1) { e1.printStackTrace(); }
		});

	}
	
	private DropShadow getDropShadow(int i) {
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(10.0);
		dropShadow.setOffsetX(i);
		dropShadow.setOffsetY(i);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
		return dropShadow;
	}
	
	private InnerShadow getInnerShadow() {
		InnerShadow innerShadow = new InnerShadow();
		innerShadow.setOffsetX(1.5);
		innerShadow.setOffsetY(1.5);
		innerShadow.setColor(Color.web("#688646"));
		return innerShadow;
	}
	
	private DropShadow getDropShadow() {
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(4.5);
		dropShadow.setOffsetX(2.0);
		dropShadow.setOffsetY(2.0);
		dropShadow.setHeight(10.0);
		dropShadow.setWidth(10.0);
		dropShadow.setColor(Color.web("#688646"));
		return dropShadow;
	}
	
	public void swipeTransition_toX(Node node, int pos) {
		TranslateTransition swipeTransition = new TranslateTransition();
		swipeTransition.setNode(node);
		swipeTransition.setDuration(Duration.millis(300));
		swipeTransition.setToX(pos);
		node.setOpacity(1.0);
		swipeTransition.play();
	}
	
	public void swipeTransition_toY(Node node, int pos) {
		TranslateTransition swipeTransition = new TranslateTransition();
		swipeTransition.setNode(node);
		swipeTransition.setDuration(Duration.millis(300));
		swipeTransition.setToY(pos);
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
}
