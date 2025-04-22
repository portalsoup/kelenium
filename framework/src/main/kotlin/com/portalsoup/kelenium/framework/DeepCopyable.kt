package com.portalsoup.kelenium.framework

interface DeepCopyable<T> {
    fun deepCopy(): T
}