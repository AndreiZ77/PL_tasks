/**
 * Реализуйте метод упрощения unix-style путей.
 * 1. simplifyPath("/a/b/c/../d"); // → "/a/b/d"
 * 2. simplifyPath("/a/"); // → "/a"
 *
 * @Andrey_Zenkov  hi_andy@mail.ru
 */

package org.pl_tasks.simplify_path;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimplifyPath {

    public static void main(String[] args) { test(); }

    static void test() {
        for (String test_path : new String[] {
                "/a/b/c/../d",
                "/a/b/c/../d/mf/gdf -ff/../3g_fg/we",
                "/a/"})

            System.out.println(simplifyPath(test_path));
    }

    public static String simplifyPath(String inputPath ) {

        Stream <String> streamFromPath = Arrays.stream(inputPath.split("/|[^\\/]+\\/\\.\\."))
                .filter(n -> !n.isEmpty());

        String outputPath = streamFromPath
                .collect(Collectors.joining("/", "/", ""));

        return outputPath;


    }

}
