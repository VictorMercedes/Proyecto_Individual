/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexers;

import java.io.File;
import java.util.*;

/**
 *
 * @author Charly Ponce
 */
public class LexersGenerator {
    public static void main(String[] args) {
        List<String> routes = new ArrayList<>();
        routes.add("");
        routes.forEach(route -> generateLexer(route));
    }
    public static void generateLexer(String route){
        File file = new File(route);
        JFlex.Main.generate(file);
    }
}
