package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;

public class StreeTHIS extends Stree {

	//private Type type;

	public StreeTHIS() throws TypeException, StreeException {
		super();
	}

	@Override
	public boolean checkType() throws StreeException {
		return true;
	}
	
	@Override
	public Type getType() throws StreeException {
		return type;
	}
}
