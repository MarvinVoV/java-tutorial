package tutorial.javaparser.model;

import com.github.javaparser.ast.CompilationUnit;

import java.util.List;
import java.util.Map;

/**
 * @author hufeng
 * @version ClazzFileInfo.java, v 0.1 26/10/2017 4:07 AM Exp $
 */

public class ClazzFileInfo {
    private String reference;
    private String filePath;
    private List<String> importList;
    private Map<String, String> members;
    private List<String> methodList;
    private CompilationUnit cu;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getImportList() {
        return importList;
    }

    public void setImportList(List<String> importList) {
        this.importList = importList;
    }

    public Map<String, String> getMembers() {
        return members;
    }

    public void setMembers(Map<String, String> members) {
        this.members = members;
    }

    public List<String> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<String> methodList) {
        this.methodList = methodList;
    }

    public CompilationUnit getCu() {
        return cu;
    }

    public void setCu(CompilationUnit cu) {
        this.cu = cu;
    }
}
