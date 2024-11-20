package champollion;

import lombok.Getter;
import lombok.Setter;

public class ServicePrevu {
	
	//Permet d'accéder en mode lecture aux attributs définis
	// @Getter
	//Permet d'accéder en mode écriture aux attributs définis
	// @Setter

	//Définition des attributs
	@Getter
	@Setter
	private UE uniteEnseignement;

	@Getter
	@Setter
	private int volumeCM=0;
	
	@Getter
	@Setter
	private int volumeTD=0;
	
	@Getter
	@Setter
	private int volumeTP=0;
	
	//Définition du constructeur
	ServicePrevu(UE ue, int volumecm, int volumetd, int volumetp){
		uniteEnseignement = ue;
		volumeCM = volumecm;
		volumeTD = volumetd;
		volumeTP = volumetp;
	}
	
}