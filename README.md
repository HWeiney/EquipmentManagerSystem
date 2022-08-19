# 《设备管理系统》设计文档

[TOC]

# 一、项目介绍

本项目是连接了MySql数据库（MariaDB数据库），采用Java-Swing技术，实现了GUI设计

# 二、Login设计

## 1.账户登录（登录和退出）√

### a.超级管理员（bossFrame）窗口√

1. 用户管理功能模块
2. 购买设备系统
3. 借用设备系统
4. 报修设备系统
5. 设备仓库系统

### b.设备管理员（managerFrame）窗口√

1. 购买设备系统

2. 借用设备系统

3. 报修设备系统

4. 设备仓库系统

   <img src="C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20220620162157851.png" alt="image-20220620162157851" style="zoom:80%;" />

### c.创建数据库并连接√

1. ## 创建User表√

> 第一阶段完成
>
> <img src="C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20220603170309748.png" alt="image-20220603170309748" style="zoom: 80%;" />

# 三、功能设计

## 1.超级管理员对用户信息进行管理√

1. 5个JTextField，分别是userid、username、password、repassword、phone，1个下拉框
2. 6个JButton，分别是刷新、添加用户、删除用户、修改用户、查询用户，返回上一界面
3. 添加用户（添加信息）
   1. 要求userid、username、password、repassword、note不为空
   1. createtime、updatetime默认系统时间，不可更改
   1. userid已存在、username重名、密码不一致则报错
   1. 点一次添加用户按钮，同时清空文本框内容
4. 删除用户（删除信息）
   1. 根据userid删除（userid不为空）
   2. 超级管理员不可以通过UI界面删除，只允许去数据库进行操作
   3. 删除前进行确认
   4. 若删除超管或不存在的用户->删除失败
5. 修改用户（修改信息）
   1. userid，username存在的情况下进行修改
   2. 修改后判断userid、username是否唯一
   3. updatetime进行更新
6. 查询用户
   1. 根据userid或者username查询
   2. 弹窗显示userid，username，password

## 2.实现设备购买界面√

![image-20220605194226562](C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20220605194226562.png)

1. 功能需求
   - [x] 表格显示设备清单列表（设备号+设备名称+库存数量）
   - [x] 每次购买完成后，自动刷新订单编号(年月日+第n单)，并且更新设备库存列表的库存数量
   - [x] 数据插入时，自动插入购买日期，设备状态
   - [x] 点击购买弹出确认弹窗
2. 所需组件
   - [x] 1个JTable（设备库存列表）
   - [x] 2个JButton（点击购买、退出系统）
   - [x] 8个JLabel（《设备库存列表》、订单编号，购买时间、c和d的内容）
   - [x] 3个JComboBox（设备名称、购买人员名称、购买数量）
   - [x] 3个JTextField（购买价格、供应商姓名、供应商联系方式）
   - [x] 3个JPanel

<img src="C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20220605235511890.png" alt="image-20220605235511890" style="zoom:80%;" />

## 3.实现设备仓库管理界面√

1. 功能需求：
   1. 展示仓库在库设备+借用设备+报修设备数量
   2. 查询所有设备订单
   3. 查询所有设备借用
   4. 查询所有设备报修
2. 所需组件：
   1. 4个JButton
   2. 1个主窗口，三个表格窗口
   3. JTabel

<img src="C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20220609191628867.png" alt="image-20220609191628867" style="zoom:80%;" />

## 4.借用设备管理界面√

1. 套用 设备购买系统 的界面

   <img src="C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20220620161954663.png" alt="image-20220620161954663" style="zoom:80%;" />

## 5.设备报修管理界面√

<img src="C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20220620162103622.png" alt="image-20220620162103622" style="zoom:80%;" />







