/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.alipay.utils;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hufeng
 * @version $Id: MyUtils, v0.1 2017年06月14日 10:06 AM hufeng Exp $
 */
public class MyUtils {
    private static final String BIZ_DIR      = "/Users/hufeng/projects/svnCode/mappprod/branch/ANT01993446_20170527_mappprod/app/biz";
    private static final String RESOURCE_DIR = "src/main/resources/META-INF/spring";


    public static void main(String[] args) throws Exception {
        MyUtils util = new MyUtils();
        util.process();

    }

    private void process () throws Exception{
        Files.newDirectoryStream(new File(BIZ_DIR).toPath(), path -> path.toFile().isDirectory()).forEach(path -> {
            final Params params = new Params();
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path.toString(), RESOURCE_DIR),
                    "*{-service,-reference, -monitor}.xml")) {
                directoryStream.forEach(configPath -> {
                    if (configPath.toString().endsWith("service.xml")) {
                        params.serviceIds.addAll(getBeansIdentity(configPath.toFile(), "bean"));
                    } else {
                        params.referenceIds.addAll(getBeansIdentity(configPath.toFile(), "sofa:reference"));
                    }
                    params.path = configPath;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            checkDuplicateConfig(params.serviceIds, params.referenceIds, params.path);
        });
    }

    class Params {
        public List<String> serviceIds   = new ArrayList<>();
        public List<String> referenceIds = new ArrayList<>();
        public Path path;
    }

    private void checkDuplicateConfig(List<String> serviceIds, List<String> referenceIds, Path path) {
        if (serviceIds == null || referenceIds == null) {
            System.out.println("Empty");
            return;
        }
        for (String id : serviceIds) {
            if (referenceIds.contains(id)) {
                System.out.println(id +" " + path.toString());
            }
        }
    }

    static List<String> getBeansIdentity(File file, String tag) {
        List<String> identities = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList nodeList = doc.getElementsByTagName(tag);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NamedNodeMap namedNodeMap = node.getAttributes();
                Node attr = namedNodeMap.getNamedItem("id");
                identities.add(attr.getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return identities;
    }
}
