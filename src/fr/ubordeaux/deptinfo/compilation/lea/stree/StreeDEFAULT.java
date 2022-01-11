package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeDEFAULT extends Stree {

	private Stm stm;

	public StreeDEFAULT(Stree left) throws TypeException, StreeException {
		super(left);
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		return getLeft().getStm();
	}

	@Override
	public boolean checkType() throws StreeException {
		return true;
	}

	public Type getType() throws StreeException{
		return getLeft().getType(); 
	}

	public Stm getStm() {
		return stm;
	}
}
