# Winter

Winter is a simple implementation of Spring Boot.

## Features

### 1. Http Server

Netty served as default embedded web server, and Winter will start on default port `8080`.

### 2. Http Request

* [x] `@RestController`: define a restful Controller.
* [x] `@GetMapping`: handle `Get` request.
* [x] `@PostMapping`: handle `Post` request.
* [x] `@RequestParam`: get parameters from url query string for `Get` request.
* [x] `@RequestBody`: get request entity from `Post` request body.
* [x] `@PathVariable`: extract parameters from URI path.


### 3. IoC

* [x] `@Autowired`: inject a bean class configured as a property of another bean.
* [x] `@Component`: mark beans as `IoC` managed components. 
* [x] `@Qualifier`: determine which bean to inject when there are multiple beans of same type.


### 4. AOP

* [x] `@Aspect`: declare a class as `aspect`
* [x] `@Pointcut`: Pointcut is expressions that are matched with join points to determine whether `advice` needs to be executed or not
* [ ] `@Around`
* [x] `@Before`: annotated methods run exactly `before` the all methods matching with pointcut expression
* [x] `@After`: annotated methods run exactly `after` the all methods matching with pointcut expression
* [ ] `@AfterReturning`
* [ ] `@AfterThrowing`