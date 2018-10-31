package core_1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *     *******************************
 *     *                             *
 *     *  @author Reymark Molo Tion  *
 *     *                             *
 *     *******************************
 */	
public class cor1_calc_controller implements Initializable {
	
	private float Area_1;
	private float Area_2;
	private float Area_3;
	private float Area_4;
	
	@FXML private Label U1;
	@FXML private Label U2;
	@FXML private Label U3;
	@FXML private Label U4;
	@FXML private Label U5;
	@FXML private Label U6;
	@FXML private Label U7;
	
	@FXML private Label RelativePermeability_label;
	@FXML private Label permeability_o_free_space_label;
	@FXML private Label total_length_label;
	@FXML private Label core_depth_label;
	@FXML private Label emf_label;
	@FXML private Label num_turns_label;
	@FXML private Label current_label;
	@FXML private Label flux_label;
	
	@FXML private Label flux_den_1_label;
	@FXML private Label flux_den_2_label;
	@FXML private Label flux_den_3_label;
	@FXML private Label flux_den_4_label;
	@FXML private Label flux_den_tot_label;
	
	@FXML private Label reluctance_1_label;
	@FXML private Label reluctance_2_label;
	@FXML private Label reluctance_3_label;
	@FXML private Label reluctance_4_label;
	@FXML private Label reluctance_tot_label;
	
	@FXML private Label Area_1_label;
	@FXML private Label Area_2_label;
	@FXML private Label Area_3_label;
	@FXML private Label Area_4_label;
	@FXML private Label Area_tot_label;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		U1.setText(core1_controller.__unit);
		U2.setText(core1_controller.__unit);
		U3.setText(core1_controller.__unit+"^2");
		U4.setText(core1_controller.__unit+"^2");
		U5.setText(core1_controller.__unit+"^2");
		U6.setText(core1_controller.__unit+"^2");
		U7.setText(core1_controller.__unit+"^2");
		solveReluctance();
	}
	
	
	public void solveReluctance() {
		
		float length = 0;
		float temp = 0;
		float emf = 0;
		float Area_tot = 0;
		
		float l1 = Main.Main.getCore_data().getData("l1");
		float l2 = Main.Main.getCore_data().getData("l2");
		float l3 = Main.Main.getCore_data().getData("l3");
		float l4 = Main.Main.getCore_data().getData("l4");
		float l5 = Main.Main.getCore_data().getData("l5");
		float l6 = Main.Main.getCore_data().getData("l6");
		float tot_length = (l1/2) + l2 + (l3/2) + (l4/2) + l5 + (l6/2); // ----------------------nf
		tot_length = 2*tot_length;
		
		float relative_permeability = Main.Main.getCore_data().getData("permeability");
		float permeabilityof_f_space = (float) (4 * Math.PI * Math.pow(10, -7));
		float total_Reluctance = 0;
		float depth = Main.Main.getCore_data().getData("depth");
		
		float current = Main.Main.getCore_data().getData("current");
		float flux = Main.Main.getCore_data().getData("flux");
		
		float num_turn = Main.Main.getCore_data().getData("number of turns");
	
		float reluctance1 = 0;
		float reluctance2 = 0;
		float reluctance3 = 0;
		float reluctance4 = 0;
		
		// RELUCTANCE 1
		length = (l3/2) + l2 + (l1/2);
		Area_1 = l6 * depth;
		temp = (relative_permeability)*(permeabilityof_f_space)*(Area_1);
		reluctance1 = (length / temp);
		total_Reluctance += reluctance1;
		
		// RELUCTANCE 2
		length = (l6/2) + l5 + (l4/2);
		Area_2 = l1 * depth;
		temp = (relative_permeability)*(permeabilityof_f_space)*(Area_2);
		reluctance2 = (length / temp);
		total_Reluctance += reluctance2;
					
		// RELUCTANCE 3
		length = (l1/2) + l2 + (l3/2);
		Area_3 = l4 * depth;
		temp = (relative_permeability)*(permeabilityof_f_space)*(Area_3);
		reluctance3 = (length / temp);
		total_Reluctance += reluctance3;
		
		// RELUCTANCE 4
		length = (l4/2) + l5 + (l6/2);
		Area_4 = l3 * depth;
		temp = (relative_permeability)*(permeabilityof_f_space)*(Area_4);
		reluctance4 = (length / temp);
		total_Reluctance += reluctance4;
		
		Area_tot = Area_1 + Area_2 + Area_3 + Area_4;
		//System.out.println("Total reluctance: "+total_Reluctance);	
		
		// -----------------
						
		if(current != 0 && flux != 0) {
			calc_flux_dens(flux);
			emf = flux*total_Reluctance;
		} else if(current == 0) {
			// ELECTROMOTIVE FORCE
			emf = flux*total_Reluctance;
			// FLUX
			current = emf/num_turn;
			// Flux density of each side of the core
			calc_flux_dens(flux);
		} else if(flux == 0) {
			flux = current*num_turn/total_Reluctance;
			emf = flux*total_Reluctance;
			calc_flux_dens(flux);
		}
		
		
		/********************************** display **************************************/
		RelativePermeability_label.setText(Float.toString(relative_permeability));
		permeability_o_free_space_label.setText(Float.toString(permeabilityof_f_space));
		total_length_label.setText(Float.toString(tot_length));
		core_depth_label.setText(Float.toString(depth));
		emf_label.setText(Float.toString(emf));
		num_turns_label.setText(Float.toString(num_turn));
		current_label.setText(Float.toString(current));
		flux_label.setText(Float.toString(flux));
		
		reluctance_1_label.setText(Float.toString(reluctance1));
		reluctance_2_label.setText(Float.toString(reluctance2));
		reluctance_3_label.setText(Float.toString(reluctance3));
		reluctance_4_label.setText(Float.toString(reluctance4));
		reluctance_tot_label.setText(Float.toString(total_Reluctance));
		
		Area_1_label.setText(Float.toString(Area_1));
		Area_2_label.setText(Float.toString(Area_2));
		Area_3_label.setText(Float.toString(Area_3));
		Area_4_label.setText(Float.toString(Area_4));
		Area_tot_label.setText(Float.toString(Area_tot));
		
	}
	
	private void calc_flux_dens(float flux) {
		// Flux density of each side of the core
		float flux_den_tot = 0;
		float flux_den_1 = flux/Area_1;
		float flux_den_2 = flux/Area_2;
		float flux_den_3 = flux/Area_3;
		float flux_den_4 = flux/Area_4;
		
		flux_den_tot = flux_den_1 + flux_den_2 + flux_den_3 + flux_den_4;
			
		flux_den_1_label.setText(Float.toString(flux_den_1));
		flux_den_2_label.setText(Float.toString(flux_den_2));
		flux_den_3_label.setText(Float.toString(flux_den_3));
		flux_den_4_label.setText(Float.toString(flux_den_4));
		flux_den_tot_label.setText(Float.toString(flux_den_tot));
		
		/*System.out.println("flux_den_1: "+flux_den_1);
		System.out.println("flux_den_2: "+flux_den_2);
		System.out.println("flux_den_3: "+flux_den_3);
		System.out.println("flux_den_4: "+flux_den_4); */
		
	}
	
	
}
