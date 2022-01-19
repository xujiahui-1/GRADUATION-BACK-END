package com.xujiahui.commonutils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XException extends  RuntimeException{
        private Integer code;
        private String message;
}
