// Generated from E:\My Code\java\cpu32e2_assembler4\src\assembler\grammar\assembler.g4 by ANTLR 4.5.3
package assembler.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link assemblerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface assemblerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link assemblerParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(assemblerParser.InputContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(assemblerParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#codeLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeLabel(assemblerParser.CodeLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#masterLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterLine(assemblerParser.MasterLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd(assemblerParser.EndContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(assemblerParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Element}
	 * labeled alternative in {@link assemblerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(assemblerParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Binary}
	 * labeled alternative in {@link assemblerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary(assemblerParser.BinaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link assemblerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(assemblerParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link assemblerParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(assemblerParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link assemblerParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(assemblerParser.ParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#origin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrigin(assemblerParser.OriginContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#align}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlign(assemblerParser.AlignContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#functionBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBlock(assemblerParser.FunctionBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#functionLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionLine(assemblerParser.FunctionLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#constBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstBlock(assemblerParser.ConstBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#constLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstLine(assemblerParser.ConstLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#constDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(assemblerParser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#dataBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataBlock(assemblerParser.DataBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#dataLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataLine(assemblerParser.DataLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#dataDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataDecl(assemblerParser.DataDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#dataEntries}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataEntries(assemblerParser.DataEntriesContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#dataArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataArray(assemblerParser.DataArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#dataValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataValue(assemblerParser.DataValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeR0}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeR0(assemblerParser.TypeR0Context ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeSR1}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSR1(assemblerParser.TypeSR1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeRS1}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeRS1(assemblerParser.TypeRS1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeI1}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeI1(assemblerParser.TypeI1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeIR}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIR(assemblerParser.TypeIRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeR2A}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeR2A(assemblerParser.TypeR2AContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeI2}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeI2(assemblerParser.TypeI2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeR2B}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeR2B(assemblerParser.TypeR2BContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeR3}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeR3(assemblerParser.TypeR3Context ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeI3A}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeI3A(assemblerParser.TypeI3AContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeI3B}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeI3B(assemblerParser.TypeI3BContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeR4}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeR4(assemblerParser.TypeR4Context ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeBRRGI}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBRRGI(assemblerParser.TypeBRRGIContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeBRRGO}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBRRGO(assemblerParser.TypeBRRGOContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeBRPCR}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBRPCR(assemblerParser.TypeBRPCRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeLDRGI}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeLDRGI(assemblerParser.TypeLDRGIContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeLDRGO}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeLDRGO(assemblerParser.TypeLDRGOContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeLDPCR}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeLDPCR(assemblerParser.TypeLDPCRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeSTRGI}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSTRGI(assemblerParser.TypeSTRGIContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeSTRGO}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSTRGO(assemblerParser.TypeSTRGOContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeSTPCR}
	 * labeled alternative in {@link assemblerParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSTPCR(assemblerParser.TypeSTPCRContext ctx);
	/**
	 * Visit a parse tree produced by {@link assemblerParser#imm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm(assemblerParser.ImmContext ctx);
}