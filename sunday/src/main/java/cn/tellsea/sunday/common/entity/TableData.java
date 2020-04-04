package cn.tellsea.sunday.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表格对象
 *
 * @author Tellsea
 * @date 2020/4/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableData {

    private int count;

    private Object data;
}
