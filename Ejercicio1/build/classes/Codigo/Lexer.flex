package Codigo;
import static Codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
H=["_","&","$"]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
%}
%%
int |
if |
else |
while {lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
({L}|{H})({L}|{D}|{H})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
{D}({L}|{D})* {lexeme=yytext(); return ERROR;}
 . {return ERROR2;}