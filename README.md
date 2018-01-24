# 总控--分校管理--课程管理接口文档
# 总控课程管理接口文档
## 该接口文档只针对总控中分校管理的课程管理文档，其余功能文档请依照其他文件。

## 一、总控--课程管理--首页/查询按钮
### 请求名称：/schoolCourse/query
### 请求方式：get
### 请求参数：

请求参数 | 类型|是否必填|备注
---|---|---|---
pageNumber |int| 否|默认当前第几页
pageSize|int|否|默认分页条数
courseId|int|否|课程名称对应的ID
authenticateGrade|string|否|鉴定等级对应的ID
authorityId|int|否|发证机构对应ID
status|string|否|课程状态对应value
### 响应案例：
               {
                page: 1,
                rowNum: 10,
                records: 13,
                total: 2,
                rows: [
                        {
                        courseId: 1,
                        authorityId: 1,
                        courseName: "母婴护理",
                        sortNo: 1,
                        isUsable: "1",
                        status: "01",
                        totalHours: 50,
                        examType: "03",
                        remarks: "从事为孕产妇在分娩前后及新生儿、婴儿提供生活护理服务",
                        authenticateGrade: "01",
                        addTime: "2017-09-25 18:30:21",
                        addAccount: "admin",
                        modifyTime: "2017-10-05 02:02:01",
                        modifyAccount: "admin",
                        certName: "母婴护理",
                        authorityName: "上海市人力资源和社会保障局",
                        orgName: "专项职业能力",
                        statusName: "上架"
    },
## 二、总控--课程管理--课程名称下拉框
### 请求名称：/params/coursesBaseInfo
### 请求方式：get/post
### 请求参数：无
### 响应案例：
                [
                    {
                    courseId: 1,
                    courseName: "母婴护理"
                    },
                    {
                    courseId: 2,
                    courseName: "家政服务"
                    },
## 三、总控--课程管理--鉴定等级下拉框
### 请求名称：/params/cfgParamsOfGrade
### 请求方式：get/post
### 请求参数：无
### 响应案例：
                [
                    {
                    text: "专项职业能力",
                    value: "01"
                    },
                    {
                    text: "初级/五级",
                    value: "02"
                    },
                    {
                    text: "中级/四级",
                    value: "03"
                    },
                    {
                    text: "高级/三级",
                    value: "04"
                    },
                    {
                    text: "技师/二级",
                    value: "05"
                    },
                    {
                    text: "高级技师/一级",
                    value: "06"
                    }
    ]
## 四、总控--课程管理--发证机构下拉框
### 请求路径：/params/authorityName
### 请求方式：get/post
### 请求参数：无
### 响应案例：
                [
                    {
                    authorityId: 1,
                    authorityName: "上海市人力资源和社会保障局",
                    remark: "主要",
                    status: "01",
                    addTime: "2017-08-03 20:19:20",
                    addAccount: "admin",
                    statusName: "上架"
                    },
                    {
                    authorityId: 2,
                    authorityName: "上海家策商学院",
                    remark: "本校",
                    status: "01",
                    addTime: "2017-08-03 20:19:20",
                    addAccount: "admin",
                    statusName: "上架"
                    }
                ]
## 五、总控--课程管理--课程状态下拉框
### 请求路径：/params/cfgParamOfStatus
### 请求方式：get/post
### 请求参数：无
### 响应案例：
                [
                    {
                    text: "上架",
                    value: "01"
                    },
                    {
                    text: "下架",
                    value: "02"
                    }
    ]

## 六、总控--课程管理--考试形式下拉框
### 请求路径：/params/cfgParamOfExamType
### 请求方式：get/post
### 请求参数：无
### 响应案例：
                [
                    {
                    text: "理论",
                    value: "01"
                    },
                    {
                    text: "实操",
                    value: "02"
                    },
                    {
                    text: "理论+实操",
                    value: "03"
                    },
                    {
                    text: "其他",
                    value: "04"
                    }
    ]
## 七、总控--课程管理--新增课程页面
### 请求路径：/schoolCourse/addCourse
### 请求方式：get/post
### 请求参数：无
### 响应案例：返回到modules/schoolCourse/addCourse jsp页面

## 八、总控--课程管理--新增课程
### 请求路径：/schoolCourse/saveCourse
### 请求方式：get/post
### 请求参数：

请求参数 | 类型|是否必填|备注
---|---|---|---
courseName | string|是|课程名称
certName | string|是|证书名称
authenticateGrade|string|是|鉴定等级对应的ID
totalHours|int|是|总课时
examType|string|是|考试形式对应ID
authorityId|int|是|发证机构ID
remark|string|否|备注
status|string|是|课程状态对应ID
### 响应案例：
                {"success":true,"msg":"新增成功"}

## 九、总控--课程管理--修改课程页面
### 请求路径：/schoolCourse/getCourseBase
### 请求方式：get/post
### 请求参数：

请求参数 | 类型|是否必填|备注
---|---|---|---
courseId| int|是|课程ID
### 响应案例：将 course对象 返回给modules/schoolCourse/updateCourse jsp页面

## 十、总控--课程管理--修改课程
### 请求路径：/schoolCourse/updateCourse
### 请求方式：get/post
### 请求参数：

请求参数 | 类型|是否必填|备注
---|---|---|---
courseId | int|是|课程ID
totalHours | int|是|总课时
examType|string|是|考试形式对应的ID
remark|string|否|备注
status|string|是|课程状态对应ID
### 响应案例：
                {"success":true,"msg":"修改成功"}
