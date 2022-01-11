package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.BINOP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeDIV extends Stree {

	private Exp exp;

	public StreeDIV(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.exp = new BINOP(BINOP.Code.DIV, left.getExp(), right.getExp());
	}

	@Override
	public Exp getExp(){
		return exp;
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		Type typeRight = getRight().getType();
		type = typeLeft;
		if ((typeLeft != null) && (typeRight != null) && (typeLeft.getTag().equals(Tag.FLOAT) || typeLeft.getTag().equals(Tag.INTEGER)))
			return typeLeft.assertEqual(typeRight);
		else
			throw new StreeException("Type error while checking null types !");
	}

	@Override
	public Type getType() throws StreeException {
		return getLeft().getType();
	}

}
