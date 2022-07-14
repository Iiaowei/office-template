package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/13 11:21:02
 */
public interface CTSeriesWrapper {
    void setTitle(CellReference cellReference);

    void setDataSource(AreaReference areaReference);

    void setNumDataSource(AreaReference areaReference);
}
