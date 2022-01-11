package fr.ubordeaux.deptinfo.compilation.lea.stree;

import java.util.HashMap;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeSLOT extends Stree {

	private String name;
	private Exp exp;

	public StreeSLOT(Stree left, String name) throws TypeException, StreeException {
		super(left);
		this.name = name;
		//this.exp=new BINOP(BINOP.Code.POINT, left.getExp(), name);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + '(' + getLeft().toString() + '.' + name + ')';
	}

	//added

	// a.b --> il faut vérifier que a est une structure (classe) qui contient un slot "b". Puis calculer le type de b. 
	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
	
		if (getLeft().getType() != null){
			if(typeLeft.getTag().getSize()==0){
				System.err.println("type : " + getLeft().getType());
				return true;
			}
			return false;
	    }		
	    else
		     throw new StreeException("Type error while checking null types !");
	    }

	 @Override
	 public Exp getExp(){
	 	return this.exp;
	 }
}
