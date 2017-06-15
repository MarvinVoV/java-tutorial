/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.alipay.utils;

import com.sun.xml.internal.ws.util.xml.NodeListIterator;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory.S_KEY_INDENT_AMOUNT;

/**
 * @author hufeng
 * @version $Id: ResolveUpgrade, v0.1 2017年06月14日 3:28 PM hufeng Exp $
 */
public class ResolveUpgrade {
    private static final Logger log = Logger.getLogger(ResolveUpgrade.class.getName());

    public void upgrade() {
        File file = new File("/Users/hufeng/projects/svnCode/mappprod/branch/ANT01993446_20170527_mappprod/app");

        try {
            Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toFile().getName().endsWith("xml") && attrs.isRegularFile()) {
                        if (!(file.toFile().getParentFile().getName().equalsIgnoreCase("OSGI-INF")
                                || file.toFile().getParentFile().getName().equalsIgnoreCase("db")
                                || file.toFile().getParentFile().getName().equalsIgnoreCase("testsuite")
                                || file.toFile().getParentFile().getName().equalsIgnoreCase("actsSuite")
                                || file.toFile().getName().equalsIgnoreCase("uri-brokers.xml")
                                || file.toFile().getName().equalsIgnoreCase("pom.xml")
                                || file.toFile().getName().contains("sqlmap")
                                || file.toFile().getName().contains("dao"))) {
                            try {
                                process(file.toFile());
                            } catch (Exception e) {
                                log.log(Level.SEVERE, "error filename = " + file.getFileName());
                                e.printStackTrace();
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void repairSchema(Document document) {
        NodeList nodeList = document.getElementsByTagName("beans");
        if (nodeList.getLength() != 1) {
            log.log(Level.SEVERE, "Beans node size should be one");
            throw new RuntimeException("No Beans");
        }
        Node root = nodeList.item(0);
        NamedNodeMap namedNodeMap = root.getAttributes();
        Node sofaXmlns = namedNodeMap.getNamedItem("xmlns:sofa");
        if (sofaXmlns != null) {
            sofaXmlns.setNodeValue("http://schema.alipay.com/sofa/schema/service");
        }
        Node schemaLocationNode = namedNodeMap.getNamedItem("xsi:schemaLocation");
        if (schemaLocationNode != null) {
            String source = schemaLocationNode.getNodeValue();
            String target = source.replaceFirst("http://img.alipay.net/dtd/schema/service",
                    "http://schema.alipay.com/sofa/schema/service")
                    .replaceFirst("http://img.alipay.net/dtd/schema/service/sofa-service.xsd",
                            "http://schema.alipay.com/sofa/sofa-service-4-0-0.xsd");
            schemaLocationNode.setNodeValue(target);
        }
    }

    private void repairSofaService(Document document) {
        NodeList nodeList = document.getElementsByTagName("sofa:service");
        if (nodeList.getLength() == 0) {
            return;
        }
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            resolveUniqueId(node);
            if (node.hasChildNodes() && node.getChildNodes().getLength() != 0) {
                String interfaceVal = null;
                NodeList childNodes = node.getChildNodes();
                NodeListIterator nodeListIterator = new NodeListIterator(childNodes);
                while (nodeListIterator.hasNext()) {
                    Node child = (Node) nodeListIterator.next();
                    if ("sofa:interface".equals(child.getNodeName())) {
                        Node nameAttrNode = getAttributeByName(child, "name");
                        if (nameAttrNode != null) {
                            interfaceVal = nameAttrNode.getNodeValue();
                            node.removeChild(child);
                            break;
                        }
                    }
                    if ("sofa:binding.tr".equalsIgnoreCase(child.getNodeName())) {
                        repairBindingTr(document, child);
                    }
                }
                if (interfaceVal != null) {
                    ((Element) node).setAttribute("interface", interfaceVal);
                }
            }
            removeEmptyTextNode(node);
        }
    }

    private void repairSofaReference(Document document) {
        NodeList nodeList = document.getElementsByTagName("sofa:reference");
        if (nodeList == null || nodeList.getLength() == 0) {
            return;
        }
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            resolveUniqueId(node);
            NodeList childNodes = node.getChildNodes();
            if (childNodes == null || childNodes.getLength() == 0) {
                continue;
            }
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node child = childNodes.item(j);
                if ("sofa:binding.tr".equalsIgnoreCase(child.getNodeName())) {
                    repairBindingTr(document, child);
                } else if ("sofa:binding.ws".equalsIgnoreCase(child.getNodeName())) {
                    repairBindingWs(document, child);
                }
            }
        }
    }

    private void resolveUniqueId(Node node) {
        Node oldUniqueIdAttr = getAttributeByName(node, "uniqueId");
        if (oldUniqueIdAttr != null) {
            String uniqueId = oldUniqueIdAttr.getNodeValue();
            removeAttributeByName(node, "uniqueId");
            ((Element) node).setAttribute("unique-id", uniqueId);
        }
    }


    private void repairBindingTr(Document document, Node node) {

        Element globalAttrsNode = document.createElement("sofa:global-attrs");
        Node oldTestUrl = getAttributeByName(node, "testURL");
        if (oldTestUrl != null) {
            String testUrl = oldTestUrl.getNodeValue();
            globalAttrsNode.setAttribute("test-url", testUrl);
            removeAttributeByName(node, "testURL");
        }

        NodeList childNodes = node.getChildNodes();
        NodeListIterator nodeListIterator = new NodeListIterator(childNodes);
        while (nodeListIterator.hasNext()) {
            Node child = (Node) nodeListIterator.next();
            if ("attributes".equalsIgnoreCase(child.getNodeName())) {
                Node timeoutAttr = getAttributeByName(child, "timeout");
                if (timeoutAttr != null) {
                    globalAttrsNode.setAttribute("timeout", timeoutAttr.getNodeValue());
                } else {
                    timeoutAttr = getAttributeByName(child, "http.timeout");
                    if (timeoutAttr != null) {
                        globalAttrsNode.setAttribute("timeout", timeoutAttr.getNodeValue());
                    }
                }
                node.removeChild(child);
            }
            if ("method".equalsIgnoreCase(child.getNodeName())) {
                Node nameAttr = getAttributeByName(child, "name");
                Node typeAttr = getAttributeByName(child, "type");
                Element methodNode = document.createElement("sofa:method");
                if (nameAttr != null) {
                    methodNode.setAttribute("name", nameAttr.getNodeValue());
                }
                if (typeAttr != null) {
                    methodNode.setAttribute("type", typeAttr.getNodeValue());
                }
                if (child.hasChildNodes()) {
                    NodeList subNodeChildren = child.getChildNodes();
                    Node timeoutNode = null;
                    for (int j = 0; j < subNodeChildren.getLength(); j++) {
                        Node subNodeChild = subNodeChildren.item(j);
                        if ("attributes".equals(subNodeChild.getNodeName())) {
                            timeoutNode = subNodeChild;
                            break;
                        }
                    }
                    if (timeoutNode != null) {
                        Node methodTimeoutAttr = getAttributeByName(timeoutNode, "_TIMEOUT");
                        if (methodTimeoutAttr != null) {
                            methodNode.setAttribute("timeout", methodTimeoutAttr.getNodeValue());
                        } else {
                            methodTimeoutAttr = getAttributeByName(timeoutNode, "TIMEOUT");
                            methodNode.setAttribute("timeout", methodTimeoutAttr.getNodeValue());
                        }
                    }
                }
                node.appendChild(methodNode);
                node.removeChild(child);
            }
            if ("compatible".equals(child.getNodeName()) && child.hasChildNodes()) {
                resolveCompatibleTag(child, document);
            }
        }
        if (globalAttrsNode.getAttributes().getLength() > 0) {
            node.insertBefore(globalAttrsNode, node.getFirstChild());
        }
        removeEmptyTextNode(node);

    }

    private void repairBindingWs(Document document, Node node) {
        Element globalAttrsNode = document.createElement("sofa:global-attrs");
        Node oldTestUrl = getAttributeByName(node, "testURL");
        if (oldTestUrl != null) {
            String testUrl = oldTestUrl.getNodeValue();
            globalAttrsNode.setAttribute("test-url", testUrl);
            removeAttributeByName(node, "testURL");
        }
        NodeList childNodes = node.getChildNodes();
        NodeListIterator nodeListIterator = new NodeListIterator(childNodes);
        while (nodeListIterator.hasNext()) {
            Node child = (Node) nodeListIterator.next();
            if ("attributes".equalsIgnoreCase(child.getNodeName())) {
                NamedNodeMap attrs = child.getAttributes();
                for (int j = 0; j < attrs.getLength(); j++) {
                    Node attrNode = attrs.item(j);
                    globalAttrsNode.setAttribute(attrNode.getNodeName(), attrNode.getNodeValue());
                }
                node.removeChild(child);
            } else if ("compatible".equalsIgnoreCase(child.getNodeName()) && child.hasChildNodes()) {
                resolveCompatibleTag(child, document);
            }
        }
        if (globalAttrsNode.getAttributes().getLength() > 0) {
            node.insertBefore(globalAttrsNode, node.getFirstChild());
        }
        removeEmptyTextNode(node);
    }

    private void resolveCompatibleTag(Node node, Document document) {
        Node oldVipNode = null;
        NodeList subNodeChildren = node.getChildNodes();
        for (int j = 0; j < subNodeChildren.getLength(); j++) {
            Node subNodeChild = subNodeChildren.item(j);
            if ("vip".equalsIgnoreCase(subNodeChild.getNodeName())) {
                oldVipNode = subNodeChild;
                break;
            }
        }
        if (oldVipNode != null) {
            String vip = oldVipNode.getTextContent();
            Element vipNode = document.createElement("sofa:vip");
            vipNode.setAttribute("url", vip);
            vipNode.setAttribute("only", "false");
            vipNode.setAttribute("enforce", "false");
            node.getParentNode().appendChild(vipNode);
        }
        node.getParentNode().removeChild(node);
    }

    /**
     * Spring 4+ not support local attribute in ref tag.
     *
     * @param document document
     */
    private void resolveLocalAttr(Document document) {
        NodeList nodeList = document.getElementsByTagName("bean");
        if (nodeList.getLength() == 0) {
            return;
        }
        NodeListIterator beansIterator = new NodeListIterator(nodeList);
        while (beansIterator.hasNext()) {
            Node beanNode = (Node) beansIterator.next();
            replaceLocalTag(beanNode);
        }

    }

    private void replaceLocalTag(Node node) {
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            NodeListIterator iterator = new NodeListIterator(childNodes);
            while (iterator.hasNext()) {
                Node child = (Node) iterator.next();
                if ("ref".equalsIgnoreCase(child.getNodeName())) {
                    Node localAttrNode = getAttributeByName(child, "local");
                    if (localAttrNode != null) {
                        String local = localAttrNode.getNodeValue();
                        ((Element) child).setAttribute("bean", local);
                        removeAttributeByName(child, "local");
                    }
                }
                replaceLocalTag(child);
            }
        }
    }

