package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;

public class StreeCONTINUE extends Stree {
	private MOVE stm;

	public StreeCONTINUE() throws TypeException, StreeException {
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
