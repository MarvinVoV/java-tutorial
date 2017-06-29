/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.alipay.utils;

import org.apache.commons.io.FileUtils;
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
        String lineSeparator = retrieveLineSeparator(file);
        List<String> fixLines = new ArrayList<>();

        Pattern pattern = Pattern.compile("^(\\s*)if\\s*\\(.+$");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            line = removeComments(line);
            if (line.matches("(\\s*)if\\s*\\(.+\\s*\\{\\s*$")) {
                fixLines.add(lines.get(i));
                continue;
            }
            Pattern linePattern = Pattern.compile("^(\\s*)if\\s*\\(.*\\).*;");
            Matcher lineMatcher = linePattern.matcher(line);
            if (lineMatcher.find()) {
                String space = lineMatcher.group(1);
                resolveOneLineExpression(line, space, fixLines);
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
                    String fixSentence = removeComments(lines.get(i)) + " {";
                    fixLines.add(fixSentence);
                    fixLines.add(lines.get(++i));
                    String endSentence = lines.get(i + 1);
                    endSentence = removeComments(endSentence);
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

        String content = StringUtils.join(fixLines, lineSeparator);
        if (lastLineIsCRorLF(file)) {
            content += lineSeparator;
        }
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

    private static void resolveOneLineExpression(String line, String space, List<String> target) {
        Stack<Character> stack = new Stack<>();
        String expr = removeComments(line);
        boolean flag = false;
        int index = 0;
        for (char c : line.toCharArray()) {
            ++index;
            if (c == '(') {
                if (!flag) {
                    flag = true;
                }
                stack.push(c);
            } else if (c == ')') {
                stack.pop();
            }
            if (flag && stack.isEmpty()) {
                break;
            }
        }
        target.add(expr.substring(0, index) + " {");
        target.add(space + "\t" + expr.substring(index));
        target.add(space + "}");
    }

    private static int indexOfSeparateIfSentence(List<String> lines, String beforeFragment, int cursor) {
        String currentLine = lines.get(cursor);
        currentLine = removeComments(currentLine);
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

    private static String retrieveLineSeparator(File file) throws IOException {
        char current;
        String lineSeparator = "";
        try (FileInputStream fis = new FileInputStream(file)) {
            while (fis.available() > 0) {
                current = (char) fis.read();
                if ((current == '\n') || (current == '\r')) {
                    lineSeparator += current;
                    if (fis.available() > 0) {
                        char next = (char) fis.read();
                        if ((next != current)
                                && ((next == '\r') || (next == '\n'))) {
                            lineSeparator += next;
                        }
                    }
                    return lineSeparator;
                }
            }
        }
        return null;
    }

    private static String removeComments(String line) {
        int index = line.indexOf("//");
        if (index != -1) {
            return line.substring(0, index);
        }
        return line;
    }

    private static boolean lastLineIsCRorLF(File file) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            long pos = raf.length() - 1;
            if (pos < 0) {
                return false;
            }
            raf.seek(pos);
            int c = raf.read();
            return c == '\n' || c == '\r';
        } catch (IOException e) {
            return false;
        } finally {
            if (raf != null) try {
                raf.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // /Users/hufeng/projects/svnCode/mappprod/trunk/app/biz/data/src/main/java/com/alipay/mappprod/biz/data/processor/CarnivalRealTimeDataProcessor.java
//        File file = new File("/Users/hufeng/projects/svnCode/mappprod/trunk/app/biz/data");
        File file = new File("/Users/hufeng/projects/svnCode/mappprod/trunk/app/biz/trade");
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
