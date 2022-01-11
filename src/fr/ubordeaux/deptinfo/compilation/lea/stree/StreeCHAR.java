package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeCHAR extends Stree {

	private Integer value;
	public StreeCHAR(Integer value) throws TypeException, StreeException {
		super();
		this.value = value;
		this.type = new TypeExpression(Tag.CHAR);
	}

	public Type getType() throws StreeException {
		return this.type;
	}
	public boolean checkType() throws StreeException {
		return true;
	}
}
