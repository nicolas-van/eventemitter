package eventemitter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventEmitterTest {

    @Test
    public void test() {
        Map<String, String> tmpMap = new HashMap<>();
        EventEmitter<String> ee = new EventEmitter<>();
        Consumer<String> c =
                (t) -> {
                    tmpMap.put("test", t);
                };
        ee.addConsumer(c);
        Assertions.assertNull(tmpMap.get("test"));
        ee.trigger("hello");
        Assertions.assertEquals(tmpMap.get("test"), "hello");
        ee.trigger("hola");
        Assertions.assertEquals(tmpMap.get("test"), "hola");
        ee.removeConsumer(c);
        ee.trigger("hello world");
        Assertions.assertEquals(tmpMap.get("test"), "hola");
    }
}
