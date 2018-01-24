```
# 总控--学校管理系统--数据库修改信息最终版
### 一、新增总控课程表(总控表需要插入数据)
```
CREATE TABLE course_base_info(
course_id int(11) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
authority_id int(11) DEFAULT NULL COMMENT '发证机构ID',
course_name VARCHAR(128) DEFAULT NULL COMMENT '课程名称',
sort_no int(11) DEFAULT NULL COMMENT '排序',
is_usable VARCHAR(1) DEFAULT '1' COMMENT '是否可用 1--可用 0--不可用',
status VARCHAR(2) DEFAULT '1' COMMENT '课程状态 01--上架 02--下架',
total_hours INT(11) DEFAULT NULL COMMENT '总课时',
exam_type VARCHAR(2) DEFAULT NULL COMMENT'考试形式',
remarks VARCHAR(3072) DEFAULT NULL COMMENT'备注',
authenticate_grade varchar(2) DEFAULT NULL COMMENT '鉴定等级',
cert_name varchar(64) DEFAULT NULL COMMENT'证书名称',
add_time datetime DEFAULT NULL COMMENT '创建时间',
add_account VARCHAR(64) DEFAULT NULL COMMENT '创建账户',
modify_time datetime DEFAULT NULL COMMENT '修改时间',
modify_account varchar(64) DEFAULT NULL COMMENT '修改人',
PRIMARY KEY (course_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='总控基础课程表';
```
### 二、创建课程和机构中间表
```
CREATE TABLE course_institution(
course_id int(11) NOT NULL COMMENT '课程ID',
institution_id int(11) NOT NULL COMMENT '机构ID',
status VARCHAR(2) DEFAULT '0' COMMENT '课程状态0--未开课 1--已开课',
add_time datetime DEFAULT NULL COMMENT '创建时间',
add_account VARCHAR(64) DEFAULT NULL COMMENT '创建账户',
modify_time datetime DEFAULT NULL COMMENT '修改时间',
modify_account varchar(64) DEFAULT NULL COMMENT '修改人'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='总控和机构课程中间表';
```
### 三、learnType插入字段
```
ALTER  TABLE learn_types ADD cert_name varchar(64) DEFAULT NULL COMMENT'证书名称';
ALTER  TABLE learn_types ADD course_base_info_id INT(11) DEFAULT NULL COMMENT'course_base_info表的ID';

```
```