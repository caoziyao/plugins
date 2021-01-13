package com.zel;

import com.zel.g4.GuaLexer;
import com.zel.g4.GuaParser;
import com.zel.lexer.DirectiveListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        String str = "a = 1 * 2";

        ANTLRInputStream input = new ANTLRInputStream(str);
        GuaLexer lexer = new GuaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GuaParser parser = new GuaParser(tokens);
        ParseTree tree = parser.prog();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new DirectiveListener(), tree);
        
        System.out.println(tree.toStringTree(parser));

        // print LISP-style tree
        //打印规则数
        System.out.println(tree.toStringTree(parser));
    }


}
