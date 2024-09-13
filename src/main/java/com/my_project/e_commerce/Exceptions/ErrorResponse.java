package com.my_project.e_commerce.Exceptions;

import jakarta.validation.constraints.AssertFalse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

 public LocalDate Date;
 public Boolean success;
 public String message;
 private List<?> Details;

 public ErrorResponse(String message , List<String> details){
     this.Date = LocalDate.now();
     this.success = Boolean.FALSE;
     this.message = message;
     this.Details = details;
 }
}
