package com.dreamless.example.entity

import com.baomidou.mybatisplus.annotation.TableName

@TableName("test")
class Test {

    constructor()
    constructor(code: String, name: String) {
        this.code = code
        this.name = name
    }

    var id: Int = 0
    var code: String = ""
    var name: String = ""
}