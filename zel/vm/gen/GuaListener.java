// Generated from E:/working/mycode/plugins/zel/vm/src/main/java/com/zel/g4\Gua.g4 by ANTLR 4.9

 package com.zel.g4;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GuaParser}.
 */
public interface GuaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GuaParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(GuaParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuaParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(GuaParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link GuaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(GuaParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link GuaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(GuaParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link GuaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(GuaParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link GuaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(GuaParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parens}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(GuaParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(GuaParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(GuaParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(GuaParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(GuaParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(GuaParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(GuaParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(GuaParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(GuaParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link GuaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(GuaParser.IntContext ctx);
}