package com.lkw.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

//实体类
@Data
public class SubjectData {
    @ExcelProperty(index=0)
    private String oneSubjectName;
    @ExcelProperty(index=1)
    private String twoSubjectName;

}
