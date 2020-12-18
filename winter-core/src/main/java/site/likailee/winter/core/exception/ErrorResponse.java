/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.exception;

import site.likailee.winter.core.common.util.DateUtils;

/**
 * 错误响应数据
 *
 * @author likailee.llk
 * @version ErrorResponse.java 2020/11/27 Fri 9:18 PM likai
 */
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

    public ErrorResponse() {
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
