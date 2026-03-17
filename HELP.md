### **项目概要**

#### **简介**

林长制综合管理系统是一套应用于林业管理领域的平台。主要功能包括对人员的调度；森林资源及特殊地点的标注；森林资源数据化地图。

#### **使用技术**

后端：SpringBoot MybatisPlus Mysql

前端：Vue ElementUI Axios leaflet

#### **角色划分**

超级管理员

管理员

林长

监护员

巡护员

#### **项目功能**

##### 1.用户管理模块

用户管理模块包含登录、登出、添加账号、修改账号和删除账号等功能，用于对用户的管理。

登录，使用账号和密码与验证码登录，验证码严格大小写。

登出，登出系统并清除sesion。

添加账号，由林长及上级角色添加账号，密码默认电话号码.

修改账号，由本人修改账号和密码。

删除账号，由林长及上级角色删除较之下级的账号。

 

##### 2.通知管理模块

通知管理模块包含通知查看、通知发送和通知删除等功能，用于通知管理。

通知查看，查看对自己和对自己单位的通知。

通知发送，由巡护员及上级角色发送通知，可选范围为自己林地的下级人员，管理员及上级角色可以发送不同林地。

通知删除，由本人删除通知。

##### 3. 标注管理模块

标注管理模块包含添加标注、查看标注、删除标注、添加标注实例、查看标注实例和删除标注实例等功能，用与管理标注。

添加标注，由管理员及上级角色添加标注，标注包括图标和提示信息。

查看标注，由管理员及上级角色查看标注。

删除标注，由管理员及上级角色删除标注。

添加标注实例，由巡护员及上级角色添加标注实例，标注实例包括坐标、标注类型和具体信息。

查看标注实例，由巡护员及上级角色。

删除标注实例，由本人或管理员及上级角色删除标注实例。

 

##### 4. 任务管理模块

任务管理模块包含添加任务、查看任务和提交任务等功能，用于管理任务。

添加任务，由监管员及上级角色对下级角色发起任务，任务可为普通任务和事件任务和巡护任务。

查看任务，包括自己的任务，自己发送的任务及巡护任务。

提交任务，提交内容包处理报告及处理图片。

 

##### 5. 林地管理模块

林地管理模块包含添加林地和查看林地等功能，用于林地管理。

添加林地，由超级管理员添加林地。

查看林地，查看林地范围。

 

##### 6. 文件管理模块

文件管理模块包含上传图片、显示图片和生成验证码等功能，用于文件管理

上传图片，图片大小不大于5mb，格式为jpg/png，默认存储为png

显示图片，通过uuid图片名下载图片

生成验证码，生成四位数字或字母的验证码



### 产品原型

----

##### 数据库设计

##### 公共字段

| 字段名      | 字段类型 | 约束                | 字段注释 | 备注         |
| ----------- | -------- | ------------------- | -------- | ------------ |
| id          | char(20) | primary key         | ID       | 雪花算法     |
| is_delete   | 数字     | default 0，not null | 逻辑删除 | 0存在，1删除 |
| create_time | DateTime | not null            | 创建时间 |              |
| create_user | char(20) | not null            | 创建人   | 创建人id     |
| update_time | DateTime | not null            | 修改时间 |              |
| update_user | char(20) | not null            | 修改人   | 修改人id     |
|             |          |                     |          |              |

##### 账号 accout

| 字段名                  | 字段类型 | 约束             | 字段注释                | 备注                             |
| ----------------------- | -------- | ---------------- | ----------------------- | -------------------------------- |
| user                    | char(20) | not null，unique | 账号                    |                                  |
| password                | char(20) | not null         | 密码                    |                                  |
| personal_information_id | char(20) | not null，unique | personal_information ID |                                  |
|                         |          |                  |                         |                                  |
|                         |          |                  |                         |                                  |

##### 个人信息 personal_information

