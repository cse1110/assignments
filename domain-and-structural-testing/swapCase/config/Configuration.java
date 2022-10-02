package delft;

import nl.tudelft.cse1110.andy.config.MetaTest;
import nl.tudelft.cse1110.andy.config.RunConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration extends RunConfiguration {

    @Override
    public Map<String, Float> weights() {
        return new HashMap<>() {{
            put("coverage", 0.1f);
            put("mutation", 0.1f);
            put("meta", 0.8f);
            put("codechecks", 0.0f);
        }};
    }

    @Override
    public List<String> classesUnderTest() {
        return List.of("delft.DelftWordUtilities");
    }

    @Override
    public int numberOfMutationsToConsider() {
        return 10;
    }

    @Override
    public List<MetaTest> metaTests() {
        return List.of(
                MetaTest.insertAt("the string is empty", 29,
                        """
                         if ("".equals(str)) {
                            throw new RuntimeException("killed the mutant");
                         }
                         """),
                MetaTest.insertAt("the string is null", 29,
                        """
                         if (str == null) {
                            throw new RuntimeException("killed the mutant");
                         }
                         """),
                MetaTest.insertAt("the string is one lowercase letter", 32,
                        """
                        if (str.matches("[a-z]")) {
                            throw new RuntimeException("killed the mutant");
                        }
                        """),
                MetaTest.insertAt("the string is one uppercase letter", 32,
                        """
                        if (str.matches("[A-Z]")) {
                            throw new RuntimeException("killed the mutant");
                        }
                        """),
                MetaTest.insertAt("the string is one character whitespace", 32,
                        """
                        if (str.matches(" ")) {
                            throw new RuntimeException("killed the mutant");
                        }
                        """),
                MetaTest.insertAt("the string is multiple lowercase characters", 32,
                        """
                        if (str.matches("[a-z]+")) {
                            throw new RuntimeException("killed the mutant");
                        }
                        """),
                MetaTest.insertAt("the string is multiple uppercase characters", 32,
                        """
                        if (str.matches("[A-Z]+")) {
                            throw new RuntimeException("killed the mutant");
                        }
                        """),
                MetaTest.insertAt("the string has more than 3 characters", 33,
                        """
                        if (buffer.length > 3) {
                            throw new RuntimeException("killed the mutant");
                        }
                        """),
                MetaTest.withStringReplacement("negates first if-statement",
                        "Character.isUpperCase(ch)",
                        "!Character.isUpperCase(ch)"),
                MetaTest.withStringReplacement("negates second if-statement",
                        "Character.isLowerCase(ch)",
                        "!Character.isLowerCase(ch)"),
                MetaTest.withStringReplacement("does only work for upper case characters",
                        """
                        else if (Character.isLowerCase(ch)) {
                            if (whitespace) {
                                buffer[i] = Character.toTitleCase(ch);
                                whitespace = false;
                            } else {
                                buffer[i] = Character.toUpperCase(ch);
                            }
                        } else {
                            whitespace = Character.isWhitespace(ch);
                        }
                        """,
                        ""),
                MetaTest.withStringReplacement("does not work for title case characters",
                        """
                        if (whitespace) {
                            buffer[i] = Character.toTitleCase(ch);
                            whitespace = false;
                        """,
                        """
                        if (whitespace) {
                            buffer[i] = Character.toLowerCase(ch);
                            whitespace = false;
                        """),
                MetaTest.withStringReplacement("does not work for upper case characters",
                        """
                        if (Character.isUpperCase(ch)) {
                            buffer[i] = Character.toLowerCase(ch);
                            whitespace = false;
                        """,
                        """
                        if (Character.isUpperCase(ch)) {
                            buffer[i] = Character.toUpperCase(ch);
                            whitespace = false;
                        """),
                MetaTest.withStringReplacement("does not work for lower case characters",
                        """
                        else {
                            buffer[i] = Character.toUpperCase(ch);
                        }
                        """,
                        """
                        else {
                            buffer[i] = Character.toLowerCase(ch);
                        }
                        """)
        );
    }

}
