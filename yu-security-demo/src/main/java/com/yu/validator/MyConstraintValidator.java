package com.yu.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Auther: yuchanglong
 * @Date: 2018-11-1
 * @Description: 自定义校验器
 */
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    private String type;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        log.info("MyConstraintValidator initialize");
        Class<?>[] groups = constraintAnnotation.groups();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        //业务逻辑判断
        //value: 前端值

        return false;
    }
}
