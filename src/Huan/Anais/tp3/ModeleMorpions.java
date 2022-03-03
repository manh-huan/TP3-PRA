package Huan.Anais.tp3;

import morpions.kit.test.SpecifModeleMorpions;

public class ModeleMorpions implements SpecifModeleMorpions {

	private int[][] tab = new int[TAILLE][TAILLE];
	int horizontale = 0;
	int diagonale = 0;
	int verticale = 0;
	
	
	
	public void setVal (int ligne, int colonne, int val) {
		
		this.tab[ligne -1][colonne -1] = val; 
	}
	public int getVal (int ligne , int colonne) {
		return this.tab [ligne -1][colonne -1];
	}
	
	public ModeleMorpions() {
		for (int i = 1; i < tab.length; i++) {
			for (int j = 1; j < tab.length; j++) {
				this.setVal(i,j,0) ;
			}
		}
	}
	

	public boolean matchNonNull() {
		boolean nonNull = false;
		while (horizontale == 0 && diagonale == 0 && verticale == 0 && nonNull == false) {
			for (int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab.length; j++) {
					horizontale = horizontale * tab[i][j];
					verticale = verticale * tab[j][i];
				}
				diagonale = diagonale * tab[i][i];
			}
			switch (diagonale) {
			case 1:
				nonNull = true;
				break;
			case 8:
				nonNull = true;
				break;
			default:
				diagonale = 0;
				nonNull = false;
				break;
			}
			switch (horizontale) {
			case 1:
				nonNull = true;
				break;
			case 8:
				nonNull = true;
				break;
			default:
				horizontale = 0;
				nonNull = false;
				break;
			}
			switch (verticale) {
			case 1:
				nonNull = true;
				break;
			case 8:
				nonNull = true;
				break;
			default:
				verticale = 0;
				nonNull = false;
				break;
			}
		}
		return nonNull;
	}

	@Override
	public Etat getEtatJeu() {
		Etat x = Etat.J1_JOUE;
		int jouer1 = 0;
		int jouer2 = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				if (tab[i][j] == 1) {
					jouer1++;
				}
				if (tab[i][j] == 2) {
					jouer2++;
				}
			}
		}
		if ((jouer1 + jouer2) < 9) {
			if (jouer1 < jouer2) {
				x = Etat.J2_JOUE;
			} else if (jouer2 > jouer1) {
				x = Etat.J1_JOUE;
			} else {
				x = Etat.J1_JOUE;
			}
		} else if ( this.matchNonNull() == true) {
			if(getVainqueur() == 1) {
				x = Etat.J1_VAINQUEUR;
			} else {
				x = Etat.J2_VAINQUEUR;
			}
		} else { 
			x = Etat.MATCH_NUL;
		}	
		return x;
	}

	@Override
	public int getJoueur() {
		int x = 0;
		if(this.getEtatJeu() == Etat.J1_JOUE) {
			x = 1;
		} else if (this.getEtatJeu() == Etat.J2_JOUE) { 
			x = 2;
		} else if(this.estFinie()) {
			x = 0;
		}
		return x;
	}

	@Override
	public int getVainqueur() {
		int vainQueur = 0;
		if (this.matchNonNull() == true) {
			for (int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab.length; j++) {
					horizontale = horizontale * tab[i][j];
					verticale = verticale * tab[j][i];
				}
				diagonale = diagonale * tab[i][i];
			}
			switch (diagonale) {
			case 1:
				vainQueur = 1;
				break;
			case 8:
				vainQueur = 2;
				break;
			}
			switch (horizontale) {
			case 1:
				vainQueur = 1;
				break;
			case 8:
				vainQueur = 2;
				break;
			}
			switch (verticale) {
			case 1:
				vainQueur = 1;
				break;
			case 8:
				vainQueur = 2;
				break;
			}
		} else {
			vainQueur = 0;
		}

		return vainQueur;
	}
	
	public int rowCheck (int i) {
		int x = 0;
		for(int j = 0 ; j < tab.length ; j++ ) {
			if(tab[i][j] != 0 ) {
				x ++ ;
			}
		}
		return x;
	}

	@Override
	public int getNombreCoups() {
		int x = 0;
		for (int i = 0 ; i < tab.length ; i++ ) {
			x += rowCheck(i) ;
		}
		
		return x;
	}

	@Override
	public boolean estFinie() {
			
		return this.getEtatJeu() != Etat.J1_JOUE && this.getEtatJeu() != Etat.J2_JOUE;
	}

	@Override
	public boolean estCoupAutorise(int ligne, int colonne) {
		
		return ligne < TAILLE && colonne < TAILLE ;
	}

	@Override
	public void jouerCoup(int ligne, int colonne) {
		// TODO Auto-generated method stub
		if(this.estCoupAutorise(ligne, colonne)  && !this.estFinie()) {
			if(this.getEtatJeu() == Etat.J1_JOUE) {
				tab[ligne-1][colonne-1] = 1;
			} else {
				tab[ligne-1][colonne-1] = 2;
			}
		}

	}

}
