package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeCASE extends Stree {

	private Stm stm;

	public StreeCASE(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.stm = generateIntermediateCode();
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();

		if (typeLeft != null)
			return true;

		else
			throw new StreeException("Type error while checking null types !");
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		return getRight().getStm();
	}
	
	@Override
	public Type getType() throws StreeException{
		return getLeft().getType(); 
	}

	@Override
	public Stm getStm() {
		return stm;
	}
}
