/* The following code was generated by JFlex 1.4.1 on 22/06/15 23:04 */

package com.language.parser;

import java.util.*;
import java_cup.runtime.*;
import com.language.exceptions.*;
import com.language.model.expression.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 22/06/15 23:04 from the specification file
 * <tt>C:/Users/Williams/workspace/Compilador/language/jflex/Scanner.jflex</tt>
 */
class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT_LINE = 1;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\5\1\25\1\2\1\0\1\3\1\1\16\5\4\0\1\3\1\45"+
    "\1\24\1\100\1\4\1\15\1\16\1\26\1\51\1\52\1\13\1\11"+
    "\1\47\1\12\1\10\1\14\1\6\11\7\1\50\1\46\1\22\1\44"+
    "\1\23\2\0\5\4\1\34\7\4\1\77\5\4\1\30\6\4\1\55"+
    "\1\27\1\56\1\20\1\66\1\0\1\35\1\63\1\65\1\41\1\33"+
    "\1\60\1\74\1\62\1\57\1\73\1\64\1\36\1\70\1\40\1\42"+
    "\1\71\1\4\1\31\1\37\1\43\1\32\1\72\1\61\1\75\1\67"+
    "\1\76\1\53\1\17\1\54\1\21\41\5\2\0\4\4\4\0\1\4"+
    "\2\0\1\5\7\0\1\4\4\0\1\4\5\0\27\4\1\0\37\4"+
    "\1\0\u01ca\4\4\0\14\4\16\0\5\4\7\0\1\4\1\0\1\4"+
    "\21\0\160\5\5\4\1\0\2\4\2\0\4\4\10\0\1\4\1\0"+
    "\3\4\1\0\1\4\1\0\24\4\1\0\123\4\1\0\213\4\1\0"+
    "\5\5\2\0\236\4\11\0\46\4\2\0\1\4\7\0\47\4\11\0"+
    "\55\5\1\0\1\5\1\0\2\5\1\0\2\5\1\0\1\5\10\0"+
    "\33\4\5\0\3\4\15\0\4\5\7\0\1\4\4\0\13\5\5\0"+
    "\53\4\37\5\4\0\2\4\1\5\143\4\1\0\1\4\10\5\1\0"+
    "\6\5\2\4\2\5\1\0\4\5\2\4\12\5\3\4\2\0\1\4"+
    "\17\0\1\5\1\4\1\5\36\4\33\5\2\0\131\4\13\5\1\4"+
    "\16\0\12\5\41\4\11\5\2\4\4\0\1\4\5\0\26\4\4\5"+
    "\1\4\11\5\1\4\3\5\1\4\5\5\22\0\31\4\3\5\244\0"+
    "\4\5\66\4\3\5\1\4\22\5\1\4\7\5\12\4\2\5\2\0"+
    "\12\5\1\0\7\4\1\0\7\4\1\0\3\5\1\0\10\4\2\0"+
    "\2\4\2\0\26\4\1\0\7\4\1\0\1\4\3\0\4\4\2\0"+
    "\1\5\1\4\7\5\2\0\2\5\2\0\3\5\1\4\10\0\1\5"+
    "\4\0\2\4\1\0\3\4\2\5\2\0\12\5\4\4\7\0\1\4"+
    "\5\0\3\5\1\0\6\4\4\0\2\4\2\0\26\4\1\0\7\4"+
    "\1\0\2\4\1\0\2\4\1\0\2\4\2\0\1\5\1\0\5\5"+
    "\4\0\2\5\2\0\3\5\3\0\1\5\7\0\4\4\1\0\1\4"+
    "\7\0\14\5\3\4\1\5\13\0\3\5\1\0\11\4\1\0\3\4"+
    "\1\0\26\4\1\0\7\4\1\0\2\4\1\0\5\4\2\0\1\5"+
    "\1\4\10\5\1\0\3\5\1\0\3\5\2\0\1\4\17\0\2\4"+
    "\2\5\2\0\12\5\1\0\1\4\17\0\3\5\1\0\10\4\2\0"+
    "\2\4\2\0\26\4\1\0\7\4\1\0\2\4\1\0\5\4\2\0"+
    "\1\5\1\4\7\5\2\0\2\5\2\0\3\5\10\0\2\5\4\0"+
    "\2\4\1\0\3\4\2\5\2\0\12\5\1\0\1\4\20\0\1\5"+
    "\1\4\1\0\6\4\3\0\3\4\1\0\4\4\3\0\2\4\1\0"+
    "\1\4\1\0\2\4\3\0\2\4\3\0\3\4\3\0\14\4\4\0"+
    "\5\5\3\0\3\5\1\0\4\5\2\0\1\4\6\0\1\5\16\0"+
    "\12\5\11\0\1\4\7\0\3\5\1\0\10\4\1\0\3\4\1\0"+
    "\27\4\1\0\12\4\1\0\5\4\3\0\1\4\7\5\1\0\3\5"+
    "\1\0\4\5\7\0\2\5\1\0\2\4\6\0\2\4\2\5\2\0"+
    "\12\5\22\0\2\5\1\0\10\4\1\0\3\4\1\0\27\4\1\0"+
    "\12\4\1\0\5\4\2\0\1\5\1\4\7\5\1\0\3\5\1\0"+
    "\4\5\7\0\2\5\7\0\1\4\1\0\2\4\2\5\2\0\12\5"+
    "\1\0\2\4\17\0\2\5\1\0\10\4\1\0\3\4\1\0\51\4"+
    "\2\0\1\4\7\5\1\0\3\5\1\0\4\5\1\4\10\0\1\5"+
    "\10\0\2\4\2\5\2\0\12\5\12\0\6\4\2\0\2\5\1\0"+
    "\22\4\3\0\30\4\1\0\11\4\1\0\1\4\2\0\7\4\3\0"+
    "\1\5\4\0\6\5\1\0\1\5\1\0\10\5\22\0\2\5\15\0"+
    "\60\4\1\5\2\4\7\5\4\0\10\4\10\5\1\0\12\5\47\0"+
    "\2\4\1\0\1\4\2\0\2\4\1\0\1\4\2\0\1\4\6\0"+
    "\4\4\1\0\7\4\1\0\3\4\1\0\1\4\1\0\1\4\2\0"+
    "\2\4\1\0\4\4\1\5\2\4\6\5\1\0\2\5\1\4\2\0"+
    "\5\4\1\0\1\4\1\0\6\5\2\0\12\5\2\0\2\4\42\0"+
    "\1\4\27\0\2\5\6\0\12\5\13\0\1\5\1\0\1\5\1\0"+
    "\1\5\4\0\2\5\10\4\1\0\44\4\4\0\24\5\1\0\2\5"+
    "\5\4\13\5\1\0\44\5\11\0\1\5\71\0\53\4\24\5\1\4"+
    "\12\5\6\0\6\4\4\5\4\4\3\5\1\4\3\5\2\4\7\5"+
    "\3\4\4\5\15\4\14\5\1\4\17\5\2\0\46\4\12\0\53\4"+
    "\1\0\1\4\3\0\u0149\4\1\0\4\4\2\0\7\4\1\0\1\4"+
    "\1\0\4\4\2\0\51\4\1\0\4\4\2\0\41\4\1\0\4\4"+
    "\2\0\7\4\1\0\1\4\1\0\4\4\2\0\17\4\1\0\71\4"+
    "\1\0\4\4\2\0\103\4\2\0\3\5\40\0\20\4\20\0\125\4"+
    "\14\0\u026c\4\2\0\21\4\1\0\32\4\5\0\113\4\3\0\3\4"+
    "\17\0\15\4\1\0\4\4\3\5\13\0\22\4\3\5\13\0\22\4"+
    "\2\5\14\0\15\4\1\0\3\4\1\0\2\5\14\0\64\4\40\5"+
    "\3\0\1\4\3\0\2\4\1\5\2\0\12\5\41\0\3\5\2\0"+
    "\12\5\6\0\130\4\10\0\51\4\1\5\1\4\5\0\106\4\12\0"+
    "\35\4\3\0\14\5\4\0\14\5\12\0\12\5\36\4\2\0\5\4"+
    "\13\0\54\4\4\0\21\5\7\4\2\5\6\0\12\5\46\0\27\4"+
    "\5\5\4\0\65\4\12\5\1\0\35\5\2\0\13\5\6\0\12\5"+
    "\15\0\1\4\130\0\5\5\57\4\21\5\7\4\4\0\12\5\21\0"+
    "\11\5\14\0\3\5\36\4\12\5\3\0\2\4\12\5\6\0\46\4"+
    "\16\5\14\0\44\4\24\5\10\0\12\5\3\0\3\4\12\5\44\4"+
    "\122\0\3\5\1\0\25\5\4\4\1\5\4\4\1\5\15\0\300\4"+
    "\47\5\25\0\4\5\u0116\4\2\0\6\4\2\0\46\4\2\0\6\4"+
    "\2\0\10\4\1\0\1\4\1\0\1\4\1\0\1\4\1\0\37\4"+
    "\2\0\65\4\1\0\7\4\1\0\1\4\3\0\3\4\1\0\7\4"+
    "\3\0\4\4\2\0\6\4\4\0\15\4\5\0\3\4\1\0\7\4"+
    "\16\0\5\5\32\0\5\5\20\0\2\4\23\0\1\4\13\0\5\5"+
    "\5\0\6\5\1\0\1\4\15\0\1\4\20\0\15\4\3\0\32\4"+
    "\26\0\15\5\4\0\1\5\3\0\14\5\21\0\1\4\4\0\1\4"+
    "\2\0\12\4\1\0\1\4\3\0\5\4\6\0\1\4\1\0\1\4"+
    "\1\0\1\4\1\0\4\4\1\0\13\4\2\0\4\4\5\0\5\4"+
    "\4\0\1\4\21\0\51\4\u0a77\0\57\4\1\0\57\4\1\0\205\4"+
    "\6\0\4\4\3\5\16\0\46\4\12\0\66\4\11\0\1\4\17\0"+
    "\1\5\27\4\11\0\7\4\1\0\7\4\1\0\7\4\1\0\7\4"+
    "\1\0\7\4\1\0\7\4\1\0\7\4\1\0\7\4\1\0\40\5"+
    "\57\0\1\4\u01d5\0\3\4\31\0\11\4\6\5\1\0\5\4\2\0"+
    "\5\4\4\0\126\4\2\0\2\5\2\0\3\4\1\0\132\4\1\0"+
    "\4\4\5\0\51\4\3\0\136\4\21\0\33\4\65\0\20\4\u0200\0"+
    "\u19b6\4\112\0\u51cc\4\64\0\u048d\4\103\0\56\4\2\0\u010d\4\3\0"+
    "\20\4\12\5\2\4\24\0\57\4\1\5\14\0\2\5\1\0\31\4"+
    "\10\0\120\4\2\5\45\0\11\4\2\0\147\4\2\0\4\4\1\0"+
    "\2\4\16\0\12\4\120\0\10\4\1\5\3\4\1\5\4\4\1\5"+
    "\27\4\5\5\20\0\1\4\7\0\64\4\14\0\2\5\62\4\21\5"+
    "\13\0\12\5\6\0\22\5\6\4\3\0\1\4\4\0\12\5\34\4"+
    "\10\5\2\0\27\4\15\5\14\0\35\4\3\0\4\5\57\4\16\5"+
    "\16\0\1\4\12\5\46\0\51\4\16\5\11\0\3\4\1\5\10\4"+
    "\2\5\2\0\12\5\6\0\27\4\3\0\1\4\1\5\4\0\60\4"+
    "\1\5\1\4\3\5\2\4\2\5\5\4\2\5\1\4\1\5\1\4"+
    "\30\0\3\4\43\0\6\4\2\0\6\4\2\0\6\4\11\0\7\4"+
    "\1\0\7\4\221\0\43\4\10\5\1\0\2\5\2\0\12\5\6\0"+
    "\u2ba4\4\14\0\27\4\4\0\61\4\u2104\0\u012e\4\2\0\76\4\2\0"+
    "\152\4\46\0\7\4\14\0\5\4\5\0\1\4\1\5\12\4\1\0"+
    "\15\4\1\0\5\4\1\0\1\4\1\0\2\4\1\0\2\4\1\0"+
    "\154\4\41\0\u016b\4\22\0\100\4\2\0\66\4\50\0\15\4\3\0"+
    "\20\5\20\0\7\5\14\0\2\4\30\0\3\4\31\0\1\4\6\0"+
    "\5\4\1\0\207\4\2\0\1\5\4\0\1\4\13\0\12\5\7\0"+
    "\32\4\4\0\1\4\1\0\32\4\13\0\131\4\3\0\6\4\2\0"+
    "\6\4\2\0\6\4\2\0\3\4\3\0\2\4\3\0\2\4\22\0"+
    "\3\5\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\3\2\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\1\1\21\1\1\1\22\13\3\1\23\1\1"+
    "\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33"+
    "\1\34\13\3\1\35\1\36\1\37\1\0\1\40\1\41"+
    "\1\42\1\43\1\44\1\45\1\0\1\46\1\0\1\46"+
    "\20\3\1\47\2\3\1\50\1\51\1\52\1\3\1\53"+
    "\15\3\1\54\7\3\1\55\3\3\1\56\2\3\1\57"+
    "\1\60\5\3\1\61\2\3\1\62\10\3\1\63\3\3"+
    "\1\64\3\3\1\65\4\3\1\66\1\67\1\3\1\70"+
    "\1\3\1\71\4\3\1\72\3\3\1\73\4\3\1\74"+
    "\1\75\4\3\1\76\2\3\1\77\1\100\1\3\1\101"+
    "\1\102\1\103\1\104\1\3\1\105\1\106\1\3\1\107"+
    "\1\3\1\110\2\3\1\111\1\112\1\113\1\114\2\3"+
    "\1\115\1\116\1\3\1\117\2\3\1\120\1\121";

  private static int [] zzUnpackAction() {
    int [] result = new int[213];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\101\0\202\0\303\0\202\0\u0104\0\u0145\0\u0186"+
    "\0\202\0\202\0\202\0\u01c7\0\u0208\0\202\0\202\0\202"+
    "\0\202\0\202\0\u0249\0\u028a\0\u02cb\0\202\0\u030c\0\202"+
    "\0\u034d\0\u038e\0\u03cf\0\u0410\0\u0451\0\u0492\0\u04d3\0\u0514"+
    "\0\u0555\0\u0596\0\u05d7\0\u0618\0\u0659\0\202\0\202\0\202"+
    "\0\202\0\202\0\202\0\202\0\202\0\202\0\u069a\0\u06db"+
    "\0\u071c\0\u075d\0\u079e\0\u07df\0\u0820\0\u0861\0\u08a2\0\u08e3"+
    "\0\u0924\0\202\0\202\0\202\0\u0965\0\202\0\202\0\202"+
    "\0\202\0\202\0\202\0\u02cb\0\202\0\u030c\0\u030c\0\u09a6"+
    "\0\u09e7\0\u0a28\0\u0a69\0\u0aaa\0\u0aeb\0\u0b2c\0\u0b6d\0\u0bae"+
    "\0\u0bef\0\u0c30\0\u0c71\0\u0cb2\0\u0cf3\0\u0d34\0\u0d75\0\u0104"+
    "\0\u0db6\0\u0df7\0\202\0\202\0\u0e38\0\u0e79\0\u0104\0\u0eba"+
    "\0\u0efb\0\u0f3c\0\u0f7d\0\u0fbe\0\u0fff\0\u1040\0\u1081\0\u10c2"+
    "\0\u1103\0\u1144\0\u1185\0\u11c6\0\u0965\0\u1207\0\u1248\0\u1289"+
    "\0\u12ca\0\u130b\0\u134c\0\u138d\0\u0104\0\u13ce\0\u140f\0\u1450"+
    "\0\u0104\0\u1491\0\u14d2\0\u0104\0\u0104\0\u1513\0\u1554\0\u1595"+
    "\0\u15d6\0\u1617\0\u0104\0\u1658\0\u1699\0\u0104\0\u16da\0\u171b"+
    "\0\u175c\0\u179d\0\u17de\0\u181f\0\u1860\0\u18a1\0\u0104\0\u18e2"+
    "\0\u1923\0\u1964\0\u0104\0\u19a5\0\u19e6\0\u1a27\0\u0104\0\u1a68"+
    "\0\u1aa9\0\u1aea\0\u1b2b\0\u0104\0\u0104\0\u1b6c\0\u0104\0\u1bad"+
    "\0\u0104\0\u1bee\0\u1c2f\0\u1c70\0\u1cb1\0\u0104\0\u1cf2\0\u1d33"+
    "\0\u1d74\0\u0104\0\u1db5\0\u1df6\0\u1e37\0\u1e78\0\u0104\0\u0104"+
    "\0\u1eb9\0\u1efa\0\u1f3b\0\u1f7c\0\u0104\0\u1fbd\0\u1ffe\0\u0104"+
    "\0\u0104\0\u203f\0\u0104\0\u0104\0\u0104\0\u0104\0\u2080\0\u0104"+
    "\0\u0104\0\u20c1\0\u0104\0\u2102\0\u0104\0\u2143\0\u2184\0\u0104"+
    "\0\u0104\0\u0104\0\u0104\0\u21c5\0\u2206\0\u0104\0\u0104\0\u2247"+
    "\0\u0104\0\u2288\0\u22c9\0\u0104\0\u0104";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[213];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\2\5\1\6\1\3\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\6\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50"+
    "\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60"+
    "\1\61\1\62\1\63\1\64\1\65\3\6\1\66\1\67"+
    "\1\70\3\6\1\71\1\72\2\73\1\74\76\73\103\0"+
    "\1\5\102\0\4\6\20\0\14\6\13\0\21\6\11\0"+
    "\1\75\76\0\2\10\1\75\103\0\1\76\101\0\1\77"+
    "\106\0\1\100\21\0\1\101\57\0\1\102\20\0\1\103"+
    "\34\0\1\104\2\0\21\104\1\105\1\0\53\104\1\106"+
    "\2\0\21\106\2\0\1\107\52\106\4\0\4\6\20\0"+
    "\1\6\1\110\12\6\13\0\21\6\5\0\4\6\20\0"+
    "\3\6\1\111\1\6\1\112\6\6\13\0\21\6\5\0"+
    "\4\6\20\0\6\6\1\113\5\6\13\0\16\6\1\114"+
    "\2\6\5\0\4\6\20\0\5\6\1\115\6\6\13\0"+
    "\21\6\5\0\4\6\20\0\10\6\1\116\3\6\13\0"+
    "\12\6\1\117\6\6\5\0\4\6\20\0\3\6\1\120"+
    "\10\6\13\0\1\121\20\6\5\0\4\6\20\0\13\6"+
    "\1\122\13\0\1\123\11\6\1\124\6\6\5\0\4\6"+
    "\20\0\12\6\1\125\1\6\13\0\21\6\5\0\4\6"+
    "\20\0\3\6\1\126\10\6\13\0\1\127\20\6\5\0"+
    "\4\6\20\0\1\6\1\130\12\6\13\0\21\6\5\0"+
    "\4\6\20\0\2\6\1\131\11\6\13\0\10\6\1\132"+
    "\10\6\45\0\1\133\100\0\1\134\40\0\4\6\20\0"+
    "\10\6\1\135\2\6\1\136\13\0\1\6\1\137\17\6"+
    "\5\0\4\6\20\0\6\6\1\140\3\6\1\141\1\6"+
    "\13\0\1\142\20\6\5\0\4\6\20\0\14\6\13\0"+
    "\3\6\1\143\15\6\5\0\4\6\20\0\5\6\1\144"+
    "\6\6\13\0\21\6\5\0\4\6\20\0\1\6\1\145"+
    "\12\6\13\0\21\6\5\0\4\6\20\0\3\6\1\146"+
    "\10\6\13\0\21\6\5\0\4\6\20\0\12\6\1\147"+
    "\1\6\13\0\21\6\5\0\4\6\20\0\1\6\1\150"+
    "\10\6\1\151\1\6\13\0\21\6\5\0\4\6\20\0"+
    "\5\6\1\152\6\6\13\0\21\6\5\0\4\6\20\0"+
    "\12\6\1\153\1\6\13\0\21\6\5\0\4\6\20\0"+
    "\12\6\1\154\1\6\13\0\21\6\7\0\2\155\75\0"+
    "\4\6\20\0\2\6\1\156\11\6\13\0\21\6\5\0"+
    "\4\6\20\0\13\6\1\157\13\0\12\6\1\160\6\6"+
    "\5\0\4\6\20\0\14\6\13\0\2\6\1\161\16\6"+
    "\5\0\4\6\20\0\7\6\1\162\4\6\13\0\21\6"+
    "\5\0\4\6\20\0\13\6\1\163\13\0\21\6\5\0"+
    "\4\6\20\0\6\6\1\164\5\6\13\0\21\6\5\0"+
    "\4\6\20\0\11\6\1\165\2\6\13\0\21\6\5\0"+
    "\4\6\20\0\14\6\13\0\12\6\1\166\6\6\5\0"+
    "\4\6\20\0\10\6\1\167\3\6\13\0\21\6\5\0"+
    "\4\6\20\0\7\6\1\170\4\6\13\0\21\6\5\0"+
    "\4\6\20\0\1\6\1\171\12\6\13\0\21\6\5\0"+
    "\4\6\20\0\14\6\13\0\17\6\1\172\1\6\5\0"+
    "\4\6\20\0\6\6\1\173\5\6\13\0\21\6\5\0"+
    "\4\6\20\0\13\6\1\174\13\0\21\6\5\0\4\6"+
    "\20\0\14\6\13\0\1\6\1\175\17\6\5\0\4\6"+
    "\20\0\14\6\13\0\6\6\1\176\12\6\5\0\4\6"+
    "\20\0\14\6\13\0\12\6\1\177\6\6\5\0\4\6"+
    "\20\0\14\6\13\0\12\6\1\200\6\6\5\0\4\6"+
    "\20\0\7\6\1\201\1\6\1\202\1\6\1\203\13\0"+
    "\21\6\5\0\4\6\20\0\3\6\1\204\10\6\13\0"+
    "\21\6\5\0\4\6\20\0\12\6\1\205\1\6\13\0"+
    "\21\6\5\0\4\6\20\0\1\6\1\206\12\6\13\0"+
    "\21\6\5\0\4\6\20\0\10\6\1\207\3\6\13\0"+
    "\21\6\5\0\4\6\20\0\14\6\13\0\1\210\20\6"+
    "\5\0\4\6\20\0\7\6\1\211\4\6\13\0\21\6"+
    "\5\0\4\6\20\0\3\6\1\212\10\6\13\0\21\6"+
    "\5\0\4\6\20\0\14\6\13\0\10\6\1\213\10\6"+
    "\5\0\4\6\20\0\2\6\1\214\5\6\1\215\3\6"+
    "\13\0\21\6\5\0\4\6\20\0\14\6\13\0\1\216"+
    "\20\6\5\0\4\6\20\0\14\6\13\0\12\6\1\217"+
    "\6\6\5\0\4\6\20\0\6\6\1\220\5\6\13\0"+
    "\21\6\5\0\4\6\20\0\14\6\13\0\1\221\20\6"+
    "\5\0\4\6\20\0\10\6\1\222\3\6\13\0\21\6"+
    "\5\0\4\6\20\0\3\6\1\223\10\6\13\0\21\6"+
    "\5\0\4\6\20\0\2\6\1\224\11\6\13\0\21\6"+
    "\5\0\4\6\20\0\6\6\1\225\5\6\13\0\21\6"+
    "\5\0\4\6\20\0\14\6\13\0\7\6\1\226\11\6"+
    "\5\0\4\6\20\0\3\6\1\227\10\6\13\0\21\6"+
    "\5\0\4\6\20\0\3\6\1\230\10\6\13\0\21\6"+
    "\5\0\4\6\20\0\7\6\1\231\4\6\13\0\21\6"+
    "\5\0\4\6\20\0\3\6\1\232\10\6\13\0\21\6"+
    "\5\0\4\6\20\0\14\6\13\0\15\6\1\233\3\6"+
    "\5\0\4\6\20\0\13\6\1\234\13\0\21\6\5\0"+
    "\4\6\20\0\3\6\1\235\10\6\13\0\21\6\5\0"+
    "\4\6\20\0\14\6\13\0\1\236\20\6\5\0\4\6"+
    "\20\0\13\6\1\237\13\0\21\6\5\0\4\6\20\0"+
    "\6\6\1\240\5\6\13\0\21\6\5\0\4\6\20\0"+
    "\3\6\1\241\10\6\13\0\21\6\5\0\4\6\20\0"+
    "\3\6\1\242\10\6\13\0\21\6\5\0\4\6\20\0"+
    "\3\6\1\243\10\6\13\0\21\6\5\0\4\6\20\0"+
    "\14\6\13\0\11\6\1\244\7\6\5\0\4\6\20\0"+
    "\5\6\1\245\6\6\13\0\21\6\5\0\4\6\20\0"+
    "\11\6\1\246\2\6\13\0\21\6\5\0\4\6\20\0"+
    "\6\6\1\247\5\6\13\0\21\6\5\0\4\6\20\0"+
    "\14\6\13\0\7\6\1\250\11\6\5\0\4\6\20\0"+
    "\5\6\1\251\6\6\13\0\21\6\5\0\4\6\20\0"+
    "\7\6\1\252\4\6\13\0\21\6\5\0\4\6\20\0"+
    "\10\6\1\253\3\6\13\0\21\6\5\0\4\6\20\0"+
    "\13\6\1\254\13\0\21\6\5\0\4\6\20\0\10\6"+
    "\1\255\3\6\13\0\21\6\5\0\4\6\20\0\2\6"+
    "\1\256\11\6\13\0\21\6\5\0\4\6\20\0\10\6"+
    "\1\257\3\6\13\0\21\6\5\0\4\6\20\0\3\6"+
    "\1\260\10\6\13\0\21\6\5\0\4\6\20\0\1\6"+
    "\1\261\12\6\13\0\21\6\5\0\4\6\20\0\5\6"+
    "\1\262\6\6\13\0\21\6\5\0\4\6\20\0\14\6"+
    "\13\0\1\263\20\6\5\0\4\6\20\0\10\6\1\264"+
    "\3\6\13\0\21\6\5\0\4\6\20\0\3\6\1\265"+
    "\10\6\13\0\21\6\5\0\4\6\20\0\10\6\1\266"+
    "\3\6\13\0\21\6\5\0\4\6\20\0\13\6\1\267"+
    "\13\0\21\6\5\0\4\6\20\0\13\6\1\270\13\0"+
    "\21\6\5\0\4\6\20\0\3\6\1\271\10\6\13\0"+
    "\21\6\5\0\4\6\20\0\1\6\1\272\12\6\13\0"+
    "\21\6\5\0\4\6\20\0\14\6\13\0\16\6\1\273"+
    "\2\6\5\0\4\6\20\0\7\6\1\274\4\6\13\0"+
    "\21\6\5\0\4\6\20\0\13\6\1\275\13\0\21\6"+
    "\5\0\4\6\20\0\3\6\1\276\10\6\13\0\21\6"+
    "\5\0\4\6\20\0\14\6\13\0\5\6\1\277\13\6"+
    "\5\0\4\6\20\0\14\6\13\0\5\6\1\300\13\6"+
    "\5\0\4\6\20\0\13\6\1\301\13\0\21\6\5\0"+
    "\4\6\20\0\14\6\13\0\1\302\20\6\5\0\4\6"+
    "\20\0\13\6\1\303\13\0\21\6\5\0\4\6\20\0"+
    "\3\6\1\304\10\6\13\0\21\6\5\0\4\6\20\0"+
    "\10\6\1\305\3\6\13\0\21\6\5\0\4\6\20\0"+
    "\14\6\13\0\6\6\1\306\12\6\5\0\4\6\20\0"+
    "\10\6\1\307\3\6\13\0\21\6\5\0\4\6\20\0"+
    "\11\6\1\310\2\6\13\0\21\6\5\0\4\6\20\0"+
    "\11\6\1\311\2\6\13\0\21\6\5\0\4\6\20\0"+
    "\14\6\13\0\3\6\1\312\15\6\5\0\4\6\20\0"+
    "\13\6\1\313\13\0\21\6\5\0\4\6\20\0\3\6"+
    "\1\314\10\6\13\0\21\6\5\0\4\6\20\0\10\6"+
    "\1\315\3\6\13\0\21\6\5\0\4\6\20\0\7\6"+
    "\1\316\4\6\13\0\21\6\5\0\4\6\20\0\3\6"+
    "\1\317\10\6\13\0\21\6\5\0\4\6\20\0\14\6"+
    "\13\0\12\6\1\320\6\6\5\0\4\6\20\0\14\6"+
    "\13\0\10\6\1\321\10\6\5\0\4\6\20\0\2\6"+
    "\1\322\11\6\13\0\21\6\5\0\4\6\20\0\2\6"+
    "\1\323\11\6\13\0\21\6\5\0\4\6\20\0\3\6"+
    "\1\324\10\6\13\0\21\6\5\0\4\6\20\0\13\6"+
    "\1\325\13\0\21\6\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[8970];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\1\1\1\11\3\1\3\11\2\1\5\11"+
    "\3\1\1\11\1\1\1\11\15\1\11\11\13\1\3\11"+
    "\1\0\6\11\1\0\1\11\1\0\24\1\2\11\171\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[213];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	private SymbolFactory sf;
	private StringBuffer string = new StringBuffer();

	public Scanner(java.io.InputStream r, SymbolFactory sf) {
		this(r);
		this.sf=sf;
	}

	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2224) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 52: 
          { return symbol(sym.TRUE, "True");
          }
        case 82: break;
        case 19: 
          { return symbol(sym.ASSIGN, "=");
          }
        case 83: break;
        case 1: 
          { throw new ParsingException("Illegal character at line " + yyline + ", column " + yycolumn + " >> " + yytext());
          }
        case 84: break;
        case 23: 
          { return symbol(sym.LPAREN, "(");
          }
        case 85: break;
        case 46: 
          { return symbol(sym.STR_FUNC, "str");
          }
        case 86: break;
        case 20: 
          { return symbol(sym.SEMICOLON, ";");
          }
        case 87: break;
        case 41: 
          { return symbol(sym.DISCTINT, "!=");
          }
        case 88: break;
        case 55: 
          { return symbol(sym.SIZE_FUNC, "index");
          }
        case 89: break;
        case 47: 
          { return symbol(sym.NOT, "not");
          }
        case 90: break;
        case 64: 
          { return symbol(sym.TUPLE_FUNC, "tuple");
          }
        case 91: break;
        case 72: 
          { return symbol(sym.RETURN, "return");
          }
        case 92: break;
        case 81: 
          { return symbol(sym.RAW_INPUT_FUNC, "raw_input");
          }
        case 93: break;
        case 79: 
          { return symbol(sym.HAS_KEY_FUNC, "has_key");
          }
        case 94: break;
        case 68: 
          { return symbol(sym.WHILE, "while");
          }
        case 95: break;
        case 53: 
          { return symbol(sym.ELSE, "else");
          }
        case 96: break;
        case 7: 
          { return symbol(sym.MINUS, "-");
          }
        case 97: break;
        case 36: 
          { return symbol(sym.RSHIFT, ">>");
          }
        case 98: break;
        case 22: 
          { return symbol(sym.COLON, ":");
          }
        case 99: break;
        case 49: 
          { return symbol(sym.INT_FUNC, "int");
          }
        case 100: break;
        case 4: 
          { return symbol(sym.INTEGER, yytext());
          }
        case 101: break;
        case 74: 
          { return symbol(sym.APPEND_FUNC, "append");
          }
        case 102: break;
        case 57: 
          { return symbol(sym.TYPE_FUNC, "type");
          }
        case 103: break;
        case 6: 
          { return symbol(sym.PLUS, "+");
          }
        case 104: break;
        case 35: 
          { return symbol(sym.LESS_EQUAL, ">");
          }
        case 105: break;
        case 48: 
          { return symbol(sym.DEF, "def");
          }
        case 106: break;
        case 75: 
          { return symbol(sym.LENGTH_FUNC, "length");
          }
        case 107: break;
        case 27: 
          { return symbol(sym.LBRACKET, "[");
          }
        case 108: break;
        case 17: 
          { return symbol(sym.TAB, "\t");
          }
        case 109: break;
        case 21: 
          { return symbol(sym.COMMA, ",");
          }
        case 110: break;
        case 62: 
          { return symbol(sym.FALSE, "False");
          }
        case 111: break;
        case 58: 
          { return symbol(sym.FIND_FUNC, "find");
          }
        case 112: break;
        case 61: 
          { return symbol(sym.NONE, "none");
          }
        case 113: break;
        case 77: 
          { return symbol(sym.VALUES_FUNC, "values");
          }
        case 114: break;
        case 25: 
          { return symbol(sym.LBRACE, "{");
          }
        case 115: break;
        case 80: 
          { return symbol(sym.CONTINUE, "continue");
          }
        case 116: break;
        case 69: 
          { return symbol(sym.BREAK, "break");
          }
        case 117: break;
        case 10: 
          { return symbol(sym.MOD, "%");
          }
        case 118: break;
        case 11: 
          { return symbol(sym.AND_BIT, "&");
          }
        case 119: break;
        case 15: 
          { return symbol(sym.LESS, "<");
          }
        case 120: break;
        case 33: 
          { return symbol(sym.DIV_INT, "/");
          }
        case 121: break;
        case 63: 
          { return symbol(sym.SPLIT_FUNC, "split");
          }
        case 122: break;
        case 28: 
          { return symbol(sym.RBRACKET, "]");
          }
        case 123: break;
        case 40: 
          { return symbol(sym.EQUALS, "==");
          }
        case 124: break;
        case 18: 
          { return symbol(sym.ESCAPE, "\\");
          }
        case 125: break;
        case 44: 
          { return symbol(sym.FLOAT, yytext());
          }
        case 126: break;
        case 65: 
          { return symbol(sym.INDEX_FUNC, "index");
          }
        case 127: break;
        case 24: 
          { return symbol(sym.RPAREN, ")");
          }
        case 128: break;
        case 9: 
          { return symbol(sym.DIV, "/");
          }
        case 129: break;
        case 34: 
          { return symbol(sym.LSHIFT, "<<");
          }
        case 130: break;
        case 32: 
          { return symbol(sym.EXP, "**");
          }
        case 131: break;
        case 13: 
          { return symbol(sym.XOR_BIT, "^");
          }
        case 132: break;
        case 71: 
          { return symbol(sym.PRINT_FUNC, "print");
          }
        case 133: break;
        case 14: 
          { return symbol(sym.NOT_BIT, "~");
          }
        case 134: break;
        case 76: 
          { return symbol(sym.INSERT_FUNC, "index");
          }
        case 135: break;
        case 73: 
          { return symbol(sym.EXTEND_FUNC, "extend");
          }
        case 136: break;
        case 37: 
          { return symbol(sym.GREATER_EQUAL, ">");
          }
        case 137: break;
        case 31: 
          { yybegin(YYINITIAL);
          }
        case 138: break;
        case 3: 
          { return symbol(sym.ID, yytext());
          }
        case 139: break;
        case 8: 
          { return symbol(sym.TIMES, "*");
          }
        case 140: break;
        case 66: 
          { return symbol(sym.ITEMS_FUNC, "items");
          }
        case 141: break;
        case 56: 
          { return symbol(sym.DICT_FUNC, "dict");
          }
        case 142: break;
        case 5: 
          { return symbol(sym.POINT, ".");
          }
        case 143: break;
        case 54: 
          { return symbol(sym.LIST_FUNC, "list");
          }
        case 144: break;
        case 70: 
          { return symbol(sym.COUNT_FUNC, "count");
          }
        case 145: break;
        case 42: 
          { return symbol(sym.IN, "in");
          }
        case 146: break;
        case 29: 
          { yybegin(COMMENT_LINE);
          }
        case 147: break;
        case 43: 
          { return symbol(sym.IF, "if");
          }
        case 148: break;
        case 51: 
          { return symbol(sym.POP_FUNC, "pop");
          }
        case 149: break;
        case 39: 
          { return symbol(sym.OR, "or");
          }
        case 150: break;
        case 78: 
          { return symbol(sym.REPLACE_FUNC, "replace");
          }
        case 151: break;
        case 60: 
          { return symbol(sym.JOIN_FUNC, "join");
          }
        case 152: break;
        case 50: 
          { return symbol(sym.FOR, "for");
          }
        case 153: break;
        case 16: 
          { return symbol(sym.GREATER, ">");
          }
        case 154: break;
        case 2: 
          { /* ignore */
          }
        case 155: break;
        case 45: 
          { return symbol(sym.AND, "and");
          }
        case 156: break;
        case 26: 
          { return symbol(sym.RBRACE, "{");
          }
        case 157: break;
        case 67: 
          { return symbol(sym.FLOAT_FUNC, "float");
          }
        case 158: break;
        case 38: 
          { return symbol(sym.STRING, yytext());
          }
        case 159: break;
        case 12: 
          { return symbol(sym.OR_BIT, "|");
          }
        case 160: break;
        case 59: 
          { return symbol(sym.KEYS_FUNC, "keys");
          }
        case 161: break;
        case 30: 
          { 
          }
        case 162: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {     return symbol(sym.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
