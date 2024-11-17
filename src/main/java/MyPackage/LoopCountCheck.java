package MyPackage;
import com.puppycrawl.tools.checkstyle.api.*;

public class LoopCountCheck extends AbstractCheck {
	private int count = 0;
	
	public int getCount()
	{
		return count;
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {
				TokenTypes.LITERAL_FOR,
				TokenTypes.DO_WHILE,
				TokenTypes.LITERAL_WHILE,
				};
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
		log(rootAst.getLineNo(), "Total number of looping statements:" + count);
	}

	@Override
	public void visitToken(DetailAST ast) {
		++count;
	}
}
