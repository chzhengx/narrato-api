<div align="center">

<a href="./logo.png"><img src="./logo.png" width="120" height="120" alt="narrato logo"></a>

# Narrato API

**Narrato API** implements the API Endpoints of Narrato, which is a Medium.com clone.

<p align="center">
[![CI Build](https://github.com/chzhengx/narrato-api/actions/workflows/maven.yml/badge.svg)](https://github.com/chzhengx/narrato-api/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=chzhengx_narrato-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=chzhengx_narrato-api)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=chzhengx_narrato-api&metric=coverage)](https://sonarcloud.io/summary/new_code?id=chzhengx_narrato-api)
</p>

</div>

## 技术栈
* [Java 21](https://dev.java/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [Spring Modulith](https://spring.io/projects/spring-modulith)
* [jOOQ](https://www.jooq.org/)
* [PostgreSQL](https://www.postgresql.org/)
* [FlywayDB](https://flywaydb.org/)
* [JUnit 5](https://junit.org/junit5/)
* [Testcontainers](https://testcontainers.com/)
* [Docker Compose](https://docs.docker.com/compose/)

## 前置条件
* JDK 21
* Docker and Docker Compose
* IDE (推荐：[IntelliJ IDEA](https://www.jetbrains.com/idea/))

使用 [SDKMAN](https://sdkman.io/) 安装 JDK

```shell
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk install java 21.0.1-tem
$ sdk install maven
```

验证前置条件

```shell
$ java -version
openjdk version "21.0.1" 2023-10-17 LTS
OpenJDK Runtime Environment Temurin-21.0.1+12 (build 21.0.1+12-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.1+12 (build 21.0.1+12-LTS, mixed mode)

$ docker info
Client:
 Version:    27.0.3
 Context:    desktop-linux
 ...
 ...
Server:
 Server Version: 27.0.3
 ...
 ...

$ docker compose version
Docker Compose version v2.28.1-desktop.1
```

## 如何操作？

```shell
# 克隆仓库
$ git clone https://github.com/sivaprasadreddy/spring-realworld-narrato-api.git
$ cd spring-realworld-narrato-api

# 运行测试
$ ./mvnw test

# 使用 spotless-maven-plugin 自动格式化代码
$ ./mvnw spotless:apply

# 从 IDE 运行/调试应用程序
Run `src/main/java/narrato/ConduitApplication.java` from IDE.

# 使用 Maven 运行应用程序
./mvnw spring-boot:run
```

应用程序配置为使用 Docker Compose 自动启动应用程序依赖项，例如 PostgreSQL。

* PostgreSQL 容器连接属性：
  ```shell
  host: localhost
  port: 65432
  username: postgres
  password: postgres
  database: postgres
  ```
* 应用程序运行在 http://localhost:8080
* Swagger UI: http://localhost:8080/swagger-ui/index.html

## 使用 [Taskfile](https://taskfile.dev/) 工具
Task 是一个任务运行器，可以更方便地运行任意命令。

### 安装

```shell
$ brew install go-task
(or)
$ go install github.com/go-task/task/v3/cmd/task@latest

#验证 task 版本
$ task --version
Task version: 3.35.1
```

### 使用 task 执行各种任务：

```shell

# 运行测试
$ task test

# 使用 spotless-maven-plugin 自动格式化代码
$ task format

# 构建 docker 镜像
$ task build_image

# 在 docker 容器中运行应用程序
$ task start
$ task stop
$ task restart
```
