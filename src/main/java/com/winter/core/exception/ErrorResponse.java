/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.exception;

import com.winter.core.common.util.DateUtils;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 错误响应数据
 *
 * @author likailee.llk
 * @version ErrorResponse.java 2020/11/27 Fri 9:18 PM likai
 */
@Data
@NoArgsConstructor
@ToString
public class ErrorResponse {
    private String timeStamp;
    private int status;
    private String message;
    private String url;

    public ErrorResponse(int status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url;
        this.timeStamp = DateUtils.now();
    }
}
