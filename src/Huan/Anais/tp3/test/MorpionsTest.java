package Huan.Anais.tp3.test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Huan.Anais.tp3.ModeleMorpions;

import morpions.kit.test.SpecifModeleMorpions;

public class MorpionsTest
{
	SpecifModeleMorpions morpions ;
	public static final int TAILLE = 3 ;
	public static final int NB_CASES = 9 ;


	@BeforeEach
	public void setUp() throws Exception
	{
		// morpions = new MorpionsReference() ;
		// morpions = new Bogue1() ;
		// morpions = new Bogue2() ;
		// morpions = new Bogue3() ;
		// morpions = new Bogue4() ;
		// morpions = new Bogue5() ;
		morpions = new ModeleMorpions() ;
	}

	@Test
	public void testInit()
	{
		// ----------------------
		// SÉQUENCE 1 À COMPLÉTER
		// ----------------------

		// Test de l'invariant de la classe
		testInvariant() ;
	}

	@Test
	public void testPremierCoup()
	{
		morpions.jouerCoup(1, 1) ;
		assertTrue(! morpions.estFinie(), "Partie pas finie après premier coup") ;
		// ----------------------
		// SÉQUENCE 2 À COMPLÉTER
		// ----------------------

		// On reteste l'invariant
		testInvariant() ;
	}

	private void testInvariant()
	{
		// Le nombre de coups est positif ou nul, et inférieur ou égal au nombre de cases
		assertTrue(morpions.getNombreCoups() >= 0, "Nombre de coups >= 0") ;
		assertTrue(morpions.getNombreCoups() <= NB_CASES,
				"Nombre de coups <= " + NB_CASES) ;
		// ----------------------
		// SÉQUENCE 3 À COMPLÉTER
		// ----------------------
	}
	@Test
	public void testCoupDejaJoue()
	{
		morpions.jouerCoup(1, 1);
		assertTrue(!morpions.estCoupAutorise(1,1), "La case (1,1) ne peut être jouée") ;
	}

	@Test
	public void testPartiePasFinie()
	{
		morpions.jouerCoup(1, 1);
		morpions.jouerCoup(2, 2);
		// ----------------------
		// SÉQUENCE 4 À COMPLÉTER
		// ----------------------
		assertFalse(morpions.estFinie(), "Partie pas finie") ;
	}

	@Test
	public void testJoueur1gagnant()
	{	
		
			morpions.jouerCoup(1, 1);
			morpions.jouerCoup(2, 2);
			morpions.jouerCoup(1, 2);
			morpions.jouerCoup(3, 1);
			morpions.jouerCoup(1, 3);
			
		 assertEquals( 1 , morpions.getVainqueur()); 
	}	
}

