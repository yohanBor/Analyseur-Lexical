package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;

public class StreePRODUCT extends Stree {
	
	public StreePRODUCT(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
	}

	 //added
	 @Override
	 public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		Type typeRight = getLeft().getType();
		if (typeLeft != null)
			return typeLeft.assertEqual(typeRight);
		else
			throw new StreeException("Type error while checking null types !");
	}

	public Type getType() throws StreeException {
		return getLeft().getType();
	}

	
}
