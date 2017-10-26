package tutorial.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import tutorial.javaparser.model.ClazzFileInfo;
import tutorial.javaparser.model.LinkNode;

import java.io.IOException;
import java.lang.reflect.Member;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hufeng
 * @version MethodLinkExplore.java, v 0.1 26/10/2017 3:30 AM Exp $
 */

public class MethodLinkExplore {

    private Map<String, ClazzFileInfo> buildIndex(String projectDir) {
        final Map<String, ClazzFileInfo> map = new HashMap<>();
        try {
            Files.walk(Paths.get(projectDir))
                    .filter(path -> path.toFile().isFile() && path.toString().endsWith(".java"))
                    .filter(path -> !path.toFile().getPath().contains("/src/test/"))
                    .filter(path -> !path.toString().contains("CommentDetailConvertor"))
                    .forEach(path -> {
                        try {
                            ClazzFileInfo clazzFileInfo = buildClazzProfile(path);
                            map.put(clazzFileInfo.getReference(), clazzFileInfo);
                        } catch (Exception e) {
                            System.out.println(path);
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    private ClazzFileInfo buildClazzProfile(Path path) {
        ClazzFileInfo clazzFileInfo = new ClazzFileInfo();
        CompilationUnit cu = null;
        try {
            cu = JavaParser.parse(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clazzFileInfo.setCu(cu);
        clazzFileInfo.setReference(parseReference(cu));
        clazzFileInfo.setFilePath(path.toString());
        clazzFileInfo.setImportList(parseImports(cu));
        clazzFileInfo.setMembers(parseMemeberTypeNames(cu));
        clazzFileInfo.setMethodList(parseMethodList(cu));
        return clazzFileInfo;
    }

    private String parseReference(CompilationUnit compilationUnit) {
        String packageName = compilationUnit.getPackageDeclaration().orElseGet(null).getNameAsString();
        String typeName = compilationUnit.getType(0).getNameAsString();
        return packageName + "." + typeName;
    }

    private List<String> parseImports(CompilationUnit compilationUnit) {
        return compilationUnit.getImports().stream().map(ImportDeclaration::getNameAsString).collect(Collectors.toList());
    }

    private Map<String, String> parseMemeberTypeNames(CompilationUnit compilationUnit) {
        List<ClassOrInterfaceDeclaration> cOf = compilationUnit.getChildNodesByType(ClassOrInterfaceDeclaration.class);
        if (cOf == null || cOf.size() == 0) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        NodeList<BodyDeclaration<?>> bodyDeclarations = cOf.get(0).getMembers();
        if (bodyDeclarations.isNonEmpty()) {

            bodyDeclarations.stream().filter(BodyDeclaration::isFieldDeclaration).forEach(item -> {
                VariableDeclarator variableDeclarator = item.asFieldDeclaration().getVariables().get(0);
                String key = variableDeclarator.getType().toString();
                String value = variableDeclarator.getNameAsString();
                map.put(key, value);
            });
        }
        return map;
    }

    private List<String> parseMethodList(CompilationUnit compilationUnit) {
        List<String> methodName = new ArrayList<>();
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                methodName.add(n.getNameAsString());
                super.visit(n, arg);
            }
        }, null);
        return methodName;
    }


    private List<ClazzFileInfo> findRootUsageClazz(Map<String, ClazzFileInfo> data, String reference, String methodName) {
        return data.values().stream().filter(v -> {
            boolean hasImport = v.getImportList().contains(reference);
            if (!hasImport) {
                return false;
            }
            System.out.println("=====" + reference);
            String interfaceName = reference.substring(reference.lastIndexOf(".") + 1);
            System.out.println(interfaceName);
            boolean hasMember = v.getMembers() != null && v.getMembers().keySet().contains(interfaceName);
            if (!hasMember) {
                return false;
            }

            final boolean[] hasUsage = new boolean[]{false};
            v.getCu().accept(new VoidVisitorAdapter<Void>() {
                @Override
                public void visit(MethodDeclaration n, Void arg) {
                    if (n.getBody().isPresent()) {
                        NodeList<Statement> statements = n.getBody().get().getStatements();
                        if (statements != null && statements.size() > 0) {
                            n.getBody().get().getStatements().forEach(item -> {
                                if (item.toString().contains(v.getMembers().get(interfaceName) + "." + methodName)) {
                                    hasUsage[0] = true;
                                }
                            });
                        }
                    }
                    super.visit(n, arg);
                }
            }, null);
            return hasUsage[0];
        }).collect(Collectors.toList());
    }

    private void exploreInvokeLink(List<ClazzFileInfo> clazzFileInfos, String reference, String methodName) {
        Stack<LinkNode> stack = new Stack<>();
        LinkNode root = new LinkNode();
        clazzFileInfos.forEach(item -> {
            LinkNode node = new LinkNode();
            node.setValue(item);
            node.setParent(root);
            root.getChildren().add(node);
            stack.push(node);
        });

        while (!stack.isEmpty()) {
            LinkNode node = stack.pop();
            ClazzFileInfo clazzFileInfo = node.getValue();
//            clazzFileInfo.getCu().

        }


    }

    public static void main(String[] args) throws Exception {
        String reference = "com.alipay.mappprod.common.integration.mquery.ShopReportFacadeClient";
        String method = "queryShopByPidWithPage";

        String projectDir = "/Users/marvin/alibaba/gitlab/mappprod";
        MethodLinkExplore explore = new MethodLinkExplore();
        Map<String, ClazzFileInfo> data = explore.buildIndex(projectDir);
        System.out.println(data.size());
        List<ClazzFileInfo> clazzFileInfos = explore.findRootUsageClazz(data, reference, method);
        System.out.println(clazzFileInfos.size());

    }
}
