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
		int[] expectedTokens = new int[] {
				// operators
				TokenTypes.SEMI,
				TokenTypes.OBJBLOCK,
				TokenTypes.SLIST,
				TokenTypes.TYPE,
				TokenTypes.PARAMETERS,
				TokenTypes.COMMA,
				TokenTypes.ARRAY_DECLARATOR,
				TokenTypes.INDEX_OP,
				TokenTypes.LT,
				TokenTypes.ASSIGN,
				TokenTypes.MINUS,
				TokenTypes.LE,
				TokenTypes.POST_INC,
				TokenTypes.LITERAL_RETURN,
				TokenTypes.LITERAL_IF,
				TokenTypes.LITERAL_FOR
		};
		
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
		assertEquals(0, getPrivateField(check, "count"));
	}
	
	@Test
	public void finishTreeTest()
	{
		NumOperatorsCheck check = spy(new NumOperatorsCheck());
		
		DetailAST ast = mock(DetailAST.class);
		
		// stub log method
		doNothing().when(check).log(0, "Number of operators:0");
		
		doReturn(0).when(ast).getLineNo();
		
		check.finishTree(ast);
		
		// make sure the correct check result was logged
		verify(check).log(0, "Number of operators:0");
	}
	
	@Test
	public void visitTokenTest()
	{
		NumOperatorsCheck check = new NumOperatorsCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		int initialCount = getPrivateField(check, "count");
		
		// make sure operator count is incremented at visit
		check.visitToken(ast);
		assertEquals(initialCount+1, getPrivateField(check, "count"));

	}
	
	private static int getPrivateField(Object check, String fieldName) {
        try {
        	Field field = check.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (int) field.get(check);
        } catch (Exception e) {
            fail("Error accessing field: " + e.getMessage());
            return -1;
        }
    }
}
