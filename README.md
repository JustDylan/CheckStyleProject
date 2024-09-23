# CheckStyleProject
# Expression Number Check
Counts the total number of expressions by counting EXPR tokens.

# Halstead Length Check
Checks the Halstead length of the program by counting total tokens that are considered Halstead operators or operands.
Tokens considered as operators:
ASSIGN
ARRAY_INIT
SEMI
ARRAY_DECLARATOR
TYPE
LITERAL_NEW
LITERAL_INT
LITERAL_DOUBLE
COMMA
RBRACK
RCURLY
GENERIC_START
GENERIC_END
RPAREN
LPAREN
TYPE_ARGUMENTS
MODIFIERS
FOR_INIT
DOT
POST_INC
SLIST
LITERAL_ELSE
LITERAL_IF
LE
LAND
LAMBDA
LITERAL_RETURN
MINUS
MINUS_ASSIGN
MOD
LT
METHOD_CALL
FINAL
LITERAL_PUBLIC
LITERAL_STATIC
LITERAL_SWITCH
DO_WHILE
LITERAL_WHILE
TYPECAST
INC
DEC

Tokens considered as operands:
LABELED_STAT
VARIABLE_DEF
NUM_INT
EXPR
IDENT
FOR_CONDITION
FOR_ITERATOR
METHOD_REF
NUM_FLOAT
NUM_DOUBLE
NUM_LONG
LITERAL_THIS
CLASS_DEF


# Method Limit Check
Checks if the number of method definitions in a class or interface exceeds a set limit. This is implemented by checking the number of METHOD_DEF tokens in the OBJBLOCK token of each CLASS_DEF and INTERFACE_DEF tokens.
