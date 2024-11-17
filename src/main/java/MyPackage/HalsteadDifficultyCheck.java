package MyPackage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;
import java.lang.Math;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadDifficultyCheck extends AbstractCheck {
	private HashSet<String> uniqueOperands = new HashSet<String>();
	private HashSet<String> uniqueOperators = new HashSet<String>();
	private int operands = 0;
	
	public int getUniqueOperands()
	{
		return uniqueOperands.size();
	}
	
	// set operands to one to prevent divide by zero during testing
	public void setOperandsOne()
	{
		uniqueOperands = new HashSet<String>();
		uniqueOperands.add("operand");
		operands = 1;
	}
	
	public int getUniqueOperators()
	{
		return uniqueOperators.size();
	}
	
	public int getOperands()
	{
		return operands;
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
	}
	
	@Override
	public void finishTree(DetailAST rootAst)
	{
		// difficulty is zero when there are no operands
		double difficulty = 0;
		if(uniqueOperands.size() != 0)
			difficulty = uniqueOperators.size()/2 * operands/uniqueOperands.size();
		
		log(rootAst.getLineNo(), "Halstead Difficulty:" + difficulty);
	}

	@Override
	public void visitToken(DetailAST ast) {
		// check if token is a operator
		if( IntStream.of(Globals.OPERATORS).anyMatch(x -> x == ast.getType()) )
		{
			uniqueOperators.add(ast.getText());
		}
		// token is a operand
		else
		{
			uniqueOperands.add(ast.getText());
			++operands;
		}
	}
}
