package MyPackage;
import java.util.HashSet;

import com.puppycrawl.tools.checkstyle.api.*;

public class CommentLineCountCheck extends AbstractCheck {
	// unique line numbers with comments
	private HashSet<Integer> linesWithComments = new HashSet<Integer>();

	public int getCount()
	{
		return linesWithComments.size();
	}
	
	@Override
	public boolean isCommentNodesRequired()
	{
		return true;
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT };
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
		linesWithComments = new HashSet<Integer>();
	}
	
	@Override
	public void finishTree(DetailAST rootAst)
	{
		log(rootAst.getLineNo(), "Comment line count:" + linesWithComments.size());
	}

	@Override
	public void visitToken(DetailAST ast) {
		// count line number starting comment
		int lineNumber = ast.getLineNo();
		linesWithComments.add(lineNumber);
		
		// count line numbers that comment spans
		if(ast.getParent().getType() == TokenTypes.BLOCK_COMMENT_BEGIN)
		{
			for(char c : ast.getText().toCharArray())
			{
				if(c == '\n')
				{
					linesWithComments.add(++lineNumber);
				}
			}
		}
		
	}
}
