package com.endproject.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/9 0:20
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ApprovalEnumStatus {

    NO_SUBMIT(0,"未提交"),
    APPROVAL_TEACHER_ING(1,"审核中"),
    APPROVAL_TEACHER_PASSED(2,"审核通过"),
    TEACHER_REJECTED(3,"教师已驳回"),
    END_ED(4,"已结束");


    private Integer code;
    private String desc;
}
