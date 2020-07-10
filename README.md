# core

 **MyBatis-Plus的查询扩展,前端传递SearchModel后端进行解析与拼接**

## 注意:

 - 前端如果是排序参数是UserName,因为本人使用的是MySql,为了命名规范所以后面会自动转化为user_name,最多支持UserAbCd转为user_ab_cd,如果不需要请注释掉QueryWrapperUtils的orders方法,或不够的话自行修改
 - 参数组成请自行查看代码,写有注释,也有一个简单的例子

## Postman请求示例

- http://192.168.1.15:9999/test/query,请将地址换成自己的

```json
{
    "name":"id,code",
    "sort":"asc,asc",
    "info":[
        {
            "connect":1,
            "items":[
                {
                    "field":"id",
                    "method":3,
                    "value":"990",
                    "connect":1         
                }
            ]
        },
        {
            "connect":2,
            "items":[
                {
                    "field":"name",
                    "method":9,
                    "value":"99",
                    "connect":1         
                }
            ]
        }
    ],
    "page":{
        "current":1,
        "size":100
    }
}
```