| 字段名        | 字段类型 | 约束                | 字段注释 | 备注                             |
| ------------- | -------- | ------------------- | -------- | -------------------------------- |
| username      | char(20) | not null            | 名字     |                                  |
| sex           | char(20) | not null            | 性别     | 0男，1女                         |
| personal_img  | char(40) | not null            | 图片名称 |                                  |
| woods_id      | char(20) | not null            | 林地 ID  |                                  |
| position      | int      | not null            | 职位     | 0管理员，1林长，2监管员，3巡护员 |
| entry_time    | DateTime | not null            | 入职时间 |                                  |
| status        | int      | default 0，not null | 状态     | 0空闲，1工作中                   |
| phone_number  | char(20) | not null，unique    | 电话号码 |                                  |
| superior_id   | char(20) | not null            | 上级 id  | 个人信息 id                      |
|               |          |                     |          |                                  |

##### 林地 woods

| 字段名        | 字段类型 | 约束             | 字段注释    | 备注 |
| ------------- | -------- | ---------------- | ----------- | ---- |
| woods_name    | char(20) | not null，unique | 林地名称    |      |
| woods_number  | char(20) | not null，unique | 林地编号    |      |
| woods_geojson | text     | not null         | 林地geojson |      |
|               |          |                  |             |      |

##### 标注 label

| 字段名       | 字段类型 | 约束             | 字段注释 | 备注                 |
| ------------ | -------- | ---------------- | -------- | -------------------- |
| label_name   | char(20) | not null，unique | 标注名称 |                      |
| label_img    | char(40) | not null         | 标注图标 |                      |
| label_type   | int      | not null         | 类型     | 0普通标注，1事件标注 |
| label_remark | text     | not null         | 描述     |                      |
|              |          |                  |          |                      |

##### 标注实例 label_example

| 字段名                 | 字段类型      | 约束                 | 字段注释   | 备注             |
|---------------------|-----------|--------------------|--------| ---------------- |
| label_id            | char(20)  | not null           | 标注id   |                  |
| example_status      | int       | default 0，not null | 标注实例状态 | 0待完成，1已完成 |
| example_content     | text      | not null           | 标注内容   |                  |
| example_imgs        | char(200) |                    | 实例图片组  | 限制5张图片      |
| label_personal_id   | char(20)  | not null           | 标注人id  |                  |
| deal_with_person_id | char(20)  |                    | 处理人id  |                  |
| deal_with_content   | text      |                    | 处理结果   |                  |
| deal_with_imgs      | char(200) |                    | 处理图片   | 限制5张图片      |
| deal_with_time      | DateTime  |                    | 处理时间   |                  |
| label_geojson       | text      | not null           |        |                  |

##### 巡护 patrol

| 字段名              | 字段类型      | 约束                  | 字段注释  | 备注        |
|------------------|-----------|---------------------|-------|-----------|
| patrol_person_id | char(20)  | not null            | 巡护人id |           |
| start_time       | DateTime  |                     | 开始时间  |           |
| end_time         | DateTime  |                     | 结束时间  |           |
| patrol_duration  | float     |                     | 巡护时长  |           |
| patrol_mileage   | float     |                     | 巡护里程  |           |
| patrol_content   | text      |                     | 巡护报告  |           |
| patrol_imgs      | char(200) |                     | 巡护图片组 | 限制5张图片    |
| patrol_area      | text      | not null            | 巡护区域  |           |
| patrol_status    | int       | not null, default 0 | 巡护状态  | 0进行中，1已完成 |

##### 任务 task

| 字段名                | 字段类型 | 约束                | 字段注释     | 备注             |
| --------------------- | -------- | ------------------- | ------------ | ---------------- |
| send_person_id        | char(20) | not null            | 发起人       |                  |
| task_type             | int      | not null            | 任务类型     | 0普通，1事件     |
| label_id              | char(20) |                     | 事件id       |                  |
| task_deadline         | int      | not null            | 任务期限     |                  |
| task_status           | int      | default 0，not null | 任务状态     | 0待完成，1已完成 |
| task_content          | text     | not null            | 任务内容     |                  |
| reception_person_id   | char(20) | not null            | 接收人       |                  |
| task_report           | text     |                     | 任务报告     |                  |
| task_imgs             | char(20) |                     | 任务图片组   | 限制5张图片      |
| task_completion       | DateTime |                     | 任务完成时间 |                  |
|                       |          |                     |              |                  |

