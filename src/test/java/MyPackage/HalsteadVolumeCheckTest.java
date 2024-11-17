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

public class HalsteadVolumeCheckTest {

	@Test
	public void getTokensTest()
	{
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		
		// expected tokens
		int[] expectedTokens = Globals.OPERANDS_OPERATORS;
		
		assertArrayEquals(expectedTokens, check.getDefaultTokens());
		assertArrayEquals(new int[0], check.getRequiredTokens());
		assertArrayEquals(expectedTokens, check.getAcceptableTokens());
	}
	
	@Test
	public void beginTreeTest()
	{
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		// count starts at 0 when no tokens are visited
		check.beginTree(ast);
		assertEquals(0, check.getLength());
		assertEquals(0, check.getVocabulary());
	}
	
	@Test
	public void finishTreeTest()
	{
		HalsteadVolumeCheck check = spy(new HalsteadVolumeCheck());
		
		DetailAST ast = mock(DetailAST.class);
		
		double volume = 0;
		
		// expected message
		String msg = "Halstead Volume:" + volume;
		
		// stub log method
		doNothing().when(check).log(anyInt(), anyString());
		
		doReturn(0).when(ast).getLineNo();
		
		check.finishTree(ast);
		
		// make sure the correct check result was logged
		verify(check).log(0, msg);
		
		// check branch where vocabulary is non-zero
		check.setVocabularyOne();
		volume = check.getLength() * Math.log(check.getVocabulary())/Math.log(2);
		
		// expected message
		msg = "Halstead Volume:" + volume;
		
		// stub log method
		doNothing().when(check).log(anyInt(), anyString());
		
		doReturn(1).when(ast).getLineNo();
		
		check.finishTree(ast);
		
		// make sure the correct check result was logged
		verify(check).log(1, msg);
	}
	
	@Test
	public void visitTokenTest()
	{
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		
		DetailAST ast = mock(DetailAST.class);
		doReturn("operator/operand").when(ast).getText();
		
		int initialLength = check.getLength();
		int initialVocabulary = check.getVocabulary();
		
		// make sure vocabulary is incremented once at multiple visits of same operator/operand
		check.visitToken(ast);
		check.visitToken(ast);
		assertEquals(initialLength+2, check.getLength());
		assertEquals(initialVocabulary+1, check.getVocabulary());

	}
}
