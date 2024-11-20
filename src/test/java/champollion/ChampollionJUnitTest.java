package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
	}
	

	//Test 1 : Test permettant de vérifier qu'un nouvel enseignant est sans service (nombre d'heures du service prévu total est égale à 0) 
	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}

	//Test 2 : Test permettant de vérifier l'enregistrement(Ajout) d'un enseignement donné aux enseignements d'un enseignant spécifique
	@Test
	public void testAjoutNouvelEnseignement() {
		untel.ajouteEnseignement(uml, 12, 20, 20);
		int taille = untel.getServicesPrevus().size();
		assertEquals(1, taille,
                        "La liste doit retourner 1 comme taille de liste");
	}
	
	//Test 3 : Test permettant de vérifier le nombre d'heures du service prévu total que posséde un enseignant spécifique
	@Test
	public void testAjouteHeures(){
		untel.ajouteEnseignement(uml, 0, 10, 0);
		untel.ajouteEnseignement(java, 10, 20, 0);
		assertEquals(10 + 20 + 10, untel.heuresPrevues(),
		"L'enseignant doit maintenant avoir 40 heures prévues comme volume horaire total (nb heures de service prévu total)");
	}

	//Test 4 : permettant de vérifier le nombre d'heures que posséde un enseignant spécifique pour un service prévu mais en se limitant à l'UE donné
	@Test
	public void testAjouteHeuresAUE() {
    	// 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);
		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

		// 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);
                
		//L'ajout d'un nouvel enseignement avec l'UE "java" pour s'assurer que la méthode heuresPrevuesPourUE(UE ue) ne concerne que le service prévu pour l'UE en paramètres
		untel.ajouteEnseignement(java, 0, 20, 0);

		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}

	// Test 5 : permettant de vérifier que l'enseignant concerné enseigne bien l'UE lors de calcul du service prévu pour un enseignement en heures
	@Test
	public void verifExistenceUE(){
		untel.ajouteEnseignement(uml, 10, 0, 0);
		Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> untel.heuresPrevuesPourUE(java));
		assertEquals("UE non enseigné par cet enseignant", exception.getMessage(), "L'exception n'a pas le message attendu");
	}

	//Test 6 : Tester la méthode ajouteIntervention()
	@Test
	public void testAjoutNouvelleintervention() {

		int taille = untel.getInterventions().size();
		assertEquals(0, taille,
						"La liste doit retourner 0 comme taille de liste");

		
		untel.ajouteEnseignement(uml, 0, 20, 0);
		untel.ajouteIntervention(uml, TypeIntervention.CM, 10);

		taille = untel.getInterventions().size();
		assertEquals(1, taille,
						"La liste doit retourner 1 comme taille de liste");
	}


	
}