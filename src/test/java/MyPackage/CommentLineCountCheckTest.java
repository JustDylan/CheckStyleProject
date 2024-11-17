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

public class CommentLineCountCheckTest {

	@Test
	public void isCommentNodesRequiredTest()
	{
		CommentLineCountCheck check = new CommentLineCountCheck();
		
		assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void getTokensTest()
	{
		CommentLineCountCheck check = new CommentLineCountCheck();
		
		// expected tokens
		int[] expectedTokens = new int[] { TokenTypes.COMMENT_CONTENT };
		
		assertArrayEquals(expectedTokens, check.getDefaultTokens());
		assertArrayEquals(new int[0], check.getRequiredTokens());
		assertArrayEquals(expectedTokens, check.getAcceptableTokens());
	}
	
	@Test
	public void beginTreeTest()
	{
		CommentLineCountCheck check = new CommentLineCountCheck();
		
		DetailAST ast = mock(DetailAST.class);
		
		// count starts at 0 when no tokens are visited
		check.beginTree(ast);
		assertEquals(0, check.getCount());
	}
	
	@Test
	public void finishTreeTest()
	{
		CommentLineCountCheck check = spy(new CommentLineCountCheck());
		
		DetailAST ast = mock(DetailAST.class);
		
		int count = check.getCount();
		
		// expected message
		String msg = "Comment line count:" + count;
		
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
		CommentLineCountCheck check = new CommentLineCountCheck();
		
		DetailAST ast = mock(DetailAST.class);
		DetailAST astParent = mock(DetailAST.class);
		
		// test for block comment spanning 3 lines
		doReturn("test1\ntest2\n").when(ast).getText();
		doReturn(astParent).when(ast).getParent();
		doReturn(0).when(ast).getLineNo();
		
		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(astParent).getType();
		
		int initialCount = check.getCount();
		
		// make sure count is incremented at visit
		check.visitToken(ast);
		assertEquals(initialCount+3, check.getCount());

		// test for single line comment
		doReturn("test3\n").when(ast).getText();
		doReturn(astParent).when(ast).getParent();
		doReturn(10).when(ast).getLineNo();
		
		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(astParent).getType();
		
		initialCount = check.getCount();
		
		// make sure count is incremented at visit
		check.visitToken(ast);
		assertEquals(initialCount+1, check.getCount());
	}
}
