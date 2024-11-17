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

public class NumOperatorsCheckTest {

	@Test
	public void getTokensTest()
	{
		NumOperatorsCheck check = new NumOperatorsCheck();
		
		// expected tokens
		int[] expectedTokens = Globals.OPERATORS;
		
		assertArrayEquals(expectedTokens, check.getDefaultTokens());
		assertArrayEquals(new int[0], check.getRequiredTokens());
		assertArrayEquals(expectedTokens, check.getAcceptableTokens());
	}
	
	@Test
	public void beginTreeTest()
	{
		NumOperatorsCheck check = new NumOperatorsCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		// operator count starts at 0 when no tokens are visited
		check.beginTree(ast);
		assertEquals(0, check.getCount());
	}
	
	@Test
	public void finishTreeTest()
	{
		NumOperatorsCheck check = spy(new NumOperatorsCheck());
		
		DetailAST ast = mock(DetailAST.class);
		
		int count = check.getCount();
		
		// expected message
		String msg = "Number of operators:" + count;
		
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
		NumOperatorsCheck check = new NumOperatorsCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		int initialCount = check.getCount();
		
		// make sure operator count is incremented at visit
		check.visitToken(ast);
		assertEquals(initialCount+1, check.getCount());

	}
}