    private Node getAttributeByName(Node node, String attributeName) {
        if (node == null || StringUtils.isEmpty(attributeName)) {
            return null;
        }
        NamedNodeMap nodeAttributes = node.getAttributes();
        return nodeAttributes.getNamedItem(attributeName);
    }

    private Node removeAttributeByName(Node node, String attributeName) {
        if (node == null || StringUtils.isEmpty(attributeName)) {
            return null;
        }
        NamedNodeMap nodeAttributes = node.getAttributes();
        return nodeAttributes.removeNamedItem(attributeName);
    }

    private void removeEmptyTextNode(Node node) {
        Node child = node.getFirstChild();
        while (child != null) {
            Node sibling = child.getNextSibling();
            if (child.getNodeType() == Node.TEXT_NODE) {
                if (child.getTextContent().trim().isEmpty())
                    node.removeChild(child);
            } else {
                removeEmptyTextNode(child);
            }
            child = sibling;
        }
    }

    private void process(File file) {
        if (!file.getName().endsWith("xml")) {
            return;
        }
        Document doc;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
            doc.setXmlStandalone(true);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Build document factory error.");
            throw new RuntimeException(e);
        }

        repairSchema(doc);
        repairSofaService(doc);
        repairSofaReference(doc);
        resolveLocalAttr(doc);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            writeXml(doc, fileOutputStream);
        } catch (Exception e) {
            log.log(Level.SEVERE, "write error.");
            throw new RuntimeException(e);
        }
//        writeXml(doc, System.out);
    }

    private void writeXml(Document document, OutputStream outputStream) {
        document.normalize();
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "GBK");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(S_KEY_INDENT_AMOUNT, "4");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);
        } catch (Exception e) {
            log.log(Level.SEVERE, "write xml error.");
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {
        ResolveUpgrade resolver = new ResolveUpgrade();
        resolver.upgrade();
    }
}
