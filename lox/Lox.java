// idk what this is 
package com.craftinginterpretors.lox

//bunch of soi boi libraries

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List; 

public class Lox{
    static boolen hadError = false;
    public static void main(String[] args) throws IOException{
       if(args.length > 1){
            System.out.println("Usage: jlox [script]");
            System.exit(64);
       }
       else if(args.length == 1){
            runFile(args[0]);
       }
       else{
            runPropmpt();
       }
    }
    
    // the starting jlox from the command line and providing a path to a lox file will
    // lead to the lox file being executed
    public static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if(hadError) System.exit(65);
    }

    // using the command "jlox" with no arguements will create an interactive prompt that runs code line by line
    //  interactive prompts are called REPLS which can be simplified to Read -> Evaluate -> Print -> Loop
    //  the readLine() function will only return null when the EOF character is read whihch is usually acheived by the user
    //  entering in the CTRL-C command
    public static void runPropmpt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input)
        
        // run a loop that will read in each line stop if the user doesnt input anything
        for (;;){
            System.out.print("> ");
            String line = reader.readLine();
            if(line == null) break;
            run(line);
            hadError = false;
        }
    }

    private static void run(String source){
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // print tokens out for now
        for(Token token: tokens){
            System.out.println(token);
        }
    }

    // Error handling
    static void error(int line, String message){
        report(line, "", message);
    }

    private static void report(int line, String where, String message){
        System.err.println(
        "[line " + line + "] Error" + where, ": ", + message);
        hadError = true;
    }
}

