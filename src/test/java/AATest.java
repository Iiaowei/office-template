import org.example.template.core.Table;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/18 13:51:13
 */
public class AATest {
    @Test
    void test1() throws NoSuchFieldException {
        List<Table> list = new ArrayList<>();
        Field stringListField = Table.class.getDeclaredField("list");
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.
        qq(list);
    }

    void qq(List list) {
        ParameterizedType genericSuperclass = (ParameterizedType)list.getClass().getGenericSuperclass();
        Type actualTypeArgument = genericSuperclass.getActualTypeArguments()[0];
        System.out.println(actualTypeArgument);
    }
}
