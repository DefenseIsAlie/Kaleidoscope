package abhishek.josyula;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import abhishek.josyula.ExprParser.ProgContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {   
        try {
            ExprLexer lex = new ExprLexer(CharStreams
                    .fromString("10-3*5"));

            CommonTokenStream strm = new CommonTokenStream(lex);
            ExprParser pr = new ExprParser(strm);

            ParseTree tr = pr.expr();
            ParseTreeWalker wlkr = new ParseTreeWalker();
            T4 listner = new T4();

            wlkr.walk(listner, tr);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class T4 extends ExprBaseListener {
    @Override
    public void exitProg(ProgContext ctx) {
        System.out.println("Hello World");
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx){
        System.out.println(ctx.getText());
    }
}