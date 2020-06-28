package lexers;
import tokens.Tokens;
import static tokens.Tokens.*;
%%
%caseless
%public
%class Lexer
%type Tokens
I = (interger|char|real)
C = ([I][N][T][E][R][G][E][R]|[C][H][A][R]|[R][E][A][L])
%{
    public String identifier;
%}
%%
{C} {identifier=yytext(); return CapitalizedIdentifier;}
{I} {identifier=yytext(); return Identifier;}
 . {return ERROR;}