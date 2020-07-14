package org.dreamless

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import java.util.function.Consumer


object QueryWrapperUtils {

    /**
     * 构造一个空的queryWrapper
     */
    fun <T> queryWrapper(): QueryWrapper<T> {
        return QueryWrapper<T>()
    }

    /**
     * 组装queryWrapper
     * @param searchModel
     * @return QueryWrapper<T>
     */
    fun <T> queryWrapper(searchModel: SearchModel<T>): QueryWrapper<T> {
        val queryWrapper = QueryWrapper<T>()
        //组装排序
        val orders = orders(searchModel.name, searchModel.sort)
        if (orders.count() > 0) {
            orders.forEach { queryWrapper.orderBy(true, it.value, it.key) }
        }
        //组装条件
        if (searchModel.info.count() > 0) {

            searchModel.info.map {
                it.items.map { m ->
                    val strArr =
                        m.field.split("(?<!^)(?=[A-Z])".toRegex()).dropLastWhile { d -> d.isEmpty() }.toTypedArray()
                    when (strArr.size) {
                        1 -> {
                            m.field = strArr[0]
                        }
                        2 -> {
                            m.field = "${strArr[0]}_${strArr[1].toLowerCase()}"
                        }
                        3 -> {
                            m.field = "${strArr[0]}_${strArr[1].toLowerCase()}_${strArr[2].toLowerCase()}"
                        }
                    }
                }
            }

            for (v in searchModel.info) {
                when (v.connect) {
                    Connect.And.value() -> {
                        queryWrapper.and(addConsumer<T>(v.items))
                    }
                    Connect.Or.value() -> {
                        queryWrapper.or(addConsumer<T>(v.items))
                    }
                }
            }
        }
        return queryWrapper
    }

