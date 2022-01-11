package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.MOVE;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeSUCC extends Stree {

	public static final Boolean RIGHT = false; // si il n'y a rien a droite
	public static final Boolean LEFT = true;  // si y a quelque chose à droite 
	private Boolean rank;
	private MOVE stm;

	public StreeSUCC(Stree left, Boolean rank) throws TypeException, StreeException {
		super(left);
		this.rank = rank;
	}

	@Override
	public boolean checkType() throws StreeException {
		Tag tag = getLeft().getType().getTag();
		return tag.equals(Tag.INTEGER) || tag.equals(Tag.FLOAT) ||  tag.equals(Tag.BOOLEAN) || tag.equals(Tag.CHAR) ;
	}

	@Override
	public Type getType() throws StreeException {
		return getLeft().getType();
	}

	@Override
	public Stm getStm() {
		return stm;
	}	
}
