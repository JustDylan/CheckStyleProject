package MyPackage;
import com.puppycrawl.tools.checkstyle.api.*;

public class NumCommentsCheck extends AbstractCheck {
	private int count = 0;

	public int getCount()
	{
		return this.count;
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
		count = 0;
	}
	
	@Override
	public void finishTree(DetailAST rootAst)
	{
		log(rootAst.getLineNo(), "Number of comments:" + count);
	}

	@Override
	public void visitToken(DetailAST ast) {
		++count;
	}
}
