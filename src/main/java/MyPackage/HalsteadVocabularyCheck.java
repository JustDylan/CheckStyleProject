package MyPackage;
import java.util.HashSet;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadVocabularyCheck extends AbstractCheck {
	private HashSet<String> vocabulary = new HashSet<String>();
	
	public int getCount()
	{
		return vocabulary.size();
	}

	@Override
	public int[] getAcceptableTokens() {
		return Globals.OPERANDS_OPERATORS;
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
		vocabulary = new HashSet<String>();
	}
	
	@Override
	public void finishTree(DetailAST rootAst)
	{
		log(rootAst.getLineNo(), "Halstead Vocabulary:" + vocabulary.size());
	}

	@Override
	public void visitToken(DetailAST ast) {
		vocabulary.add(ast.getText());
	}
}
