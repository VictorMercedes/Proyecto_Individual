package Codigo;
import static Codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
a=[a]
H=["_","&","$"]+
D=[0-9]{5}
E=[1,0]{2}
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
({E})({D})* {lexeme=yytext(); return Patron;}
 . {return ERROR;}