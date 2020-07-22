package com.portalsoup.core

data class QueryParameters(val parameters: Map<String, String>) {

    override fun toString(): String = "?" + parameters
            .map { (k, v) -> "$k=$v" }
            .joinToString("&")

    companion object {
        fun fromPairs(vararg pairs: Pair<String, String>): QueryParameters = QueryParameters(mapOf(*pairs))
    }
}