/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.alipay.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hufeng
 * @version $Id: RuleFixUtil, v0.1 2017年06月28日 11:29 AM hufeng Exp $
 */
public class RuleFixUtil {
    private static final Logger LOGGER = Logger.getLogger(RuleFixUtil.class.getName());

    private static void fixIfWithoutBraceRule(File file, String encoding) throws Exception {
        LOGGER.log(Level.INFO, "----Start process file: " + file.getAbsolutePath());
        List<String> lines = FileUtils.readLines(file, encoding);
        System.out.println(lines.size());
        List<String> fixLines = new ArrayList<>();

        Pattern pattern = Pattern.compile("^(\\s*)if\\s*\\(.+$");
        for (int i = 0; i < lines.size(); i++) {
            if (i == 99) {
                System.out.println(i);
            }
            if (lines.get(i).matches("(\\s*)if\\s*\\(.+\\s*\\{$")) {
                fixLines.add(lines.get(i));
                continue;
            }
            Matcher matcher = pattern.matcher(lines.get(i));
            if (matcher.find()) {
                LOGGER.log(Level.INFO, i + "\t" + matcher.group());
                String space = matcher.group(1);
                if (!isBalance(lines.get(i))) {
                    int scope = resolveSeparateIfSentence(lines, i, fixLines, space);
                    if (scope == 0) {
                        fixLines.add(lines.get(i));
                    } else {
                        i += scope;
                    }
                } else {
                    String fixSentence = lines.get(i) + " {";
                    fixLines.add(fixSentence);
                    fixLines.add(lines.get(++i));
                    String endSentence = lines.get(i + 1);
                    if (endSentence.matches("^\\s+else\\s*$")) {
                        i++;
                        fixLines.add(space + "} else {");
                        fixLines.add(lines.get(++i));
                        fixLines.add(space + "}");
                    } else if (endSentence.matches("^\\s+else\\s*\\{\\s*$")) {
                        i++;
                        fixLines.add(space + "} else {");
                    } else {
                        fixLines.add(space + "}");
                    }
                }
            } else {
                fixLines.add(lines.get(i));
            }
        }

        String content = StringUtils.join(fixLines, "\n");
        content += "\n";
        FileUtils.write(file, content, encoding);
    }

    private static int resolveSeparateIfSentence(List<String> lines, int cursor, List<String> target, String space) {
        int next = cursor + 1;
        int end = indexOfSeparateIfSentence(lines, lines.get(cursor), next);
        if (end != -1) {
            for (int i = cursor; i < end; i++) {
                target.add(lines.get(i));
            }
            target.add(lines.get(end) + " {");
            target.add(lines.get(end + 1));
            target.add(space + "}");
            return end - cursor + 1;
        } else {
            return 0;
        }
    }

    private static int indexOfSeparateIfSentence(List<String> lines, String beforeFragment, int cursor) {
        String currentLine = lines.get(cursor);
        if (currentLine.matches("^.*\\s*\\{\\s*$")) {
            return -1;
        }
        String line = beforeFragment + currentLine;
        if (isBalance(line)) {
            return cursor;
        } else {
            return indexOfSeparateIfSentence(lines, line, ++cursor);
        }
    }


    private static boolean isBalance(String line) {
        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            if ('(' == c) {
                stack.push(c);
            } else if (')' == c) {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) throws Exception {
//        File file = new File("/Users/hufeng/projects/svnCode/mappprod/trunk/app/biz/data/src/main/java/com/alipay/mappprod/biz/data/datahandler/DataCardHandler.java");
        File file = new File("/Users/hufeng/projects/svnCode/mappprod/trunk/app/biz/promo/src/main/java/com/alipay/mappprod/biz/promo/service/impl/ItemActivityPostManagerImpl.java");
        try {
            Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toFile().getName().endsWith("java") && attrs.isRegularFile()) {
                        try {
                            RuleFixUtil.fixIfWithoutBraceRule(file.toFile(), "GBK");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