    /**
     * @param searchItems:分组内条件
     * @return Consumer<QueryWrapper<T>>:返回拼接的条件
     */
    private fun <T> addConsumer(searchItems: List<SearchItem>): Consumer<QueryWrapper<T>> {
        return Consumer {
            for (v in searchItems) {
                when (v.connect) {
                    Connect.Or.value() -> {
                        when (v.method) {
                            Method.Eq.value() -> {
                                it.or().eq(v.field, v.value)
                            }
                            Method.Ne.value() -> {
                                it.or().ne(v.field, v.value)
                            }
                            Method.Gt.value() -> {
                                it.or().gt(v.field, v.value)
                            }
                            Method.Ge.value() -> {
                                it.or().ge(v.field, v.value)
                            }
                            Method.Lt.value() -> {
                                it.or().lt(v.field, v.value)
                            }
                            Method.Le.value() -> {
                                it.or().or().le(v.field, v.value)
                            }
                            Method.Between.value() -> {
                                val b = v.value.split(',')
                                it.or().between(v.field, b[0], b[1])
                            }
                            Method.NotBetween.value() -> {
                                val b = v.value.split(',')
                                it.or().notBetween(v.field, b[0], b[1])
                            }
                            Method.Like.value() -> {
                                it.or().like(v.field, v.value)
                            }
                            Method.NotLike.value() -> {
                                it.or().notLike(v.field, v.value)
                            }
                            Method.LikeLeft.value() -> {
                                it.or().likeLeft(v.field, v.value)
                            }
                            Method.LikeRight.value() -> {
                                it.or().likeRight(v.field, v.value)
                            }
                            Method.IsNull.value() -> {
                                it.or().isNull(v.field)
                            }
                            Method.IsNotNull.value() -> {
                                it.or().isNotNull(v.field)
                            }
                            Method.In.value() -> {
                                it.or().`in`(v.field, v.value)
                            }
                            Method.NotIn.value() -> {
                                it.or().notIn(v.field, v.value)
                            }
                            Method.Day.value() -> {
                                it.or().ge(v.field, DateTimeUtils.getStartOfDayToString())
                                    .le(v.field, DateTimeUtils.getEndOfDayToString())
                            }
                            Method.Week.value() -> {
                                it.or().ge(v.field, DateTimeUtils.getStartOfWeekToString())
                                    .le(v.field, DateTimeUtils.getEndOfWeekToString())
                            }
                            Method.Month.value() -> {
                                it.or().ge(v.field, DateTimeUtils.getStartOfMonthToString())
                                    .le(v.field, DateTimeUtils.getEndOfMonthToString())
                            }
                        }
                    }
                    Connect.And.value() -> {
                        when (v.method) {
                            Method.Eq.value() -> {
                                it.eq(v.field, v.value)
                            }
                            Method.Ne.value() -> {
                                it.ne(v.field, v.value)
                            }
                            Method.Gt.value() -> {
                                it.gt(v.field, v.value)
                            }
                            Method.Ge.value() -> {
                                it.ge(v.field, v.value)
                            }
                            Method.Lt.value() -> {
                                it.lt(v.field, v.value)
                            }
                            Method.Le.value() -> {
                                it.le(v.field, v.value)
                            }
                            Method.Between.value() -> {
                                val b = v.value.split(',')
                                it.between(v.field, b[0], b[1])
                            }
                            Method.NotBetween.value() -> {
                                val b = v.value.split(',')
                                it.notBetween(v.field, b[0], b[1])
                            }
                            Method.Like.value() -> {
                                it.like(v.field, v.value)
                            }
                            Method.NotLike.value() -> {
                                it.notLike(v.field, v.value)
                            }
                            Method.LikeLeft.value() -> {
                                it.likeLeft(v.field, v.value)
                            }
                            Method.LikeRight.value() -> {
                                it.likeRight(v.field, v.value)
                            }
                            Method.IsNull.value() -> {
                                it.isNull(v.field)
                            }
                            Method.IsNotNull.value() -> {
                                it.isNotNull(v.field)
                            }
                            Method.In.value() -> {
                                it.`in`(v.field, v.value)
                            }
                            Method.NotIn.value() -> {
                                it.notIn(v.field, v.value)
                            }
                            Method.Day.value() -> {
                                it.and { i ->
                                    i.ge(v.field, DateTimeUtils.getStartOfDayToString())
                                        .le(v.field, DateTimeUtils.getEndOfDayToString())
                                }
                            }
                            Method.Week.value() -> {
                                it.and { i ->
                                    i.ge(v.field, DateTimeUtils.getStartOfWeekToString())
                                        .le(v.field, DateTimeUtils.getEndOfWeekToString())
                                }
                            }
                            Method.Month.value() -> {
                                it.and { i ->
                                    i.ge(v.field, DateTimeUtils.getStartOfMonthToString())
                                        .le(v.field, DateTimeUtils.getEndOfMonthToString())
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 排序
     * @param orderName:排序字段,用逗号","隔开
     * @param orderSort:排序方式,用逗号","隔开
     * @return orders:column,isAsc
     */
    private fun orders(orderName: String, orderSort: String): MutableMap<String, Boolean> {
        //如果存在排序,则加入排序
        val orders = mutableMapOf<String, Boolean>()
        if (orderName.isNotEmpty() && orderSort.isNotEmpty()) {
            val names = orderName.split(',')
            val sorts = orderSort.split(',')
            if (names.size == sorts.size) {
                val ascStr: List<String> = listOf("ASC", "Asc", "asc")
                for ((i, v) in names.withIndex()) {
                    var field: String = ""
                    val arr = v.split("(?<!^)(?=[A-Z])".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    when (arr.size) {
                        1 -> {
                            field = arr[0]
                        }
                        2 -> {
                            field = "${arr[0]}_${arr[1].toLowerCase()}"
                        }
                        3 -> {
                            field = "${arr[0]}_${arr[1].toLowerCase()}_${arr[2].toLowerCase()}"
                        }
                    }
                    orders[field] = ascStr.contains(sorts[i])
                }
                return orders
            } else {
                throw Exception("请确认排序条件！！！")
            }
        }
        return orders
    }

}
