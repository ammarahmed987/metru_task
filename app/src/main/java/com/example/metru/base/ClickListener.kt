package com.example.metru.base

interface ClickListener {
    fun <T> onClick(data :T,type: String = "",createNested:Boolean = false)
}