package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.BINOP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;
public class StreeAND extends Stree {

	private BINOP exp;

	public StreeAND(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.exp = new BINOP(BINOP.Code.AND, left.getExp(), right.getExp());
	}

	@Override
	public Exp getExp(){
		return exp;
	}

	//added
	public boolean checkType() throws StreeException{
		return (getRight().getType().assertBoolean() && getLeft().getType().assertBoolean());
	}

	//added
	public Type getType() throws StreeException{
		return new TypeExpression(Tag.BOOLEAN);
	}

}
