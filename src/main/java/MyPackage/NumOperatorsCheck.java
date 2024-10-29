package MyPackage;
import com.puppycrawl.tools.checkstyle.api.*;

public class NumOperatorsCheck extends AbstractCheck {
	private int count = 0;

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {
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
		log(rootAst.getLineNo(), "Number of operators:" + count);
	}

	@Override
	public void visitToken(DetailAST ast) {
		++count;
	}
}
