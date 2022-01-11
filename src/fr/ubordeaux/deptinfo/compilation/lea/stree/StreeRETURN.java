package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.MEM;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.MOVE;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.NAME;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeRETURN extends Stree {

	private MOVE stm;
	public StreeRETURN(Stree left, Type returnT) throws StreeException, TypeException {
		super(left, returnT);	
		this.stm = new MOVE(new MEM(new NAME(new Label("returnValue"))), left.getExp());
	}

	@Override
	public Stm getStm() {
		return stm;
	}	

	//added
	 @Override
	 public boolean checkType() throws StreeException {
	 	Type typeLeft = getLeft().getType();
	 	if (typeLeft != null){
			System.err.println("return T : " + getTypeReturn());
            return typeLeft.assertEqual(getTypeReturn());
		 }
	 		
	 	else
	 		throw new StreeException("Type error while checking null types !");
	 }

}
