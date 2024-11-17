package MyPackage;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.Arrays;
import java.util.stream.IntStream;

// class containing the lists of operator and operand tokens shared across the check classes
public class Globals {
	public static int[] OPERATORS = new int[] {
			//Halstead operators
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
			TokenTypes.LITERAL_FOR,
			TokenTypes.DO_WHILE,
			TokenTypes.LITERAL_WHILE,
	};
	
	public static int[] OPERANDS = new int[] {
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
	
	// combination of both OPERATORS and OPERANDS
	public static int[] OPERANDS_OPERATORS = IntStream.concat(Arrays.stream(OPERATORS), Arrays.stream(OPERANDS)).toArray();
}
