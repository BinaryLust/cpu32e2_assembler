// Generated from E:\My Code\java\cpu32e2_assembler4\src\assembler\grammar\assembler.g4 by ANTLR 4.5.3
package assembler.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class assemblerParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ALIGN=1, ORIGIN=2, FUNCTION=3, ENDFUNCTION=4, CONST=5, ENDCONST=6, DATA=7, 
		ENDDATA=8, DATATYPE=9, OR0=10, OSR1=11, ORS1=12, OI1=13, IR=14, RI2=15, 
		OR2=16, RI3=17, OI3=18, OR4=19, BR=20, LD=21, ST=22, REG=23, SYSREG=24, 
		MTYPE=25, INCTYPE=26, COND=27, LABEL=28, ID=29, DNUM=30, HNUM=31, BNUM=32, 
		PLUS=33, MINUS=34, NOT=35, MULTIPLY=36, DIVIDE=37, MODULUS=38, SHIFTLEFT=39, 
		SHIFTRIGHT=40, SHIFTRIGHTARITH=41, AND=42, XOR=43, OR=44, COMMA=45, LBRACKET=46, 
		RBRACKET=47, EQUALS=48, LPAREN=49, RPAREN=50, NEWLINE=51, CHAR=52, STRING=53, 
		SINGLE_CMNT=54, COMMENT=55, WHITESPACE=56, FILEEND=57;
	public static final int
		RULE_input = 0, RULE_block = 1, RULE_codeLabel = 2, RULE_masterLine = 3, 
		RULE_end = 4, RULE_value = 5, RULE_expression = 6, RULE_atom = 7, RULE_origin = 8, 
		RULE_align = 9, RULE_functionBlock = 10, RULE_functionLine = 11, RULE_constBlock = 12, 
		RULE_constLine = 13, RULE_constDecl = 14, RULE_dataBlock = 15, RULE_dataLine = 16, 
		RULE_dataDecl = 17, RULE_dataEntries = 18, RULE_dataArray = 19, RULE_dataValue = 20, 
		RULE_inst = 21, RULE_imm = 22;
	public static final String[] ruleNames = {
		"input", "block", "codeLabel", "masterLine", "end", "value", "expression", 
		"atom", "origin", "align", "functionBlock", "functionLine", "constBlock", 
		"constLine", "constDecl", "dataBlock", "dataLine", "dataDecl", "dataEntries", 
		"dataArray", "dataValue", "inst", "imm"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'align'", "'origin'", "'function'", "'endfunction'", "'const'", 
		"'endconst'", "'data'", "'enddata'", null, null, "'ssr'", "'lsr'", "'int'", 
		"'iret'", null, "'not'", null, "'mui'", null, null, "'ld'", "'st'", null, 
		null, null, null, null, null, null, null, null, null, "'+'", "'-'", "'~'", 
		"'*'", "'/'", "'%'", "'<<'", "'>>'", "'>>>'", "'&'", "'^'", "'|'", "','", 
		"'['", "']'", "'='", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ALIGN", "ORIGIN", "FUNCTION", "ENDFUNCTION", "CONST", "ENDCONST", 
		"DATA", "ENDDATA", "DATATYPE", "OR0", "OSR1", "ORS1", "OI1", "IR", "RI2", 
		"OR2", "RI3", "OI3", "OR4", "BR", "LD", "ST", "REG", "SYSREG", "MTYPE", 
		"INCTYPE", "COND", "LABEL", "ID", "DNUM", "HNUM", "BNUM", "PLUS", "MINUS", 
		"NOT", "MULTIPLY", "DIVIDE", "MODULUS", "SHIFTLEFT", "SHIFTRIGHT", "SHIFTRIGHTARITH", 
		"AND", "XOR", "OR", "COMMA", "LBRACKET", "RBRACKET", "EQUALS", "LPAREN", 
		"RPAREN", "NEWLINE", "CHAR", "STRING", "SINGLE_CMNT", "COMMENT", "WHITESPACE", 
		"FILEEND"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "assembler.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public assemblerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class InputContext extends ParserRuleContext {
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitInput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46);
				block();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALIGN) | (1L << ORIGIN) | (1L << FUNCTION) | (1L << CONST) | (1L << DATA) | (1L << DATATYPE) | (1L << OR0) | (1L << OSR1) | (1L << ORS1) | (1L << OI1) | (1L << IR) | (1L << RI2) | (1L << OR2) | (1L << RI3) | (1L << OI3) | (1L << OR4) | (1L << BR) | (1L << LD) | (1L << ST) | (1L << LABEL) | (1L << NEWLINE) | (1L << FILEEND))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public FunctionBlockContext functionBlock() {
			return getRuleContext(FunctionBlockContext.class,0);
		}
		public DataBlockContext dataBlock() {
			return getRuleContext(DataBlockContext.class,0);
		}
		public ConstBlockContext constBlock() {
			return getRuleContext(ConstBlockContext.class,0);
		}
		public List<MasterLineContext> masterLine() {
			return getRuleContexts(MasterLineContext.class);
		}
		public MasterLineContext masterLine(int i) {
			return getRuleContext(MasterLineContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			switch (_input.LA(1)) {
			case FUNCTION:
				{
				setState(51);
				functionBlock();
				}
				break;
			case DATA:
				{
				setState(52);
				dataBlock();
				}
				break;
			case CONST:
				{
				setState(53);
				constBlock();
				}
				break;
			case ALIGN:
			case ORIGIN:
			case DATATYPE:
			case OR0:
			case OSR1:
			case ORS1:
			case OI1:
			case IR:
			case RI2:
			case OR2:
			case RI3:
			case OI3:
			case OR4:
			case BR:
			case LD:
			case ST:
			case LABEL:
			case NEWLINE:
			case FILEEND:
				{
				setState(55); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(54);
						masterLine();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(57); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeLabelContext extends ParserRuleContext {
		public TerminalNode LABEL() { return getToken(assemblerParser.LABEL, 0); }
		public CodeLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeLabel; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitCodeLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeLabelContext codeLabel() throws RecognitionException {
		CodeLabelContext _localctx = new CodeLabelContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_codeLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(LABEL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MasterLineContext extends ParserRuleContext {
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public CodeLabelContext codeLabel() {
			return getRuleContext(CodeLabelContext.class,0);
		}
		public InstContext inst() {
			return getRuleContext(InstContext.class,0);
		}
		public DataDeclContext dataDecl() {
			return getRuleContext(DataDeclContext.class,0);
		}
		public OriginContext origin() {
			return getRuleContext(OriginContext.class,0);
		}
		public AlignContext align() {
			return getRuleContext(AlignContext.class,0);
		}
		public MasterLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_masterLine; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitMasterLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MasterLineContext masterLine() throws RecognitionException {
		MasterLineContext _localctx = new MasterLineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_masterLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_la = _input.LA(1);
			if (_la==LABEL) {
				{
				setState(63);
				codeLabel();
				}
			}

			setState(70);
			switch (_input.LA(1)) {
			case OR0:
			case OSR1:
			case ORS1:
			case OI1:
			case IR:
			case RI2:
			case OR2:
			case RI3:
			case OI3:
			case OR4:
			case BR:
			case LD:
			case ST:
				{
				setState(66);
				inst();
				}
				break;
			case DATATYPE:
				{
				setState(67);
				dataDecl();
				}
				break;
			case ORIGIN:
				{
				setState(68);
				origin();
				}
				break;
			case ALIGN:
				{
				setState(69);
				align();
				}
				break;
			case NEWLINE:
			case FILEEND:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(72);
			end();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EndContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(assemblerParser.NEWLINE, 0); }
		public TerminalNode FILEEND() { return getToken(assemblerParser.FILEEND, 0); }
		public EndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndContext end() throws RecognitionException {
		EndContext _localctx = new EndContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_end);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_la = _input.LA(1);
			if ( !(_la==NEWLINE || _la==FILEEND) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode DNUM() { return getToken(assemblerParser.DNUM, 0); }
		public TerminalNode HNUM() { return getToken(assemblerParser.HNUM, 0); }
		public TerminalNode BNUM() { return getToken(assemblerParser.BNUM, 0); }
		public TerminalNode CHAR() { return getToken(assemblerParser.CHAR, 0); }
		public TerminalNode ID() { return getToken(assemblerParser.ID, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << DNUM) | (1L << HNUM) | (1L << BNUM) | (1L << CHAR))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ElementContext extends ExpressionContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ElementContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitBinary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryContext extends ExpressionContext {
		public Token op;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public UnaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case NOT:
				{
				_localctx = new UnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(79);
				((UnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT))) != 0)) ) {
					((UnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(80);
				atom();
				}
				break;
			case ID:
			case DNUM:
			case HNUM:
			case BNUM:
			case LPAREN:
			case CHAR:
				{
				_localctx = new ElementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(104);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(102);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(84);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(85);
						((BinaryContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MODULUS))) != 0)) ) {
							((BinaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(86);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(87);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(88);
						((BinaryContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((BinaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(89);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(90);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(91);
						((BinaryContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SHIFTLEFT) | (1L << SHIFTRIGHT) | (1L << SHIFTRIGHTARITH))) != 0)) ) {
							((BinaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(92);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(93);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(94);
						((BinaryContext)_localctx).op = match(AND);
						setState(95);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(96);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(97);
						((BinaryContext)_localctx).op = match(XOR);
						setState(98);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(99);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(100);
						((BinaryContext)_localctx).op = match(OR);
						setState(101);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(106);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VariableContext extends AtomContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public VariableContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenContext extends AtomContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_atom);
		try {
			setState(112);
			switch (_input.LA(1)) {
			case ID:
			case DNUM:
			case HNUM:
			case BNUM:
			case CHAR:
				_localctx = new VariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				value();
				}
				break;
			case LPAREN:
				_localctx = new ParenContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(LPAREN);
				setState(109);
				expression(0);
				setState(110);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OriginContext extends ParserRuleContext {
		public TerminalNode ORIGIN() { return getToken(assemblerParser.ORIGIN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OriginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_origin; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitOrigin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OriginContext origin() throws RecognitionException {
		OriginContext _localctx = new OriginContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_origin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(ORIGIN);
			setState(115);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlignContext extends ParserRuleContext {
		public TerminalNode ALIGN() { return getToken(assemblerParser.ALIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AlignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_align; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitAlign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlignContext align() throws RecognitionException {
		AlignContext _localctx = new AlignContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_align);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(ALIGN);
			setState(118);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionBlockContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(assemblerParser.FUNCTION, 0); }
		public TerminalNode ID() { return getToken(assemblerParser.ID, 0); }
		public List<EndContext> end() {
			return getRuleContexts(EndContext.class);
		}
		public EndContext end(int i) {
			return getRuleContext(EndContext.class,i);
		}
		public TerminalNode ENDFUNCTION() { return getToken(assemblerParser.ENDFUNCTION, 0); }
		public List<FunctionLineContext> functionLine() {
			return getRuleContexts(FunctionLineContext.class);
		}
		public FunctionLineContext functionLine(int i) {
			return getRuleContext(FunctionLineContext.class,i);
		}
		public FunctionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitFunctionBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionBlockContext functionBlock() throws RecognitionException {
		FunctionBlockContext _localctx = new FunctionBlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_functionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(FUNCTION);
			setState(121);
			match(ID);
			setState(122);
			end();
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALIGN) | (1L << CONST) | (1L << DATA) | (1L << DATATYPE) | (1L << OR0) | (1L << OSR1) | (1L << ORS1) | (1L << OI1) | (1L << IR) | (1L << RI2) | (1L << OR2) | (1L << RI3) | (1L << OI3) | (1L << OR4) | (1L << BR) | (1L << LD) | (1L << ST) | (1L << LABEL) | (1L << NEWLINE) | (1L << FILEEND))) != 0)) {
				{
				{
				setState(123);
				functionLine();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
			match(ENDFUNCTION);
			setState(130);
			end();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionLineContext extends ParserRuleContext {
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public CodeLabelContext codeLabel() {
			return getRuleContext(CodeLabelContext.class,0);
		}
		public InstContext inst() {
			return getRuleContext(InstContext.class,0);
		}
		public DataDeclContext dataDecl() {
			return getRuleContext(DataDeclContext.class,0);
		}
		public AlignContext align() {
			return getRuleContext(AlignContext.class,0);
		}
		public DataBlockContext dataBlock() {
			return getRuleContext(DataBlockContext.class,0);
		}
		public ConstBlockContext constBlock() {
			return getRuleContext(ConstBlockContext.class,0);
		}
		public FunctionLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionLine; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitFunctionLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionLineContext functionLine() throws RecognitionException {
		FunctionLineContext _localctx = new FunctionLineContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_functionLine);
		int _la;
		try {
			setState(143);
			switch (_input.LA(1)) {
			case ALIGN:
			case DATATYPE:
			case OR0:
			case OSR1:
			case ORS1:
			case OI1:
			case IR:
			case RI2:
			case OR2:
			case RI3:
			case OI3:
			case OR4:
			case BR:
			case LD:
			case ST:
			case LABEL:
			case NEWLINE:
			case FILEEND:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				_la = _input.LA(1);
				if (_la==LABEL) {
					{
					setState(132);
					codeLabel();
					}
				}

				setState(138);
				switch (_input.LA(1)) {
				case OR0:
				case OSR1:
				case ORS1:
				case OI1:
				case IR:
				case RI2:
				case OR2:
				case RI3:
				case OI3:
				case OR4:
				case BR:
				case LD:
				case ST:
					{
					setState(135);
					inst();
					}
					break;
				case DATATYPE:
					{
					setState(136);
					dataDecl();
					}
					break;
				case ALIGN:
					{
					setState(137);
					align();
					}
					break;
				case NEWLINE:
				case FILEEND:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(140);
				end();
				}
				break;
			case DATA:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				dataBlock();
				}
				break;
			case CONST:
				enterOuterAlt(_localctx, 3);
				{
				setState(142);
				constBlock();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstBlockContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(assemblerParser.CONST, 0); }
		public ConstDeclContext constDecl() {
			return getRuleContext(ConstDeclContext.class,0);
		}
		public List<EndContext> end() {
			return getRuleContexts(EndContext.class);
		}
		public EndContext end(int i) {
			return getRuleContext(EndContext.class,i);
		}
		public TerminalNode ENDCONST() { return getToken(assemblerParser.ENDCONST, 0); }
		public List<ConstLineContext> constLine() {
			return getRuleContexts(ConstLineContext.class);
		}
		public ConstLineContext constLine(int i) {
			return getRuleContext(ConstLineContext.class,i);
		}
		public ConstBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitConstBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstBlockContext constBlock() throws RecognitionException {
		ConstBlockContext _localctx = new ConstBlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_constBlock);
		int _la;
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				match(CONST);
				setState(146);
				constDecl();
				setState(147);
				end();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(CONST);
				setState(150);
				end();
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << NEWLINE) | (1L << FILEEND))) != 0)) {
					{
					{
					setState(151);
					constLine();
					}
					}
					setState(156);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(157);
				match(ENDCONST);
				setState(158);
				end();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstLineContext extends ParserRuleContext {
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public ConstDeclContext constDecl() {
			return getRuleContext(ConstDeclContext.class,0);
		}
		public ConstLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constLine; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitConstLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstLineContext constLine() throws RecognitionException {
		ConstLineContext _localctx = new ConstLineContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_constLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(162);
				constDecl();
				}
			}

			setState(165);
			end();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstDeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(assemblerParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConstDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitConstDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDeclContext constDecl() throws RecognitionException {
		ConstDeclContext _localctx = new ConstDeclContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_constDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(ID);
			setState(168);
			match(EQUALS);
			setState(169);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataBlockContext extends ParserRuleContext {
		public TerminalNode DATA() { return getToken(assemblerParser.DATA, 0); }
		public DataDeclContext dataDecl() {
			return getRuleContext(DataDeclContext.class,0);
		}
		public List<EndContext> end() {
			return getRuleContexts(EndContext.class);
		}
		public EndContext end(int i) {
			return getRuleContext(EndContext.class,i);
		}
		public TerminalNode ENDDATA() { return getToken(assemblerParser.ENDDATA, 0); }
		public List<DataLineContext> dataLine() {
			return getRuleContexts(DataLineContext.class);
		}
		public DataLineContext dataLine(int i) {
			return getRuleContext(DataLineContext.class,i);
		}
		public DataBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitDataBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataBlockContext dataBlock() throws RecognitionException {
		DataBlockContext _localctx = new DataBlockContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_dataBlock);
		int _la;
		try {
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				match(DATA);
				setState(172);
				dataDecl();
				setState(173);
				end();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(DATA);
				setState(176);
				end();
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALIGN) | (1L << DATATYPE) | (1L << NEWLINE) | (1L << FILEEND))) != 0)) {
					{
					{
					setState(177);
					dataLine();
					}
					}
					setState(182);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(183);
				match(ENDDATA);
				setState(184);
				end();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataLineContext extends ParserRuleContext {
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public DataDeclContext dataDecl() {
			return getRuleContext(DataDeclContext.class,0);
		}
		public AlignContext align() {
			return getRuleContext(AlignContext.class,0);
		}
		public DataLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataLine; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitDataLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataLineContext dataLine() throws RecognitionException {
		DataLineContext _localctx = new DataLineContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_dataLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			switch (_input.LA(1)) {
			case DATATYPE:
				{
				setState(188);
				dataDecl();
				}
				break;
			case ALIGN:
				{
				setState(189);
				align();
				}
				break;
			case NEWLINE:
			case FILEEND:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(192);
			end();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataDeclContext extends ParserRuleContext {
		public TerminalNode DATATYPE() { return getToken(assemblerParser.DATATYPE, 0); }
		public DataArrayContext dataArray() {
			return getRuleContext(DataArrayContext.class,0);
		}
		public DataEntriesContext dataEntries() {
			return getRuleContext(DataEntriesContext.class,0);
		}
		public TerminalNode ID() { return getToken(assemblerParser.ID, 0); }
		public DataDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitDataDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataDeclContext dataDecl() throws RecognitionException {
		DataDeclContext _localctx = new DataDeclContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_dataDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(DATATYPE);
			setState(196);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(195);
				match(ID);
				}
			}

			setState(200);
			switch (_input.LA(1)) {
			case LBRACKET:
				{
				setState(198);
				dataArray();
				}
				break;
			case EQUALS:
				{
				setState(199);
				dataEntries();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataEntriesContext extends ParserRuleContext {
		public List<DataValueContext> dataValue() {
			return getRuleContexts(DataValueContext.class);
		}
		public DataValueContext dataValue(int i) {
			return getRuleContext(DataValueContext.class,i);
		}
		public DataEntriesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataEntries; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitDataEntries(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataEntriesContext dataEntries() throws RecognitionException {
		DataEntriesContext _localctx = new DataEntriesContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_dataEntries);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(EQUALS);
			setState(203);
			dataValue();
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(204);
				match(COMMA);
				setState(205);
				dataValue();
				}
				}
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataArrayContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public DataArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataArray; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitDataArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataArrayContext dataArray() throws RecognitionException {
		DataArrayContext _localctx = new DataArrayContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_dataArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(LBRACKET);
			setState(212);
			expression(0);
			setState(213);
			match(RBRACKET);
			setState(214);
			match(EQUALS);
			setState(215);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode STRING() { return getToken(assemblerParser.STRING, 0); }
		public DataValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitDataValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataValueContext dataValue() throws RecognitionException {
		DataValueContext _localctx = new DataValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_dataValue);
		try {
			setState(219);
			switch (_input.LA(1)) {
			case ID:
			case DNUM:
			case HNUM:
			case BNUM:
			case PLUS:
			case MINUS:
			case NOT:
			case LPAREN:
			case CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				expression(0);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstContext extends ParserRuleContext {
		public InstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inst; }
	 
		public InstContext() { }
		public void copyFrom(InstContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeI3BContext extends InstContext {
		public TerminalNode OI3() { return getToken(assemblerParser.OI3, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeI3BContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeI3B(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeI3AContext extends InstContext {
		public TerminalNode RI3() { return getToken(assemblerParser.RI3, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeI3AContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeI3A(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeSR1Context extends InstContext {
		public TerminalNode OSR1() { return getToken(assemblerParser.OSR1, 0); }
		public TerminalNode SYSREG() { return getToken(assemblerParser.SYSREG, 0); }
		public TerminalNode REG() { return getToken(assemblerParser.REG, 0); }
		public TypeSR1Context(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeSR1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeLDRGIContext extends InstContext {
		public TerminalNode LD() { return getToken(assemblerParser.LD, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public TerminalNode MTYPE() { return getToken(assemblerParser.MTYPE, 0); }
		public TypeLDRGIContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeLDRGI(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeR4Context extends InstContext {
		public TerminalNode OR4() { return getToken(assemblerParser.OR4, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public TypeR4Context(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeR4(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeLDPCRContext extends InstContext {
		public TerminalNode LD() { return getToken(assemblerParser.LD, 0); }
		public TerminalNode REG() { return getToken(assemblerParser.REG, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode MTYPE() { return getToken(assemblerParser.MTYPE, 0); }
		public TypeLDPCRContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeLDPCR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeRS1Context extends InstContext {
		public TerminalNode ORS1() { return getToken(assemblerParser.ORS1, 0); }
		public TerminalNode REG() { return getToken(assemblerParser.REG, 0); }
		public TerminalNode SYSREG() { return getToken(assemblerParser.SYSREG, 0); }
		public TypeRS1Context(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeRS1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeBRPCRContext extends InstContext {
		public TerminalNode BR() { return getToken(assemblerParser.BR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COND() { return getToken(assemblerParser.COND, 0); }
		public TypeBRPCRContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeBRPCR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeSTPCRContext extends InstContext {
		public TerminalNode ST() { return getToken(assemblerParser.ST, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode REG() { return getToken(assemblerParser.REG, 0); }
		public TerminalNode MTYPE() { return getToken(assemblerParser.MTYPE, 0); }
		public TypeSTPCRContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeSTPCR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeR0Context extends InstContext {
		public TerminalNode OR0() { return getToken(assemblerParser.OR0, 0); }
		public TypeR0Context(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeR0(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeLDRGOContext extends InstContext {
		public TerminalNode LD() { return getToken(assemblerParser.LD, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public ImmContext imm() {
			return getRuleContext(ImmContext.class,0);
		}
		public TerminalNode MTYPE() { return getToken(assemblerParser.MTYPE, 0); }
		public TerminalNode INCTYPE() { return getToken(assemblerParser.INCTYPE, 0); }
		public TypeLDRGOContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeLDRGO(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeBRRGOContext extends InstContext {
		public TerminalNode BR() { return getToken(assemblerParser.BR, 0); }
		public TerminalNode REG() { return getToken(assemblerParser.REG, 0); }
		public ImmContext imm() {
			return getRuleContext(ImmContext.class,0);
		}
		public TerminalNode COND() { return getToken(assemblerParser.COND, 0); }
		public TypeBRRGOContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeBRRGO(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeR3Context extends InstContext {
		public TerminalNode RI3() { return getToken(assemblerParser.RI3, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public TypeR3Context(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeR3(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeSTRGOContext extends InstContext {
		public TerminalNode ST() { return getToken(assemblerParser.ST, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public ImmContext imm() {
			return getRuleContext(ImmContext.class,0);
		}
		public TerminalNode MTYPE() { return getToken(assemblerParser.MTYPE, 0); }
		public TerminalNode INCTYPE() { return getToken(assemblerParser.INCTYPE, 0); }
		public TypeSTRGOContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeSTRGO(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeBRRGIContext extends InstContext {
		public TerminalNode BR() { return getToken(assemblerParser.BR, 0); }
		public TerminalNode REG() { return getToken(assemblerParser.REG, 0); }
		public TerminalNode COND() { return getToken(assemblerParser.COND, 0); }
		public TypeBRRGIContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeBRRGI(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeI1Context extends InstContext {
		public TerminalNode OI1() { return getToken(assemblerParser.OI1, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeI1Context(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeI1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeSTRGIContext extends InstContext {
		public TerminalNode ST() { return getToken(assemblerParser.ST, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public TerminalNode MTYPE() { return getToken(assemblerParser.MTYPE, 0); }
		public TypeSTRGIContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeSTRGI(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeIRContext extends InstContext {
		public TerminalNode IR() { return getToken(assemblerParser.IR, 0); }
		public TerminalNode REG() { return getToken(assemblerParser.REG, 0); }
		public TypeIRContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeIR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeI2Context extends InstContext {
		public TerminalNode RI2() { return getToken(assemblerParser.RI2, 0); }
		public TerminalNode REG() { return getToken(assemblerParser.REG, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeI2Context(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeI2(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeR2BContext extends InstContext {
		public TerminalNode OR2() { return getToken(assemblerParser.OR2, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public TypeR2BContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeR2B(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeR2AContext extends InstContext {
		public TerminalNode RI2() { return getToken(assemblerParser.RI2, 0); }
		public List<TerminalNode> REG() { return getTokens(assemblerParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(assemblerParser.REG, i);
		}
		public TypeR2AContext(InstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitTypeR2A(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstContext inst() throws RecognitionException {
		InstContext _localctx = new InstContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_inst);
		int _la;
		try {
			setState(359);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				_localctx = new TypeR0Context(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
				match(OR0);
				}
				break;
			case 2:
				_localctx = new TypeSR1Context(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				match(OSR1);
				setState(223);
				match(SYSREG);
				setState(224);
				match(COMMA);
				setState(225);
				match(REG);
				}
				break;
			case 3:
				_localctx = new TypeRS1Context(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(226);
				match(ORS1);
				setState(227);
				match(REG);
				setState(228);
				match(COMMA);
				setState(229);
				match(SYSREG);
				}
				break;
			case 4:
				_localctx = new TypeI1Context(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(230);
				match(OI1);
				setState(231);
				expression(0);
				}
				break;
			case 5:
				_localctx = new TypeIRContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(232);
				match(IR);
				setState(233);
				match(LBRACKET);
				setState(234);
				match(REG);
				setState(235);
				match(RBRACKET);
				}
				break;
			case 6:
				_localctx = new TypeR2AContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(236);
				match(RI2);
				setState(237);
				match(REG);
				setState(238);
				match(COMMA);
				setState(239);
				match(REG);
				}
				break;
			case 7:
				_localctx = new TypeI2Context(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(240);
				match(RI2);
				setState(241);
				match(REG);
				setState(242);
				match(COMMA);
				setState(243);
				expression(0);
				}
				break;
			case 8:
				_localctx = new TypeR2BContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(244);
				match(OR2);
				setState(245);
				match(REG);
				setState(246);
				match(COMMA);
				setState(247);
				match(REG);
				}
				break;
			case 9:
				_localctx = new TypeR3Context(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(248);
				match(RI3);
				setState(249);
				match(REG);
				setState(250);
				match(COMMA);
				setState(251);
				match(REG);
				setState(252);
				match(COMMA);
				setState(253);
				match(REG);
				}
				break;
			case 10:
				_localctx = new TypeI3AContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(254);
				match(RI3);
				setState(255);
				match(REG);
				setState(256);
				match(COMMA);
				setState(257);
				match(REG);
				setState(258);
				match(COMMA);
				setState(259);
				expression(0);
				}
				break;
			case 11:
				_localctx = new TypeI3BContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(260);
				match(OI3);
				setState(261);
				match(REG);
				setState(262);
				match(COMMA);
				setState(263);
				match(REG);
				setState(264);
				match(COMMA);
				setState(265);
				expression(0);
				}
				break;
			case 12:
				_localctx = new TypeR4Context(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(266);
				match(OR4);
				setState(267);
				match(REG);
				setState(268);
				match(COMMA);
				setState(269);
				match(REG);
				setState(270);
				match(COMMA);
				setState(271);
				match(REG);
				setState(272);
				match(COMMA);
				setState(273);
				match(REG);
				}
				break;
			case 13:
				_localctx = new TypeBRRGIContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(274);
				match(BR);
				setState(275);
				match(LBRACKET);
				setState(276);
				match(REG);
				setState(277);
				match(RBRACKET);
				setState(280);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(278);
					match(COMMA);
					setState(279);
					match(COND);
					}
				}

				}
				break;
			case 14:
				_localctx = new TypeBRRGOContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(282);
				match(BR);
				setState(283);
				match(LBRACKET);
				setState(284);
				match(REG);
				setState(285);
				imm();
				setState(286);
				match(RBRACKET);
				setState(289);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(287);
					match(COMMA);
					setState(288);
					match(COND);
					}
				}

				}
				break;
			case 15:
				_localctx = new TypeBRPCRContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(291);
				match(BR);
				setState(292);
				match(LBRACKET);
				setState(293);
				expression(0);
				setState(294);
				match(RBRACKET);
				setState(297);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(295);
					match(COMMA);
					setState(296);
					match(COND);
					}
				}

				}
				break;
			case 16:
				_localctx = new TypeLDRGIContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(299);
				match(LD);
				setState(300);
				match(REG);
				setState(301);
				match(COMMA);
				setState(302);
				match(LBRACKET);
				setState(303);
				match(REG);
				setState(304);
				match(RBRACKET);
				setState(305);
				match(COMMA);
				setState(306);
				match(MTYPE);
				}
				break;
			case 17:
				_localctx = new TypeLDRGOContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(307);
				match(LD);
				setState(308);
				match(REG);
				setState(309);
				match(COMMA);
				setState(310);
				match(LBRACKET);
				setState(311);
				match(REG);
				setState(312);
				imm();
				setState(313);
				match(RBRACKET);
				setState(314);
				match(COMMA);
				setState(315);
				match(MTYPE);
				setState(318);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(316);
					match(COMMA);
					setState(317);
					match(INCTYPE);
					}
				}

				}
				break;
			case 18:
				_localctx = new TypeLDPCRContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(320);
				match(LD);
				setState(321);
				match(REG);
				setState(322);
				match(COMMA);
				setState(323);
				match(LBRACKET);
				setState(324);
				expression(0);
				setState(325);
				match(RBRACKET);
				setState(326);
				match(COMMA);
				setState(327);
				match(MTYPE);
				}
				break;
			case 19:
				_localctx = new TypeSTRGIContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(329);
				match(ST);
				setState(330);
				match(LBRACKET);
				setState(331);
				match(REG);
				setState(332);
				match(RBRACKET);
				setState(333);
				match(COMMA);
				setState(334);
				match(REG);
				setState(335);
				match(COMMA);
				setState(336);
				match(MTYPE);
				}
				break;
			case 20:
				_localctx = new TypeSTRGOContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(337);
				match(ST);
				setState(338);
				match(LBRACKET);
				setState(339);
				match(REG);
				setState(340);
				imm();
				setState(341);
				match(RBRACKET);
				setState(342);
				match(COMMA);
				setState(343);
				match(REG);
				setState(344);
				match(COMMA);
				setState(345);
				match(MTYPE);
				setState(348);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(346);
					match(COMMA);
					setState(347);
					match(INCTYPE);
					}
				}

				}
				break;
			case 21:
				_localctx = new TypeSTPCRContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(350);
				match(ST);
				setState(351);
				match(LBRACKET);
				setState(352);
				expression(0);
				setState(353);
				match(RBRACKET);
				setState(354);
				match(COMMA);
				setState(355);
				match(REG);
				setState(356);
				match(COMMA);
				setState(357);
				match(MTYPE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImmContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(assemblerParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(assemblerParser.MINUS, 0); }
		public ImmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof assemblerVisitor ) return ((assemblerVisitor<? extends T>)visitor).visitImm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImmContext imm() throws RecognitionException {
		ImmContext _localctx = new ImmContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_imm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(362);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3;\u016f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\6\2\62"+
		"\n\2\r\2\16\2\63\3\3\3\3\3\3\3\3\6\3:\n\3\r\3\16\3;\5\3>\n\3\3\4\3\4\3"+
		"\5\5\5C\n\5\3\5\3\5\3\5\3\5\5\5I\n\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\5\bU\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\bi\n\b\f\b\16\bl\13\b\3\t\3\t\3\t\3\t\3\t\5\ts\n"+
		"\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\7\f\177\n\f\f\f\16\f\u0082"+
		"\13\f\3\f\3\f\3\f\3\r\5\r\u0088\n\r\3\r\3\r\3\r\5\r\u008d\n\r\3\r\3\r"+
		"\3\r\5\r\u0092\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u009b\n\16"+
		"\f\16\16\16\u009e\13\16\3\16\3\16\3\16\5\16\u00a3\n\16\3\17\5\17\u00a6"+
		"\n\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\7\21\u00b5\n\21\f\21\16\21\u00b8\13\21\3\21\3\21\3\21\5\21\u00bd\n\21"+
		"\3\22\3\22\5\22\u00c1\n\22\3\22\3\22\3\23\3\23\5\23\u00c7\n\23\3\23\3"+
		"\23\5\23\u00cb\n\23\3\24\3\24\3\24\3\24\7\24\u00d1\n\24\f\24\16\24\u00d4"+
		"\13\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\5\26\u00de\n\26\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\5\27\u011b\n\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0124"+
		"\n\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u012c\n\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\5\27\u0141\n\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\5\27\u015f\n\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\5\27\u016a\n\27\3\30\3\30\3\30\3\30\2\3\16\31\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\2\b\4\2\65\65;;\4\2\37\"\66\66\3\2#"+
		"%\3\2&(\3\2#$\3\2)+\u0194\2\61\3\2\2\2\4=\3\2\2\2\6?\3\2\2\2\bB\3\2\2"+
		"\2\nL\3\2\2\2\fN\3\2\2\2\16T\3\2\2\2\20r\3\2\2\2\22t\3\2\2\2\24w\3\2\2"+
		"\2\26z\3\2\2\2\30\u0091\3\2\2\2\32\u00a2\3\2\2\2\34\u00a5\3\2\2\2\36\u00a9"+
		"\3\2\2\2 \u00bc\3\2\2\2\"\u00c0\3\2\2\2$\u00c4\3\2\2\2&\u00cc\3\2\2\2"+
		"(\u00d5\3\2\2\2*\u00dd\3\2\2\2,\u0169\3\2\2\2.\u016b\3\2\2\2\60\62\5\4"+
		"\3\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\3\3\2"+
		"\2\2\65>\5\26\f\2\66>\5 \21\2\67>\5\32\16\28:\5\b\5\298\3\2\2\2:;\3\2"+
		"\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=\65\3\2\2\2=\66\3\2\2\2=\67\3\2\2\2"+
		"=9\3\2\2\2>\5\3\2\2\2?@\7\36\2\2@\7\3\2\2\2AC\5\6\4\2BA\3\2\2\2BC\3\2"+
		"\2\2CH\3\2\2\2DI\5,\27\2EI\5$\23\2FI\5\22\n\2GI\5\24\13\2HD\3\2\2\2HE"+
		"\3\2\2\2HF\3\2\2\2HG\3\2\2\2HI\3\2\2\2IJ\3\2\2\2JK\5\n\6\2K\t\3\2\2\2"+
		"LM\t\2\2\2M\13\3\2\2\2NO\t\3\2\2O\r\3\2\2\2PQ\b\b\1\2QR\t\4\2\2RU\5\20"+
		"\t\2SU\5\20\t\2TP\3\2\2\2TS\3\2\2\2Uj\3\2\2\2VW\f\t\2\2WX\t\5\2\2Xi\5"+
		"\16\b\nYZ\f\b\2\2Z[\t\6\2\2[i\5\16\b\t\\]\f\7\2\2]^\t\7\2\2^i\5\16\b\b"+
		"_`\f\6\2\2`a\7,\2\2ai\5\16\b\7bc\f\5\2\2cd\7-\2\2di\5\16\b\6ef\f\4\2\2"+
		"fg\7.\2\2gi\5\16\b\5hV\3\2\2\2hY\3\2\2\2h\\\3\2\2\2h_\3\2\2\2hb\3\2\2"+
		"\2he\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\17\3\2\2\2lj\3\2\2\2ms\5\f"+
		"\7\2no\7\63\2\2op\5\16\b\2pq\7\64\2\2qs\3\2\2\2rm\3\2\2\2rn\3\2\2\2s\21"+
		"\3\2\2\2tu\7\4\2\2uv\5\16\b\2v\23\3\2\2\2wx\7\3\2\2xy\5\16\b\2y\25\3\2"+
		"\2\2z{\7\5\2\2{|\7\37\2\2|\u0080\5\n\6\2}\177\5\30\r\2~}\3\2\2\2\177\u0082"+
		"\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082"+
		"\u0080\3\2\2\2\u0083\u0084\7\6\2\2\u0084\u0085\5\n\6\2\u0085\27\3\2\2"+
		"\2\u0086\u0088\5\6\4\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008c"+
		"\3\2\2\2\u0089\u008d\5,\27\2\u008a\u008d\5$\23\2\u008b\u008d\5\24\13\2"+
		"\u008c\u0089\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008b\3\2\2\2\u008c\u008d"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0092\5\n\6\2\u008f\u0092\5 \21\2\u0090"+
		"\u0092\5\32\16\2\u0091\u0087\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0090\3"+
		"\2\2\2\u0092\31\3\2\2\2\u0093\u0094\7\7\2\2\u0094\u0095\5\36\20\2\u0095"+
		"\u0096\5\n\6\2\u0096\u00a3\3\2\2\2\u0097\u0098\7\7\2\2\u0098\u009c\5\n"+
		"\6\2\u0099\u009b\5\34\17\2\u009a\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u009c\3\2"+
		"\2\2\u009f\u00a0\7\b\2\2\u00a0\u00a1\5\n\6\2\u00a1\u00a3\3\2\2\2\u00a2"+
		"\u0093\3\2\2\2\u00a2\u0097\3\2\2\2\u00a3\33\3\2\2\2\u00a4\u00a6\5\36\20"+
		"\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8"+
		"\5\n\6\2\u00a8\35\3\2\2\2\u00a9\u00aa\7\37\2\2\u00aa\u00ab\7\62\2\2\u00ab"+
		"\u00ac\5\16\b\2\u00ac\37\3\2\2\2\u00ad\u00ae\7\t\2\2\u00ae\u00af\5$\23"+
		"\2\u00af\u00b0\5\n\6\2\u00b0\u00bd\3\2\2\2\u00b1\u00b2\7\t\2\2\u00b2\u00b6"+
		"\5\n\6\2\u00b3\u00b5\5\"\22\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2"+
		"\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6"+
		"\3\2\2\2\u00b9\u00ba\7\n\2\2\u00ba\u00bb\5\n\6\2\u00bb\u00bd\3\2\2\2\u00bc"+
		"\u00ad\3\2\2\2\u00bc\u00b1\3\2\2\2\u00bd!\3\2\2\2\u00be\u00c1\5$\23\2"+
		"\u00bf\u00c1\5\24\13\2\u00c0\u00be\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c0\u00c1"+
		"\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\5\n\6\2\u00c3#\3\2\2\2\u00c4"+
		"\u00c6\7\13\2\2\u00c5\u00c7\7\37\2\2\u00c6\u00c5\3\2\2\2\u00c6\u00c7\3"+
		"\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00cb\5(\25\2\u00c9\u00cb\5&\24\2\u00ca"+
		"\u00c8\3\2\2\2\u00ca\u00c9\3\2\2\2\u00cb%\3\2\2\2\u00cc\u00cd\7\62\2\2"+
		"\u00cd\u00d2\5*\26\2\u00ce\u00cf\7/\2\2\u00cf\u00d1\5*\26\2\u00d0\u00ce"+
		"\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\'\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5\u00d6\7\60\2\2\u00d6\u00d7\5\16\b"+
		"\2\u00d7\u00d8\7\61\2\2\u00d8\u00d9\7\62\2\2\u00d9\u00da\5\16\b\2\u00da"+
		")\3\2\2\2\u00db\u00de\5\16\b\2\u00dc\u00de\7\67\2\2\u00dd\u00db\3\2\2"+
		"\2\u00dd\u00dc\3\2\2\2\u00de+\3\2\2\2\u00df\u016a\7\f\2\2\u00e0\u00e1"+
		"\7\r\2\2\u00e1\u00e2\7\32\2\2\u00e2\u00e3\7/\2\2\u00e3\u016a\7\31\2\2"+
		"\u00e4\u00e5\7\16\2\2\u00e5\u00e6\7\31\2\2\u00e6\u00e7\7/\2\2\u00e7\u016a"+
		"\7\32\2\2\u00e8\u00e9\7\17\2\2\u00e9\u016a\5\16\b\2\u00ea\u00eb\7\20\2"+
		"\2\u00eb\u00ec\7\60\2\2\u00ec\u00ed\7\31\2\2\u00ed\u016a\7\61\2\2\u00ee"+
		"\u00ef\7\21\2\2\u00ef\u00f0\7\31\2\2\u00f0\u00f1\7/\2\2\u00f1\u016a\7"+
		"\31\2\2\u00f2\u00f3\7\21\2\2\u00f3\u00f4\7\31\2\2\u00f4\u00f5\7/\2\2\u00f5"+
		"\u016a\5\16\b\2\u00f6\u00f7\7\22\2\2\u00f7\u00f8\7\31\2\2\u00f8\u00f9"+
		"\7/\2\2\u00f9\u016a\7\31\2\2\u00fa\u00fb\7\23\2\2\u00fb\u00fc\7\31\2\2"+
		"\u00fc\u00fd\7/\2\2\u00fd\u00fe\7\31\2\2\u00fe\u00ff\7/\2\2\u00ff\u016a"+
		"\7\31\2\2\u0100\u0101\7\23\2\2\u0101\u0102\7\31\2\2\u0102\u0103\7/\2\2"+
		"\u0103\u0104\7\31\2\2\u0104\u0105\7/\2\2\u0105\u016a\5\16\b\2\u0106\u0107"+
		"\7\24\2\2\u0107\u0108\7\31\2\2\u0108\u0109\7/\2\2\u0109\u010a\7\31\2\2"+
		"\u010a\u010b\7/\2\2\u010b\u016a\5\16\b\2\u010c\u010d\7\25\2\2\u010d\u010e"+
		"\7\31\2\2\u010e\u010f\7/\2\2\u010f\u0110\7\31\2\2\u0110\u0111\7/\2\2\u0111"+
		"\u0112\7\31\2\2\u0112\u0113\7/\2\2\u0113\u016a\7\31\2\2\u0114\u0115\7"+
		"\26\2\2\u0115\u0116\7\60\2\2\u0116\u0117\7\31\2\2\u0117\u011a\7\61\2\2"+
		"\u0118\u0119\7/\2\2\u0119\u011b\7\35\2\2\u011a\u0118\3\2\2\2\u011a\u011b"+
		"\3\2\2\2\u011b\u016a\3\2\2\2\u011c\u011d\7\26\2\2\u011d\u011e\7\60\2\2"+
		"\u011e\u011f\7\31\2\2\u011f\u0120\5.\30\2\u0120\u0123\7\61\2\2\u0121\u0122"+
		"\7/\2\2\u0122\u0124\7\35\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124"+
		"\u016a\3\2\2\2\u0125\u0126\7\26\2\2\u0126\u0127\7\60\2\2\u0127\u0128\5"+
		"\16\b\2\u0128\u012b\7\61\2\2\u0129\u012a\7/\2\2\u012a\u012c\7\35\2\2\u012b"+
		"\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u016a\3\2\2\2\u012d\u012e\7\27"+
		"\2\2\u012e\u012f\7\31\2\2\u012f\u0130\7/\2\2\u0130\u0131\7\60\2\2\u0131"+
		"\u0132\7\31\2\2\u0132\u0133\7\61\2\2\u0133\u0134\7/\2\2\u0134\u016a\7"+
		"\33\2\2\u0135\u0136\7\27\2\2\u0136\u0137\7\31\2\2\u0137\u0138\7/\2\2\u0138"+
		"\u0139\7\60\2\2\u0139\u013a\7\31\2\2\u013a\u013b\5.\30\2\u013b\u013c\7"+
		"\61\2\2\u013c\u013d\7/\2\2\u013d\u0140\7\33\2\2\u013e\u013f\7/\2\2\u013f"+
		"\u0141\7\34\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u016a\3"+
		"\2\2\2\u0142\u0143\7\27\2\2\u0143\u0144\7\31\2\2\u0144\u0145\7/\2\2\u0145"+
		"\u0146\7\60\2\2\u0146\u0147\5\16\b\2\u0147\u0148\7\61\2\2\u0148\u0149"+
		"\7/\2\2\u0149\u014a\7\33\2\2\u014a\u016a\3\2\2\2\u014b\u014c\7\30\2\2"+
		"\u014c\u014d\7\60\2\2\u014d\u014e\7\31\2\2\u014e\u014f\7\61\2\2\u014f"+
		"\u0150\7/\2\2\u0150\u0151\7\31\2\2\u0151\u0152\7/\2\2\u0152\u016a\7\33"+
		"\2\2\u0153\u0154\7\30\2\2\u0154\u0155\7\60\2\2\u0155\u0156\7\31\2\2\u0156"+
		"\u0157\5.\30\2\u0157\u0158\7\61\2\2\u0158\u0159\7/\2\2\u0159\u015a\7\31"+
		"\2\2\u015a\u015b\7/\2\2\u015b\u015e\7\33\2\2\u015c\u015d\7/\2\2\u015d"+
		"\u015f\7\34\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u016a\3"+
		"\2\2\2\u0160\u0161\7\30\2\2\u0161\u0162\7\60\2\2\u0162\u0163\5\16\b\2"+
		"\u0163\u0164\7\61\2\2\u0164\u0165\7/\2\2\u0165\u0166\7\31\2\2\u0166\u0167"+
		"\7/\2\2\u0167\u0168\7\33\2\2\u0168\u016a\3\2\2\2\u0169\u00df\3\2\2\2\u0169"+
		"\u00e0\3\2\2\2\u0169\u00e4\3\2\2\2\u0169\u00e8\3\2\2\2\u0169\u00ea\3\2"+
		"\2\2\u0169\u00ee\3\2\2\2\u0169\u00f2\3\2\2\2\u0169\u00f6\3\2\2\2\u0169"+
		"\u00fa\3\2\2\2\u0169\u0100\3\2\2\2\u0169\u0106\3\2\2\2\u0169\u010c\3\2"+
		"\2\2\u0169\u0114\3\2\2\2\u0169\u011c\3\2\2\2\u0169\u0125\3\2\2\2\u0169"+
		"\u012d\3\2\2\2\u0169\u0135\3\2\2\2\u0169\u0142\3\2\2\2\u0169\u014b\3\2"+
		"\2\2\u0169\u0153\3\2\2\2\u0169\u0160\3\2\2\2\u016a-\3\2\2\2\u016b\u016c"+
		"\t\6\2\2\u016c\u016d\5\16\b\2\u016d/\3\2\2\2\37\63;=BHThjr\u0080\u0087"+
		"\u008c\u0091\u009c\u00a2\u00a5\u00b6\u00bc\u00c0\u00c6\u00ca\u00d2\u00dd"+
		"\u011a\u0123\u012b\u0140\u015e\u0169";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}