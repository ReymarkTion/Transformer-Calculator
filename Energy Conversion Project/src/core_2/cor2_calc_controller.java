package core_2;

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
public class cor2_calc_controller implements Initializable {
	
	@FXML private Label U1;
	@FXML private Label U2;
	
	
	@FXML private Label RelativePermeability_label;
	@FXML private Label permeability_o_free_space_label;
	@FXML private Label total_length_label;
	@FXML private Label core_depth_label;
	@FXML private Label emf_label;
	@FXML private Label num_turns_label;
	@FXML private Label current_label;
	
	@FXML private Label flux_den_left_label;
	@FXML private Label flux_den_right_label;
	
	@FXML private Label reluctance_1_label;
	@FXML private Label reluctance_2_label;
	@FXML private Label reluctance_3_label;
	@FXML private Label reluctance_4_label;
	@FXML private Label reluctance_5_label;
	@FXML private Label reluctance_tot_label;
	
	@FXML private Label flux_right_label;
	@FXML private Label flux_left_label;
	@FXML private Label flux_center_label;
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		U1.setText(core2_controller.__unit);
		U2.setText(core2_controller.__unit);
		solveReluctance();
	}
	
	
	public void solveReluctance() {
		
		float length = 0;
		float temp = 0;
		float emf = 0;
		
		float l1 = Main.Main.getCore_data().getData("l1");
		float l2 = Main.Main.getCore_data().getData("l2");
		float l3 = Main.Main.getCore_data().getData("l3");
		float l4 = Main.Main.getCore_data().getData("l4");
		float l5 = Main.Main.getCore_data().getData("l5");
		float l6 = Main.Main.getCore_data().getData("l6");
		float l7 = Main.Main.getCore_data().getData("l7");
		float l8 = Main.Main.getCore_data().getData("l8");
		
		float air_gap_L = Main.Main.getCore_data().getData("air gap left");
		float air_gap_R = Main.Main.getCore_data().getData("air gap rigth");
		float air_gap_inc = Main.Main.getCore_data().getData("air gap inc");
		
		
		float tot_length = (l1/2) + l2 + (l3/2) + (l4/2) + l5 + (l6/2); // ----------------------nf
		tot_length = 2*tot_length;
		
		float relative_permeability = Main.Main.getCore_data().getData("permeability");
		
		float total_Reluctance = 0;
		float depth = Main.Main.getCore_data().getData("depth");
		
		float current = Main.Main.getCore_data().getData("current");
		float flux_tot = Main.Main.getCore_data().getData("flux");
		
		float flux_center 	= 0;
		float flux_left 	= 0;
		float flux_right 	= 0;
		
		float flux_den_air_R = 0;
		float flux_den_air_L = 0;
		
		float num_turn = Main.Main.getCore_data().getData("number of turns");
	
		float r1 = 0;
		float r2 = 0;
		float r3 = 0;
		float r4 = 0;
		float r5 = 0;
		
		float temp_rel_1 = 0;
		float temp_rel_2 = 0;
		float temp_rel_3 = 0;
		
		
		// RELUCTANCE 1
		float temp_area = (l6) * depth;
		temp = (relative_permeability * Main.Main.getM_o() * temp_area);
		length = ((l1/2) + l2 + (l3/2));
		temp_rel_1 = (length / temp);
		
		temp_area = (l8) * depth;
		temp = (relative_permeability * Main.Main.getM_o() * temp_area);
		temp_rel_2 = (length / temp);
		
		temp_area = (l1) * depth;
		temp = (relative_permeability * Main.Main.getM_o() * temp_area);
		length = ((l6/2) + l7 + (l8/2));
		temp_rel_3 = (length / temp);
		
		r1 = temp_rel_1 + temp_rel_2 + temp_rel_3;
		
		// left air gap reluctance 
		temp = (Main.Main.getM_o() * (temp_area * (1 + air_gap_inc)));
		r2 = (air_gap_L / temp);
		
		// RELUCTANCE 2
		temp_area = (l6) * depth;
		temp = (relative_permeability * Main.Main.getM_o() * temp_area);
		length = ((l3/2) + l4 + (l5/2));
		temp_rel_1 = (length / temp);
		
		temp_area = (l8) * depth;
		temp = (relative_permeability * Main.Main.getM_o() * temp_area);
		temp_rel_2 = (length / temp);
		
		temp_area = (l5) * depth;
		temp = (relative_permeability * Main.Main.getM_o() * temp_area);
		length = ((l6/2) + l7 + (l8/2));
		temp_rel_3 = (length / temp);
		
		r3 = temp_rel_1 + temp_rel_2 + temp_rel_3;
		
		// right air gap reluctance 
		temp = (Main.Main.getM_o() * (temp_area * (1 + air_gap_inc)));
		r4 = (air_gap_R / temp);
		
		temp_area = (l3) * depth;
		temp = (relative_permeability * Main.Main.getM_o() * temp_area);
		length = ((l6/2) + l7 + (l8/2));
		r5 = (length / temp);
		
		total_Reluctance = (r5 + ((r1 + r2)*(r3 + r4))/(r1 + r2 + r3 + r4));
		
		// -----------------
		if(current == 0) {
			
			current = ((flux_tot * total_Reluctance) / num_turn);
			flux_center = flux_tot;
			
			//System.out.println("Fluc tot: "+flux_tot);
			
		} else if(flux_tot == 0) {
			// flux
			flux_center = ((num_turn * current) / total_Reluctance);
			flux_tot 	= flux_center;
		}
		
		flux_left 	= (((r3 + r4) / (r1 + r2 + r3 + r4)) * flux_tot);
		flux_right 	= (((r1 + r2) / (r1 + r2 + r3 + r4)) * flux_tot);
		
		// flux density
		float A_eff 	= (((air_gap_L*100) * (air_gap_R * 100)) * (1 + air_gap_inc));
		flux_den_air_L 	= flux_left / A_eff;
		flux_den_air_R 	= flux_right / A_eff;
		
		// electromotive force
		emf = (flux_tot * total_Reluctance);
		
		
		/********************************** display **************************************/
		RelativePermeability_label.setText(Float.toString(relative_permeability));
		permeability_o_free_space_label.setText(Float.toString(Main.Main.getM_o()));
		total_length_label.setText(Float.toString(tot_length));
		core_depth_label.setText(Float.toString(depth));
		emf_label.setText(Float.toString(emf));
		num_turns_label.setText(Float.toString(num_turn));
		current_label.setText(Float.toString(current));
		

		flux_den_left_label.setText(Float.toString(flux_den_air_L));
		flux_den_right_label.setText(Float.toString(flux_den_air_R));
		
		flux_right_label.setText(Float.toString(flux_right));
		flux_left_label.setText(Float.toString(flux_left));
		flux_center_label.setText(Float.toString(flux_center));
		
		reluctance_1_label.setText(Float.toString(r1));
		reluctance_2_label.setText(Float.toString(r2));
		reluctance_3_label.setText(Float.toString(r3));
		reluctance_4_label.setText(Float.toString(r4));
		reluctance_5_label.setText(Float.toString(r5));
		reluctance_tot_label.setText(Float.toString(total_Reluctance));
		
		
	}

}
