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

public class HalsteadVocabularyCheckTest {

	@Test
	public void getTokensTest()
	{
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		
		// expected tokens
		int[] expectedTokens = Globals.OPERANDS_OPERATORS;
		
		assertArrayEquals(expectedTokens, check.getDefaultTokens());
		assertArrayEquals(new int[0], check.getRequiredTokens());
		assertArrayEquals(expectedTokens, check.getAcceptableTokens());
	}
	
	@Test
	public void beginTreeTest()
	{
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		// count starts at 0 when no tokens are visited
		check.beginTree(ast);
		assertEquals(0, check.getCount());
	}
	
	@Test
	public void finishTreeTest()
	{
		HalsteadVocabularyCheck check = spy(new HalsteadVocabularyCheck());
		
		DetailAST ast = mock(DetailAST.class);
		
		int count = check.getCount();
		
		// expected message
		String msg = "Halstead Vocabulary:" + count;
		
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
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		
		DetailAST ast = mock(DetailAST.class);
		doReturn("operator/operand").when(ast).getText();
		
		int initialCount = check.getCount();
		
		// make sure vocabulary is incremented once at multiple visits of same operator/operand
		check.visitToken(ast);
		check.visitToken(ast);
		assertEquals(initialCount+1, check.getCount());

	}
}
