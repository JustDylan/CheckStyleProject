# CheckStyleProject
### Operator tokens used in checks:
```
SEMI, OBJBLOCK, SLIST, TYPE, PARAMETERS, COMMA, ARRAY_DECLARATOR, INDEX_OP, LT, ASSIGN, MINUS, LE, POST_INC, LITERAL_RETURN, LITERAL_IF, LITERAL_FOR, LITERAL_NEW, LITERAL_BREAK, LITERAL_ELSE, LAMBDA, TYPECAST, INC, DEC, POST_DEC, MOD, LITERAL_PUBLIC, LITERAL_STATIC, LITERAL_SWITCH, METHOD_CALL, DO_WHILE, LITERAL_WHILE, TYPECAST
```

### Operand tokens used in checks:
```
LABELED_STAT, VARIABLE_DEF, NUM_INT, EXPR, IDENT, FOR_CONDITION, FOR_ITERATOR, METHOD_REF, NUM_FLOAT, NUM_DOUBLE, NUM_LONG, LITERAL_THIS, CLASS_DEF
```
# Category A Checks

### Halstead Length Check
Checks the Halstead length of the program by counting total tokens that are considered Halstead operators or operands.
Uses tokens from the operator and operand lists.

### Halstead Vocabulary Check
Computes the Halstead vocabulary of the program by counting the total number of unique operators and operands.
Uses tokens from the operator and operand lists.

### Halstead Volume Check
Computes the Halstead volume of the program by multiplying the Halstead length by the log base 2 of the Halstead vocabulary of the program.
Uses Tokens from the operator and operand lists.

### Halstead Difficulty Check
Computes the Halstead difficulty of the program by taking half of the unique operators and multiplying it by the total number of operands divided by the number of unique operands in the program.
Uses tokens from the operator and operand lists.

### Halsead Effort Check
Computes the Halstead effort of the program by multiplying the Halstead difficulty by the Halstead volume of the program.
Uses tokens from the operator and operand lists.

# Category B Checks

### Expression Number Check
Counts the total number of expressions by counting EXPR tokens.

### Comment Line Count Check
Counts the total number of lines that contain comments in the program. Uses the COMMENT_CONTENT and BLOCK_COMMENT_BEGIN tokens.

### Looping Statement Count Check
Counts the total number of looping statements present in the program. Uses the LITERAL_FOR, DO_WHILE, and LITERAL_WHILE tokens.

### Comment Number Check
Counts the total number of comments by counting COMMENT_CONTENT tokens.

### Operand Number Check
Counts the total number of operands by counting tokens in the operands list.

### Operator Number Check
Counts the total number of operators by counting tokens in the operators list.

# Other Checks

### Method Limit Check
Checks if the number of method definitions in a class or interface exceeds a set limit. This is implemented by checking the number of METHOD_DEF tokens in the OBJBLOCK token of each CLASS_DEF and INTERFACE_DEF tokens.
