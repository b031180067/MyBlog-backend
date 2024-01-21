package com.bill.annotation;

import com.bill.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class}) // 指定提供驗證規則的類
public @interface State {

    // 驗證失敗後的訊息
    String message() default "狀態只能是 已發布 or 草稿";

    // 指定分組
    Class<?>[] groups() default {};

    // 取得State註解的附加訊息
    Class<? extends Payload>[] payload() default {};
}
