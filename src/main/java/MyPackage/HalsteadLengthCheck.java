package MyPackage;
import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadLengthCheck extends AbstractCheck {
	private int count = 0;

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {
				//Halstead operators
				TokenTypes.ASSIGN,
				TokenTypes.ARRAY_INIT,
				TokenTypes.SEMI,
				TokenTypes.ARRAY_DECLARATOR,
				TokenTypes.TYPE,
				TokenTypes.LITERAL_NEW,
				TokenTypes.LITERAL_INT,
				TokenTypes.LITERAL_DOUBLE,
				TokenTypes.COMMA,
				TokenTypes.RBRACK,
				TokenTypes.RCURLY,
				TokenTypes.GENERIC_START,
				TokenTypes.GENERIC_END,
				TokenTypes.RPAREN,
				TokenTypes.LPAREN,
				TokenTypes.TYPE_ARGUMENTS,
				TokenTypes.MODIFIERS,
				TokenTypes.FOR_INIT,
				TokenTypes.DOT,
				TokenTypes.POST_INC,
				TokenTypes.SLIST,
				TokenTypes.LITERAL_ELSE,
				TokenTypes.LITERAL_IF,
				TokenTypes.LE,
				TokenTypes.LAND,
				TokenTypes.LAMBDA,
				TokenTypes.LITERAL_RETURN,
				TokenTypes.MINUS,
				TokenTypes.MINUS_ASSIGN,
				TokenTypes.MOD,
				TokenTypes.LT,
				TokenTypes.METHOD_CALL,
				TokenTypes.FINAL,
				TokenTypes.LITERAL_PUBLIC,
				TokenTypes.LITERAL_STATIC,
				TokenTypes.LITERAL_SWITCH,
				TokenTypes.DO_WHILE,
				TokenTypes.LITERAL_WHILE,
				TokenTypes.TYPECAST,
				TokenTypes.INC,
				TokenTypes.DEC,
				
				//Halstead operands
				TokenTypes.LABELED_STAT,
				TokenTypes.VARIABLE_DEF,
				TokenTypes.NUM_INT,
				TokenTypes.EXPR,
				TokenTypes.IDENT,
				TokenTypes.FOR_CONDITION,
				TokenTypes.FOR_ITERATOR,
				TokenTypes.METHOD_REF,
				TokenTypes.NUM_FLOAT,
				TokenTypes.NUM_DOUBLE,
				TokenTypes.NUM_LONG,
				TokenTypes.LITERAL_THIS,
				TokenTypes.CLASS_DEF
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
		log(rootAst.getLineNo(), "Halstead Length:" + count);
	}

	@Override
	public void visitToken(DetailAST ast) {
		++count;
	}
}
