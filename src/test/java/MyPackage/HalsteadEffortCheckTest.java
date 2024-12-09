package MyPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import org.mockito.MockedStatic;
import static org.mockito.ArgumentMatchers.*;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

public class HalsteadEffortCheckTest {

	@Test
	public void getTokensTest()
	{
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		
		// expected tokens
		int[] expectedTokens = Globals.OPERANDS_OPERATORS;
		
		assertArrayEquals(expectedTokens, check.getDefaultTokens());
		assertArrayEquals(new int[0], check.getRequiredTokens());
		assertArrayEquals(expectedTokens, check.getAcceptableTokens());
	}
	
	@Test
	public void beginTreeTest()
	{
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		// operator count starts at 0 when no tokens are visited
		check.beginTree(ast);
		assertEquals(0, check.getUniqueOperands());
		assertEquals(0, check.getUniqueOperators());
		assertEquals(0, check.getOperands());
		assertEquals(0, check.getOperators());
	}
	
	@Test
	public void finishTreeTest()
	{
		HalsteadEffortCheck check = spy(new HalsteadEffortCheck());
		
		DetailAST ast = mock(DetailAST.class);
		
		double difficulty = 0;
		double volume = 0;
		
		// expected message
		String msg = "Halstead Effort:" + difficulty*volume;
		
		// stub log method
		doNothing().when(check).log(anyInt(), anyString());
		
		doReturn(0).when(ast).getLineNo();
		
		check.finishTree(ast);
		
		// make sure the correct check result was logged
		verify(check).log(0, msg);
		
		// set operators to go down other branch
		check.setOperandsOne();
		doReturn(1).when(ast).getLineNo();
		difficulty = check.getUniqueOperators()/2.0 * check.getOperands()/check.getUniqueOperands();
		volume = (check.getOperands()+check.getOperators()) * Math.log(check.getUniqueOperands()+check.getUniqueOperators())/Math.log(2);
		msg = "Halstead Effort:" + difficulty*volume;
		
		check.finishTree(ast);
		
		verify(check).log(1, msg);
	}
	
	@Test
	public void visitTokenTest()
	{
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		// test operator branch
		doReturn(Globals.OPERATORS[0]).when(ast).getType();
		doReturn("operator").when(ast).getText();
		
		int initialUniqueOperators = check.getUniqueOperators();
		int initialUniqueOperands = check.getUniqueOperands();
		int initialOperands = check.getOperands();
		int initialOperators = check.getOperators();
		
		// make sure unique operator count is incremented once on same operator and other counts remain the same
		check.visitToken(ast);
		check.visitToken(ast);
		assertEquals(initialUniqueOperators+1, check.getUniqueOperators());
		assertEquals(initialUniqueOperands, check.getUniqueOperands());
		assertEquals(initialOperands, check.getOperands());
		assertEquals(initialOperators+2, check.getOperators());
		
		// test operand branch
		doReturn(Globals.OPERANDS[0]).when(ast).getType();
		doReturn("operand").when(ast).getText();
		
		// check if repeated operands only increments total operands twice and unique operands once
		check.visitToken(ast);
		check.visitToken(ast);
		assertEquals(initialUniqueOperators+1, check.getUniqueOperators());
		assertEquals(initialUniqueOperands+1, check.getUniqueOperands());
		assertEquals(initialOperands+2, check.getOperands());
		assertEquals(initialOperators+2, check.getOperators());
	}
}
