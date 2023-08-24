package com.example.metru.base

interface ClickListner {
    fun <T> onClick(data :T,type: String = "",createNested:Boolean = false)
}