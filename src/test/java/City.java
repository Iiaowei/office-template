import org.example.template.core.annotation.HeadStyle;
import org.example.template.core.annotation.Property;

import java.io.Serializable;

/**
 * @author liaowei
 * @version V1.0
 * @date 2021/10/22 13:24:42
 */
@HeadStyle(color = 0xD710ED)
public class City implements Serializable {
    @Property(value = "城市名称", color = 0x070100)
    private String name;
}
