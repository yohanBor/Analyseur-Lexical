package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.CONST;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;



public class StreeFLOAT extends Stree {

	private Float value;
	private Exp exp;

	public StreeFLOAT(Float value) throws TypeException, StreeException {
		super();
		this.value = value;
		this.exp = new CONST(value.intValue());
		this.type = new TypeExpression(Tag.FLOAT);
	}


	@Override
	public boolean checkType() throws StreeException {
		return true;
	}

	public Type getType() throws StreeException {
		return type;
	}

	//@Override
	public Exp getExp(){
		return exp;
	}


}
