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
    /**
     * 发生错误的错误时间
     */
    private String timeStamp;
    /**
     * 状态码
     */
    private int status;
    /**
     * 错误原因
     */
    private String error;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 请求的 URL
     */
    private String url;

    public ErrorResponse(int status, String error, String message, String url) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.url = url;
        this.timeStamp = DateUtils.now();
    }

}
