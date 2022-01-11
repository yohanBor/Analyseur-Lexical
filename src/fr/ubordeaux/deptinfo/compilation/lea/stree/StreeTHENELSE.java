package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeTHENELSE extends Stree {

	// Si il y a un else
	public StreeTHENELSE(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
	}

	//Sans else
	public StreeTHENELSE(Stree left) throws TypeException, StreeException {
		this(left, null);
	}

	@Override
	public boolean checkType() throws StreeException {
		return true;
	}
	

}
