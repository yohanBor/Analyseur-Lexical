package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;


public class StreeBREAK extends Stree {

	private JUMP stm;

	public StreeBREAK() throws TypeException, StreeException {
		super();
	}

	@Override
	public boolean checkType() throws StreeException {
		return true;
	}

	//added
	public Type getType() throws StreeException{
		return type; 
	}

	@Override
	public Stm getStm() {
		return stm;
	}	
}
