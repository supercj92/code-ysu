package com.cfysu.lab.apache.commons.text;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.diff.EditScript;
import org.apache.commons.text.diff.StringsComparator;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.apache.commons.text.matcher.StringMatcherFactory;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2022/1/19
 */
public class TextMain {

    @Test
    public void testSubstitutor() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("key", "val");
        StringSubstitutor sub = new StringSubstitutor(StringLookupFactory.INSTANCE.mapStringLookup(variables),
            StringMatcherFactory.INSTANCE.stringMatcher("${"), StringMatcherFactory.INSTANCE.stringMatcher("}"), '$');
        //StringSubstitutor sub = new StringSubstitutor(variables);
        String replace = sub.replace("aaa${key}");

        System.out.println(replace);

        System.out.println(StringSubstitutor.replace("bbb${key}", variables));
    }

    @Test
    public void testDiff() {
        StringsComparator stringsComparator = new StringsComparator("aaa", "aab");
        EditScript<Character> script = stringsComparator.getScript();
        System.out.println();
    }

    @Test
    public void testSimilar() {
        //FuzzyScore fuzzyScore = new FuzzyScore();
    }
}
