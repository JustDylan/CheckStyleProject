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

public class ExpresssionCountCheckTest {

	@Test
	public void getTokensTest()
	{
		ExpressionCountCheck check = new ExpressionCountCheck();
		
		// expected tokens
		int[] expectedTokens = new int[] {TokenTypes.EXPR};
		
		assertArrayEquals(expectedTokens, check.getDefaultTokens());
		assertArrayEquals(new int[0], check.getRequiredTokens());
		assertArrayEquals(expectedTokens, check.getAcceptableTokens());
	}
	
	@Test
	public void beginTreeTest()
	{
		ExpressionCountCheck check = new ExpressionCountCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		// expression count starts at 0 when no tokens are visited
		check.beginTree(ast);
		assertEquals(0, check.getCount());
	}
	
	@Test
	public void finishTreeTest()
	{
		ExpressionCountCheck check = spy(new ExpressionCountCheck());
		
		DetailAST ast = mock(DetailAST.class);
		
		int count = check.getCount();
		
		// expected message
		String msg = "Total number of expressions is:" + count;
		
		// stub log method
		doNothing().when(check).log(anyInt(), anyString());
		
		doReturn(0).when(ast).getLineNo();
		
		check.finishTree(ast);
		
		// make sure the correct check result was logged
		verify(check).log(0, msg);
	}
	
	@Test
	public void visitTokenTest()
	{
		ExpressionCountCheck check = new ExpressionCountCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		int initialCount = check.getCount();
		
		// make sure expression count is incremented at visit
		check.visitToken(ast);
		assertEquals(initialCount+1, check.getCount());

	}
}
