package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;
import java.util.HasMap;
import java.util.Map;

import static com.craftinginterpreters.lox.TokenType.*; 

class Lexer{
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Lexer(String source){
        this.source = source;
    }

    List<Token> scanTokens() {
        while (!isAtEnd()){
            // beginning of next lexeme
            start = current;
            scanToken();
        }
        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }
    private boolean isAtEnd(){
        return current >= source.length();
    }
}

