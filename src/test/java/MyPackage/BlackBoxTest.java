package MyPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

class BlackBoxTest {

	static final String path = "src/test/java/MyPackage/";
	
	@Test
	public void halsteadLengthTest()
	{
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "HalsteadLengthBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Halstead Length:106", result);
	}
	
	@Test
	public void halsteadVocabularyTest()
	{
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "HalsteadVocabularyBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Halstead Vocabulary:35", result);
	}
	
	@Test
	public void halsteadVolumeTest()
	{
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "HalsteadVolumeBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Halstead Volume:378.92251761995067", result);
	}
	
	@Test
	public void halsteadDifficultyTest()
	{
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "HalsteadDifficultyBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Halstead Difficulty:17.0", result);
	}
	
	@Test
	public void halsteadEffortTest()
	{
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "HalsteadEffortBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Halstead Effort:3172.8542485580183", result);
	}
	
	@Test
	public void numCommentsTest()
	{
		NumCommentsCheck check = new NumCommentsCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "NumCommentsBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Number of comments:8", result);
	}
	
	@Test
	public void commentLineCountTest()
	{
		CommentLineCountCheck check = new CommentLineCountCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "CommentLineCountBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Comment line count:9", result);
	}
	
	@Test
	public void loopCountTest()
	{
		LoopCountCheck check = new LoopCountCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "LoopCountBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Total number of looping statements:3", result);
	}
	
	@Test
	public void numOperatorsTest()
	{
		NumOperatorsCheck check = new NumOperatorsCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "NumOperatorsBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Number of operators:36", result);
	}
	
	@Test
	public void numOperandsTest()
	{
		NumOperandsCheck check = new NumOperandsCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "NumOperandsBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Number of operands:28", result);
	}
	
	@Test
	public void expressionCountTest()
	{
		ExpressionCountCheck check = new ExpressionCountCheck();
		String result = "";
		
		// run check on file
		try
		{
			result = test(check, path + "ExpressionCountBBTestCase.java");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		// verify check logged correct output 
		assertEquals("Total number of expressions is:3", result);
	}
	
	/**
	 * @param check check to run on file
	 * @param filePath path to file that check runs on
	 * @return string containing first message of check output.
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	static String test(AbstractCheck check, String filePath) throws IOException, CheckstyleException {
		// Build File
		//String filePath = "src/test/java/MyPackage/";
		File file = new File(filePath);
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
		
		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// add comment nodes if required by check
		if(check.isCommentNodesRequired())
		{
			JavaParser.appendHiddenCommentNodes(root);
		}
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check,root);
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		// return first message logged
		for(LocalizedMessage lm : check.getMessages()) {
			return lm.getMessage();
		}
		
		// if there's no messages logged return an empty string.
		return "";
	}
	
	// traverses AST tree a and visits any tokens in the acceptable list of check b.
	private static void helper(AbstractCheck b, DetailAST a) {
		while(a != null) {
			int token = a.getType();

			// call check's visit token when it's in its acceptable token list.
			if( IntStream.of(b.getAcceptableTokens()).anyMatch(x -> x == token) )
				b.visitToken(a);
			
			helper(b,a.getFirstChild());
			
			a = a.getNextSibling();
		}
	}
}


