package tutorial.javaparser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hufeng
 * @version LinkNode.java, v 0.1 26/10/2017 6:15 AM Exp $
 */

public class LinkNode {

    private LinkNode parent;
    private List<LinkNode> children = new ArrayList<>();
    private ClazzFileInfo value;

    public LinkNode getParent() {
        return parent;
    }

    public void setParent(LinkNode parent) {
        this.parent = parent;
    }

    public List<LinkNode> getChildren() {
        return children;
    }

    public void setChildren(List<LinkNode> children) {
        this.children = children;
    }

    public ClazzFileInfo getValue() {
        return value;
    }

    public void setValue(ClazzFileInfo value) {
        this.value = value;
    }
}
