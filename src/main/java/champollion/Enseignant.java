package champollion;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Un enseignant est caractérisé par les informations suivantes : son nom, son adresse email, et son service prévu,
 * et son emploi du temps.
 */
public class Enseignant extends Personne {

    @Getter
    private ArrayList<ServicePrevu> servicesPrevus;

    @Getter
    private ArrayList<Intervention> interventions;


    // TODO : rajouter les autres méthodes présentes dans le diagramme UML

    public Enseignant(String nom, String email) {
        super(nom, email);
        servicesPrevus = new ArrayList<ServicePrevu>();
        interventions = new ArrayList<Intervention>();
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {

        int hPrevues=0;
        for(ServicePrevu s : servicesPrevus){
            hPrevues+= s.getVolumeCM() + s.getVolumeTD() + s.getVolumeTP();
        }
        return hPrevues;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue)/* throws IllegalArgumentException*/ {
        
        boolean verifExistenceUE=false;
        int hPrevuesUE=0;
        for(ServicePrevu s : servicesPrevus){
            if (s.getUniteEnseignement().equals(ue)){
                hPrevuesUE += s.getVolumeCM() + s.getVolumeTD() + s.getVolumeTP();
                verifExistenceUE=true;
            }
        }

        if (!verifExistenceUE) {
            throw new IllegalArgumentException("UE non enseigné par cet enseignant");
        }
        return hPrevuesUE;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magistral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {

        //Ajouter un servicePrevu à la liste "servicesPrevus", avec les valeurs des attributs spécifiés en paramètres
        servicesPrevus.add(new ServicePrevu(ue, volumeCM, volumeTD, volumeTP));
    }

    public void ajouteIntervention(UE uniteEnseignement, TypeIntervention type_Intervention, int duree) throws IllegalStateException{
        
        if (duree <= this.heuresPrevuesPourUE(uniteEnseignement)){
            this.interventions.add(new Intervention(uniteEnseignement, type_Intervention, duree));
        }
        else 
            throw new IllegalArgumentException("Ajout ne peut pas aboutir");

    }
}
