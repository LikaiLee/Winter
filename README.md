# Winter

`Winter` is a simple implementation of Spring Boot.

## Features

### 1. Http Server

`Netty` served as default embedded web server, and `Winter` will start on default port `8080`.


### 2. Http Request

* [x] `@RestController`: define a restful Controller.
* [x] `@GetMapping`: handle `Get` request.
* [x] `@PostMapping`: handle `Post` request.
* [x] `@RequestParam`: get parameters from url query string for `Get` request.
* [x] `@RequestBody`: get request entity from `Post` request body.
* [x] `@PathVariable`: extract parameters from URI path.


### 3. Boot

* [x] `@SpringBootApplication`: automatically package scanning
* [x] `@ComponentScan`: Enables the component-scanning so that the controller class and other components will be automatically registered


### 4. IoC

* [x] `@Autowired`: inject a bean class configured as a property of another bean.
* [x] `@Component`: mark beans as `IoC` managed components. 
* [x] `@Qualifier`: determine which bean to inject when there are multiple beans of same type.
* [x] resolve circular dependency


### 5. AOP

* [x] `@Aspect`: declare a class as `aspect`
* [x] `@Order`: ordering aspects, the lowest number the more important the aspect is, so it will be executed first
* [x] `@Pointcut`: Pointcut is expressions that are matched with join points to determine whether `advice` needs to be executed or not
* [x] `@Around`: ensures that an advice can run before and after the method execution 
* [x] `@Before`: annotated methods run exactly `before` the all methods matching with pointcut expression
* [x] `@After`: annotated methods run exactly `after` the all methods matching with pointcut expression
* [ ] `@AfterReturning`
* [ ] `@AfterThrowing`


### 6. Interceptor / Aspect

* [x] implement interceptor by JDK Proxy or Cglib Proxy
* [x] define interceptor orders
* [x] multiple interceptors


### 7. Configuration

* [x] `@Value`: inject value into fields
* [x] enable read configuration from `*.properties` or `*.yaml` or `*.yml` files

### 8. Exception

* [x] response error message according to the specific exception
