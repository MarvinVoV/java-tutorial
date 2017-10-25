package tutorial.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.typesystem.Type;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * <a href="https://github.com/javaparser/javaparser/wiki/Manual">Manual</a>
 * <a href="http://javaparser.org/">javaparser.org</a>
 * @author hufeng
 * @version JavaParserTutorial.java, v 0.1 26/10/2017 1:51 AM Exp $
 */

public class JavaParserTutorial {

    private static CompilationUnit cu;
    private static CombinedTypeSolver combinedTypeSolver;

    @BeforeClass
    public static void init() throws Exception {
        String file = "/Users/marvin/alibaba/gitlab/mappprod/app/biz/platform/src/main/java/com/alipay/mappprod/biz/platform/rpc/processor/ShopQueryProcessor.java";
        FileInputStream in = new FileInputStream(file);
        cu = JavaParser.parse(in);


        combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new ReflectionTypeSolver());
        combinedTypeSolver.add(new JavaParserTypeSolver(new File("src/test/resources/javaparser_src/proper_source")));
        combinedTypeSolver.add(new JavaParserTypeSolver(new File("src/test/resources/javaparser_src/generated")));

    }

    @Test
    public void printCompilationUnit() {
        System.out.println(cu.toString());

    }

    @Test
    public void visitClassMethods() throws Exception {
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this
             CompilationUnit, including inner class methods */
                System.out.println(n.getName());
                System.out.println(n.getBody());
                System.out.println(n.getModifiers());
                System.out.println(n.getDeclarationAsString());
                System.out.println(n.getChildNodes().get(n.getChildNodes().size()-1));
                Node node = n.getChildNodes().get(n.getChildNodes().size() - 1);
                n.getBody().get().accept(new VoidVisitorAdapter<Void>() {
                    @Override
                    public void visit(BlockStmt n, Void arg) {
                        System.out.println("----");
                        System.out.println(n);
                        System.out.println("----");
                        super.visit(n, arg);
                    }
                }, null);
//                BlockStmt blockStmt = JavaParser.parseBlock("{} // hello");
                JavaParserFacade javaParserFacade = JavaParserFacade.get(combinedTypeSolver);
//                SymbolReference symbolReference = javaParserFacade.solve(node);
//                System.out.println(symbolReference);
                super.visit(n, arg);
            }
        }, null);
    }

}
