package com.dreamless.example.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.dreamless.example.entity.Test
import com.dreamless.example.mapper.TestDao
import org.dreamless.ApiResult
import org.dreamless.SearchModel
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("test")
class TestController {

    @Resource
    lateinit var testDao: TestDao

    @PostMapping("add")
    fun add() {
        for (i in 1..1000) {
            testDao.insert(Test(i.toString(), "${i}号"))
        }
    }

    @PostMapping("query")
    fun query(@RequestBody searchModel: SearchModel<Test>): ApiResult<IPage<Test>> {
        return ApiResult.success(testDao.selectPage(searchModel.page, searchModel.queryWrapper()))
    }
}