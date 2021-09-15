package delft;

import nl.tudelft.cse1110.andy.config.RunConfiguration;
import nl.tudelft.cse1110.andy.config.MetaTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration extends RunConfiguration {

    @Override
    public Map<String, Float> weights() {
        return new HashMap<>() {{
            put("coverage", 0.5f);
            put("mutation", 0.5f);
        }};
    }

    @Override
    public List<String> classesUnderTest() {
        return List.of("delft.ZigZag");
    }

}
