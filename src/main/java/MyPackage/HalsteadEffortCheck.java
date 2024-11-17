package MyPackage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;
import java.lang.Math;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadEffortCheck extends AbstractCheck {
	private HashSet<String> uniqueOperands = new HashSet<String>();
	private HashSet<String> uniqueOperators = new HashSet<String>();
	private int operands = 0;
	private int operators = 0;
	
	public int getUniqueOperands()
	{
		return uniqueOperands.size();
	}
	
	public int getUniqueOperators()
	{
		return uniqueOperators.size();
	}
	
	public int getOperands()
	{
		return operands;
	}
	
	public int getOperators()
	{
		return operators;
	}


	@Override
	public int[] getAcceptableTokens() {
		return Globals.OPERANDS_OPERATORS;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	@Override
	public int[] getDefaultTokens() {
		  return this.getAcceptableTokens();
	}
	
	@Override
	public void beginTree(DetailAST rootAst)
	{
		uniqueOperands = new HashSet<String>();
		uniqueOperators = new HashSet<String>();
		operands = 0;
		operators = 0;
	}
	
	@Override
	public void finishTree(DetailAST rootAst)
	{
		double volume = operands+operators * Math.log(uniqueOperands.size()+uniqueOperators.size())/Math.log(2);
		double difficulty = uniqueOperators.size()/2 * operands/uniqueOperands.size();
		log(rootAst.getLineNo(), "Halstead Effort:" + difficulty*volume);
	}

	@Override
	public void visitToken(DetailAST ast) {
		// check if token is a operator
		if( IntStream.of(Globals.OPERATORS).anyMatch(x -> x == ast.getType()) )
		{
			uniqueOperators.add(ast.getText());
			++operators;
		}
		// token is a operand
		else
		{
			uniqueOperands.add(ast.getText());
			++operands;
		}
	}
}
