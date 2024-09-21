package MyPackage;
import com.puppycrawl.tools.checkstyle.api.*;

public class ExpressionCountCheck extends AbstractCheck {
	private int count = 0;

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.EXPR };
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
		log(rootAst.getLineNo(), "Total number of expressions is:" + count);
	}

	@Override
	public void visitToken(DetailAST ast) {
		++count;
	}
}
