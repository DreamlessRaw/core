package com.dreamless.example.mapper

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.dreamless.example.entity.Test
import org.apache.ibatis.annotations.Param

interface TestMapper : BaseMapper<Test> {
    fun paged(page: Page<Test>, @Param("ew") queryWrapper: QueryWrapper<Test>):IPage<Test>
}
