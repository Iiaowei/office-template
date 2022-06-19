import org.example.template.core.annotation.HeadStyle;
import org.example.template.core.annotation.Property;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2021/10/22 13:26:12
 */
public class Export {
    @Test
    public void export() {
        Hhh hhh = null;
        run(hhh::method2);
        run1(hhh::method3);
    }
    public void run(Funtion1<Serializable> funtion1) {
        funtion1.apply(new City());
    }
    public void run1(Funtion1<List> funtion1) {
        funtion1.apply(new ArrayList());
    }
}
interface Hhh {
    void method1();
    void method2(Serializable city);
    void method3(List city);

//    void method2(Object o);
}
