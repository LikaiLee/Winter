/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.exception;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.Getter;

/**
 * 需要返回给客户端的错误信息
 *
 * @author likailee.llk
 * @version ResponseException.java 2020/12/14 Mon 7:34 PM likai
 */
@Getter
public class ResponseException extends RuntimeException {
    private HttpResponseStatus httpResponseStatus;

    public ResponseException(String message, HttpResponseStatus httpResponseStatus) {
        super(message);
        this.httpResponseStatus = httpResponseStatus;
    }
}
