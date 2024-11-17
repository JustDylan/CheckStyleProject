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

public class NumCommentsCheckTest {

	@Test
	public void isCommentNodesRequiredTest()
	{
		NumCommentsCheck check = new NumCommentsCheck();
		
		assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void getTokensTest()
	{
		NumCommentsCheck check = new NumCommentsCheck();
		
		// expected tokens
		int[] expectedTokens = new int[] { TokenTypes.COMMENT_CONTENT };
		
		assertArrayEquals(expectedTokens, check.getDefaultTokens());
		assertArrayEquals(new int[0], check.getRequiredTokens());
		assertArrayEquals(expectedTokens, check.getAcceptableTokens());
	}
	
	@Test
	public void beginTreeTest()
	{
		NumCommentsCheck check = new NumCommentsCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		// count starts at 0 when no tokens are visited
		check.beginTree(ast);
		assertEquals(0, check.getCount());
	}
	
	@Test
	public void finishTreeTest()
	{
		NumCommentsCheck check = spy(new NumCommentsCheck());
		
		DetailAST ast = mock(DetailAST.class);
		
		int count = check.getCount();
		
		// expected message
		String msg = "Number of comments:" + count;
		
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
		NumCommentsCheck check = new NumCommentsCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		int initialCount = check.getCount();
		
		// make sure count is incremented at visit
		check.visitToken(ast);
		assertEquals(initialCount+1, check.getCount());

	}
}
