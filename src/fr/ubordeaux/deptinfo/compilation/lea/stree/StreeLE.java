package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.BINOP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;
public class StreeLE extends Stree {

	private BINOP exp;

	public StreeLE(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.exp = new BINOP(BINOP.Code.LE, left.getExp(), right.getExp());
	}

	@Override
	public Exp getExp(){
		return exp;
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		Type typeRight = getLeft().getType();
		if (typeLeft != null)
			return typeLeft.assertEqual(typeRight);
		else
			throw new StreeException("Type error while checking null types !");
	}

	//added
	public Type getType() throws StreeException{
		return new TypeExpression(Tag.BOOLEAN); 
	}

}
