package org.dreamless

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page

// region SearchModel
/**
 * @param name:排序字段,多个用逗号","隔开
 * @param sort:排序方式,多个用逗号","隔开
 * @param page:分页
 * @param info:条件集合
 */
data class SearchModel<T>(var name: String, var sort: String,
                          var info: MutableList<SearchInfo> = mutableListOf(),
                          var page: Page<T>
) {
    /**
     * 查询所有
     */
    fun queryWrapper(): QueryWrapper<T> {
        return QueryWrapperUtils.queryWrapper(this)
    }

    /**
     * 只查询未被删除的
     */
    fun queryWrapperDel(): QueryWrapper<T> {
        this.info.add(SearchInfo(Connect.And.value(), mutableListOf(SearchItem("deleted", Method.Eq.value(), "0", Connect.And.value()))))
        return QueryWrapperUtils.queryWrapper(this)
    }

    /**
     * 添加单个条件
     */
    fun addItem(connect: Connect, field: String, method: Method, value: String, ic: Connect) {
        this.info.add(SearchInfo(connect = connect.value(),
            items = mutableListOf(SearchItem(field = field, method = method.value(), value = value, connect = ic.value()))
        ))
    }

    /**
     * 添加多个条件
     */
    fun addItems(connect: Connect, items: MutableList<SearchItem>) {
        this.info.add(SearchInfo(connect = connect.value(), items = items))
    }
}
// endregion

/**
 * @param connect:And或Or
 * @param items:条件详情
 */
data class SearchInfo(var connect: Int,
                      var items: MutableList<SearchItem>)

/**
 * @param field:字段
 * @param method:操作符
 * @param value:值
 * @param connect:And或Or
 */
data class SearchItem(var field: String, var method: Int, var value: String,
                      var connect: Int)

/**
 * 操作符
 * 示例请查看 https://mp.baomidou.com/guide/wrapper.html#abstractwrapper
 */
enum class Method {
    /**
     * 等于 <>
     */
    Eq {
        override fun value(): Int {
            return 1
        }
    },
    /**
     * 不等于 <>
     */
    Ne {
        override fun value(): Int {
            return 2
        }
    },
    /**
     * 大于 >
     */
    Gt {
        override fun value(): Int {
            return 3
        }
    },
    /**
     * 大于等于 >=
     */
    Ge {
        override fun value(): Int {
            return 4
        }
    },
    /**
     * 小于 <
     */
    Lt {
        override fun value(): Int {
            return 5
        }
    },
    /**
     * 小于等于 <=
     */
    Le {
        override fun value(): Int {
            return 6
        }
    },
    /**
     * BETWEEN 值1 AND 值2
     */
    Between {
        override fun value(): Int {
            return 7
        }
    },
    /**
     * NOT BETWEEN 值1 AND 值2
     */
    NotBetween {
        override fun value(): Int {
            return 8
        }
    },
    /**
     * LIKE '%值%'
     */
    Like {
        override fun value(): Int {
            return 9
        }
    },
    /**
     * NOT LIKE '%值%'
     */
    NotLike {
        override fun value(): Int {
            return 10
        }
    },
    /**
     * LIKE '%值'
     */
    LikeLeft {
        override fun value(): Int {
            return 11
        }
    },
    /**
     * LIKE '值%'
     */
    LikeRight {
        override fun value(): Int {
            return 12
        }
    },
    /**
     * 字段 IS NULL
     */
    IsNull {
        override fun value(): Int {
            return 13
        }
    },
    /**
     * 字段 IS NOT NULL
     */
    IsNotNull {
        override fun value(): Int {
            return 14
        }
    },
    /**
     * 字段 IN (value.get(0), value.get(1), ...)
     */
    In {
        override fun value(): Int {
            return 15
        }
    },
    /**
     * 字段 NOT IN (v0, v1, ...)
     */
    NotIn {
        override fun value(): Int {
            return 16
        }
    },
    /**
     * 特殊时间端处理(天)
     */
    Day {
        override fun value(): Int {
            return 17
        }
    },
    /**
     * 特殊时间端处理(周)
     */
    Week {
        override fun value(): Int {
            return 18
        }
    },
    /**
     * 特殊时间端处理(月)
     */
    Month {
        override fun value(): Int {
            return 19
        }
    };

    abstract fun value(): Int
}

/**
 * group连接符
 * 示例请查看 https://mp.baomidou.com/guide/wrapper.html#abstractwrapper
 */
enum class Connect {
    /**
     * AND 嵌套
     */
    And {
        override fun value(): Int {
            return 1
        }
    },
    /**
     * OR
     */
    Or {
        override fun value(): Int {
            return 2
        }
    };

    abstract fun value(): Int
}
