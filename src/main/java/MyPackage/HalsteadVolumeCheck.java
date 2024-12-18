package MyPackage;
import java.util.HashSet;
import java.lang.Math;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadVolumeCheck extends AbstractCheck {
	private HashSet<String> vocabulary = new HashSet<String>();
	private int length = 0;
	
	public int getVocabulary()
	{
		return vocabulary.size();
	}
	
	public int getLength()
	{
		return length;
	}
	
	// sets vocabulary to one to test other branches
	public void setVocabularyOne()
	{
		vocabulary.add("token");
		++length;
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
		length = 0;
	}
	
	@Override
	public void finishTree(DetailAST rootAst)
	{
		// volume is 0 for empty vocabulary
		double volume = 0;
		if(vocabulary.size() != 0)
			volume = length * Math.log(vocabulary.size())/Math.log(2);
		
		log(rootAst.getLineNo(), "Halstead Volume:" + volume);
	}

	@Override
	public void visitToken(DetailAST ast) {
		vocabulary.add(ast.getText());
		++length;
	}
}