##### 通知 inform

| 字段名         | 字段类型 | 约束     | 字段注释 | 备注                                       |
| -------------- | -------- | -------- | -------- | ------------------------------------------ |
| send_person_id | char(20) | not null | 发起人   |                                            |
| inform_title   | char(60) | not null | 通知标题 | 限制60字                                   |
| inform_content | text     | not null | 通知内容 |                                            |
| woods_id       | char(20) |          | 林地id   | 通知范围                                   |
| position       | int      | not null | 职位     | 通知范围，0管理员，1林长，2监管员，3巡护员 |
|                |          |          |          |                                            |

---



#### 界面展示

##### 1、登录

![image-20230619143645836](assets/image-20230619143645836.png)

##### 2、主界面

![image-20230619150438520](assets/image-20230619150438520.png)

###### 2.1、登出

![image-20230619150602055](assets/image-20230619150602055.png)

###### 2.2、个人信息及修改密码

![image-20230619150803732](assets/image-20230619150803732.png)

![image-20230619150832868](assets/image-20230619150832868.png)

###### 2.3、侧边栏

![image-20230619150929336](assets/image-20230619150929336.png)

##### 3、地图

![image-20230619150959193](assets/image-20230619150959193.png)

###### 3.1、更改标注显示状态

![image-20230619151049174](assets/image-20230619151049174.png)

###### 3.2、添加标注

![image-20230619151124817](assets/image-20230619151124817.png)

###### 3.3、添加林地

![image-20230619151147600](assets/image-20230619151147600.png)

###### 3.4、显示标注详细信息

![image-20230619151233734](assets/image-20230619151233734.png)

###### 3.5、提交事件

![image-20230619151406988](assets/image-20230619151406988.png)



###### 4、人员

![image-20230619151427324](assets/image-20230619151427324.png)

###### 4.1、添加人员

![image-20230619151450104](assets/image-20230619151450104.png)

###### 4.2、详细信息

![image-20230619151511773](assets/image-20230619151511773.png)

###### 4.3、派遣任务

![image-20230619151529626](assets/image-20230619151529626.png)

###### 4.4、删除人员

![image-20230619151554461](assets/image-20230619151554461.png)



##### 5、任务

![image-20230619151640193](assets/image-20230619151640193.png)

###### 5.1、提交任务

![image-20230619151659147](assets/image-20230619151659147.png)

###### 5.2、查看派遣任务

![image-20230619151725756](assets/image-20230619151725756.png)

###### 5.3、查询巡护任务

![image-20230619151803110](assets/image-20230619151803110.png)

###### 5.4、开始巡护

![image-20230619151854936](assets/image-20230619151854936.png)

###### 5.5、结束巡护

![image-20230619152008877](assets/image-20230619152008877.png)

##### 6、通知

![image-20230619152031177](assets/image-20230619152031177.png)

###### 6.1、查看发布的通知

![image-20230619152056858](assets/image-20230619152056858.png)

###### 6.2、删除通知

![image-20230619152115757](assets/image-20230619152115757.png)

###### 6.3、添加通知

![image-20230619152132928](assets/image-20230619152132928.png)

##### 7、标注

![image-20230619152147366](assets/image-20230619152147366.png)

###### 7.1、删除标注

![image-20230619152210736](assets/image-20230619152210736.png)

###### 7.2、添加标注

![image-20230619152236905](assets/image-20230619152236905.png)

##### 8、巡护情况

![image-20230619152314718](assets/image-20230619152314718.png)

###### 8.1、详细巡护情况

![image-20230619152330136](assets/image-20230619152330136.png)