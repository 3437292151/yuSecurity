package com.yu.beans.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.yu.validator.MyConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * Created by dell on 2018-7-17.
 */
@Data
public class UserDTO {

    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    @JsonView(UserDetailView.class)
    private String id;

    /*@MyConstraint(message = "这是一个测试")
    @ApiModelProperty(value = "用户名")*/
    @JsonView(UserSimpleView.class)
    @MyConstraint(message = "自定义校验标签")
    private String username;

    @NotBlank(message = "密码不能为空")
    @JsonView(UserDetailView.class)
    private String password;

    //@Past(message = "生日必须是过去的时间")
    @JsonView(UserSimpleView.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

}
