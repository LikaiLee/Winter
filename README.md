# Winter

Winter is a simple implementation of Spring Boot.

## Features

### 1. Http Server

Netty served as default embedded web server, and Winter will start on default port `8080`.

### 2. Http Request

* [x] `@RestController`: Restful Controller.
* [x] `@GetMapping`: handle `Get` request.
* [x] `@PostMapping`: handle `Post` request.
* [x] `@RequestParam`: get parameters from url query string for `Get` request.
* [x] `@RequestBody`: get request entity from `Post` request body.
* [x] `@PathVariable`: extract parameters from URI path.
