package com.portalsoup.wireprotocol.core

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.full.companionObject

/**
 * A mixin interface that adds the log() method to the class's scope, reducing the boilerplate involved to set up logging
 * on a class
 */
interface ILogging {
    fun log(): Logger = getLogger(getClassForLogging(this.javaClass))

    private fun getLogger(forClass: Class<*>): Logger = LoggerFactory.getLogger(forClass)

    private fun <T : Any> getClassForLogging(javaClass: Class<T>): Class<*> =
        javaClass.enclosingClass?.takeIf {
            it.kotlin.companionObject?.java == javaClass
        } ?: javaClass
}