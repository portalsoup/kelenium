package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MoveTargetOutOfBoundsTest: BaseErrorResponseTest() {
    @Test
    fun serializeMoveTargetOutOfBoundsTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.MOVE_TARGET_OUT_OF_BOUNDS.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(MoveTargetOutOfBounds::class.java))
    }
}