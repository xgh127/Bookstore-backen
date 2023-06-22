# bookstore-backen
## 项目介绍
### 项目简介
这是SJTU互联网应用开发技术课程的大作业，一个简单的网上书店的后端。
### 项目功能
#### 用户功能
- 用户注册、登录
- 用户修改个人信息
- 用户查看书籍列表
- 用户查看书籍详情
- 用户添加购物车
- 用户查看购物车
- 用户删除购物车中的书籍
- 用户提交订单
- 用户查看订单
- 用户删除订单
- 用户查看订单列表
- 用户查看自己的消费情况，包括一定时间范围内的消费总额，以及购买书籍总数，书籍数量的总数
- #### 管理员功能
- 管理员登录
- 管理员查看用户列表
- 管理员禁止某些用户登录
- 管理员查看所有的订单列表
- 管理员编辑书籍信息
- 管理员删除书籍
- 管理员添加书籍
- 管理员进行销售额统计，可以按照时间统计，也可以按照书籍统计
- 管理员可以统计用户的消费情况，包括一定时间范围内用户的消费总额
### 项目技术
#### 前端
- React框架 + Ant Design + js+html+css
- 使用fecth进行前后端交互
#### 后端
- SpringBoot
- MySQL
- Hibernate
- Spring Data JPA

### 项目运行
#### 前端
见package.json
#### 后端
用的java版本是8
#### 数据库
数据库建库文件为bookstore.sql，具体数据库设计请自行运行sql文件查看- 
- 需要注意的是：User和User_auth需要分开，user_auth存储密码等敏感但是不常用的信息，好处如下：
  - 1.性能方面：懒加载，可以减少数据库的查询次数和数据传输的大小，提高性能
  - 2.安全方面：用户的敏感信息不会被泄露（虽然我觉得这个没什么用，因为我还是需要手动去除密码）
  - 3.编程原则：更加复合面向对象编程的原则，将用户的信息分成两个类，一个是User，一个是User_auth，分别存储不同的信息
## 课程说明
### 课程要求
- 课程要求：
  - 80分 4 次迭代 每次20分
  - 20分 10次作业 每次2分
- 课程内容：完成整个书城开发的所有相关技术，包括但不限于：
  - 前端：HTML、CSS、JavaScript、React、Ant Design
  - 后端：Java、Spring Boot、Spring Data JPA、Hibernate、MySQL
  - 其他：Git、GitHub、Maven、Postman、RESTful API
- 课程考核：
  - 课堂考勤：随机，老师喜欢线下发带水印的卷子进行随机签到，无法签到直接期末总评-2分
### 课程安排
  两周3次课，每次2节课

