/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter;

import com.winter.server.HttpServer;

/**
 * Winter is named because it is written in winter.
 *
 * @author likailee.llk
 * @version WinterApplication.java 2020/11/25 Wed 4:20 PM likai
 */
public class WinterApplication {
    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.start();
    }
}
