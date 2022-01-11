package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.CJUMP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.JUMP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.LABEL;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.SEQ;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeSWITCH extends Stree {

	private static final String TEST_CASE = "testCase";
	private static final String STM_CASE = "stmCase";	
	private Stm stm;
	private int nbCase;

	public StreeSWITCH(Stree left, Stree right, int nbCase) throws TypeException, StreeException {
		super(left, right);
		this.setTypeReturn(getLeft().getType());
		this.nbCase = nbCase;
		this.stm = generateIntermediateCode2();
	}

	public Stm generateIntermediateCode2() throws StreeException, TypeException {

		//Crée tab_label de taille 2 * nbCase + 2
		//où l'indice 0 correspond au cas default, les indices de 1 à nbCase pour les cases, les indices nbCase+1 à 2*nbCase aux tests, et le dernier nbCase*2+1 au label "end"
		Label tabLabel[] = new Label[2*nbCase+2];
		tabLabel[0]= new Label("Default");
		for (int i = 1 ; i <= nbCase ; i++) 
			tabLabel[i] = new Label("Case_" + String.valueOf(i));
		for (int i = nbCase + 1 ; i < tabLabel.length-1 ; i++) 
			tabLabel[i] = new Label("Test_Case_" + String.valueOf(i - nbCase));
		tabLabel[2*nbCase+1]= new Label("End");

		//Crée tab_Cjump de taille nbCase
		//Pour une meilleure utilisation du tableau, le premier élement est à l'indice 1
		CJUMP[] tabCJUMP = new CJUMP[nbCase+1]; //2 cases : tab[3] -> [0,1,2]
		int index = 1;
		while (index < tabCJUMP.length) {
			if (!checkType(getRight(index).getLeft().getLeft().getType()))
				throw new TypeException("Type error while checking " + this.getClass().getSimpleName() +" : Type du switch : " + getLeft().getType() + " VS type du case : " + getRight(index).getLeft().getLeft().getType());
			
			if (index < tabCJUMP.length - 1) {
				tabCJUMP[index] = new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), getRight(index).getLeft().getLeft().getExp(), tabLabel[index], tabLabel[nbCase+index+1]);
				
			//Cas particulier du dernier jump qui doit aller sur défault:
			}else {
				tabCJUMP[index] = new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), getRight(index).getLeft().getLeft().getExp(), tabLabel[index], tabLabel[0]);
				
			}
			index++;
		}
		return createSEQ(1, nbCase, TEST_CASE, tabLabel, tabCJUMP);																					
	}
	
	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		
		if(typeLeft != null && getRight().getType() != null)
			return(typeLeft.assertEqual(getRight().getType()));			
		else
			throw new StreeException("Type error while checking " + this.getClass().getSimpleName() +" : Type du switch : " + typeLeft + " VS type du case : " + getRight().getType());
	}

	public boolean checkType(Type caseR) throws StreeException {
		Type typeLeft = getLeft().getType();

		if(typeLeft != null && caseR != null )
			return(typeLeft.assertEqual(caseR));
					
		else
			throw new StreeException("Type error while checking null types !");
	}

	@Override
	public Stm getStm() {		
		return stm;
	}

	public SEQ createSEQ(int activeCase, int nbCase, String type, Label[] tabLabel, CJUMP[] tabCJUMP) throws StreeException{
			
			//Génération du code inter. pour tester tous les différents cases:
			if (type.equals(TEST_CASE) && activeCase < nbCase){
				return new SEQ(new LABEL(tabLabel[nbCase+activeCase]),
					new SEQ(tabCJUMP[activeCase], createSEQ(activeCase+1, nbCase, TEST_CASE, tabLabel, tabCJUMP)));
			}
			
			//Si nous arrivons au dernier case a tester, nous passons dans l'écriture du code de chaque case (et du défault) et on remet active case a 1
			if (type.equals(TEST_CASE) && nbCase==activeCase)
				return new SEQ(new LABEL(tabLabel[nbCase+activeCase]),
						new SEQ(tabCJUMP[activeCase], createSEQ(1, nbCase, STM_CASE, tabLabel, tabCJUMP)));
			
			//Génération du STM_CASE pour écrire le code au sein des différents cases
			if (type.equals(STM_CASE) && activeCase < nbCase )
				return new SEQ(new LABEL(tabLabel[activeCase]),
						new SEQ(getRight(activeCase).getLeft().getStm(),
								new SEQ(new JUMP(tabLabel[2*nbCase+1]), createSEQ(activeCase+1, nbCase, STM_CASE, tabLabel, tabCJUMP )))); //A changer ou verif
			
			//Le cas d'arretSTM_CASEcursion: nous sommes sur la dernière partie du code inter.
			if (type.equals(STM_CASE) && activeCase==nbCase)
				return new SEQ (new LABEL(tabLabel[nbCase]),
						new SEQ ((getRight(nbCase)).getLeft().getStm(),
								new SEQ (new JUMP(tabLabel[2*nbCase+1]),
										new SEQ ( new LABEL(tabLabel[0]),
												new SEQ ((getRight(nbCase+1)).getStm(), // +1 getRight ???
														new LABEL(tabLabel[2*nbCase+1]))))));
			//N'arrive jamais ici
			return null;	
	}

	@Override
	public Type getType() throws StreeException{
		return getLeft().getType(); 
	}
}