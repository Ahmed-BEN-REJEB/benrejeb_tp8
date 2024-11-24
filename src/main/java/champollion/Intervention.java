package champollion;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

public class Intervention {
    
    //Définition des attributs
    @Getter
    @Setter
    private Date dateDebut;

    @Getter
    @Setter
    private int heureDebut;

    @Getter
    @Setter
    private int duree=0;

    @Getter
    @Setter
    private boolean annulee=false;

    @Getter
    @Setter
    private TypeIntervention type_Intervention;

    @Getter
    @Setter
    private UE uniteEnseignement;

    //Définition du constructeur
    Intervention(UE uniteEnseignement, TypeIntervention type_Intervention, int duree){
        this.uniteEnseignement = uniteEnseignement;
        this.type_Intervention = type_Intervention;
        this.duree = duree;
    }
}
