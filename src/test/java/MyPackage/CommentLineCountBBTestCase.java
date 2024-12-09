package MyPackage;

public class CommentLineCountBBTestCase {

	// 1
	void helloWorld()
	{
		String test1 = "// not a comment";
		String test2 = "/* not a comment */";
		/*2 */ /*2 */ /*2 */
		
		// // 3
		
		int test3 = /* 4 */ 2;
		
		/* 5
		 * 6
		 * 7
		 8 */
	}
}
// 9