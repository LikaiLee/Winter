/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.common;

import io.netty.util.AsciiString;

/**
 * HTTP 相关常量
 *
 * @author likailee.llk
 * @version HttpConstants.java 2020/11/26 Thu 4:56 PM likai
 */
public class HttpConstants {
    public static final String FAVICON = "/favicon.ico";
    public static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    public static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
    public static final AsciiString CONNECTION = AsciiString.cached("Connection");
    public static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");

    public static final AsciiString APPLICATION_JSON = AsciiString.cached("application/json");

    private HttpConstants() {
    }
}