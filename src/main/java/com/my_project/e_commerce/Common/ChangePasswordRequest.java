package com.my_project.e_commerce.Common;

import lombok.Data;

@Data
public class ChangePasswordRequest {
 private String oldPassword;
 private String newPassword;
 private String RepeatNewPassword;
}
