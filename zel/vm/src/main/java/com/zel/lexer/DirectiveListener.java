package com.zel.lexer;

import com.zel.g4.GuaBaseListener;
import com.zel.g4.GuaParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class DirectiveListener extends GuaBaseListener {

    @Override
    public void exitPrintExpr(GuaParser.PrintExprContext ctx) {
        System.out.println("return\n");
    }

    @Override
    public void exitAssign(GuaParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        System.out.println("= " + id);
    }

    @Override
    public void exitMulDiv(GuaParser.MulDivContext ctx) {
        if (ctx.op.getType() == GuaParser.MUL) {
            System.out.println("MUL");
        } else {
            System.out.println("DIV");
        }
    }

    @Override
    public void exitAddSub(GuaParser.AddSubContext ctx) {
        if (ctx.op.getType() == GuaParser.ADD) {
            System.out.println("ADD");
        } else {
            System.out.println("SUB");
        }
    }

    @Override
    public void exitId(GuaParser.IdContext ctx) {
        System.out.println("id " + ctx.ID().getText());
    }

    @Override
    public void exitInt(GuaParser.IntContext ctx) {
        System.out.println("int " + ctx.INT().getText());
    }
}
