package com.portalsoup.kelenium.framework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import java.io.Closeable
import kotlin.reflect.KProperty


class PropertyObserver<T>(initial: T) : Closeable {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    private val flow: MutableStateFlow<T> = MutableStateFlow(initial)
    private val changeHandlers = mutableListOf<(old: T, new: T) -> Unit>()

    private var cachedPrevious: T = initial

    init {
        flow
            .drop(1) // Drop the change setting the initial value
            .distinctUntilChanged() // suppress duplicate emissions
            .onEach(::notify) // Notify observers on every state change
            .launchIn(scope)
    }

    constructor(initial: T, onChange: (old: T, new: T) -> Unit) : this(initial) {
        this.addObserver(onChange)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return when (val value = flow.value) {
            is DeepCopyable<*> -> {
                @Suppress("UNCHECKED_CAST")
                (value as DeepCopyable<T>).deepCopy()
            }
            else -> return value
        }
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        cachedPrevious = this.flow.value
        this.flow.value = value
    }

    private fun stopObserver() {
        scope.cancel()
    }

    private fun notify(change: T) {
        changeHandlers.onEach { it(cachedPrevious, change) }
    }
    // This method should append a new function to the change handler, utilizng flow features where possible
    fun addObserver(f: (old: T, new: T) -> Unit) = changeHandlers.add(f)

    override fun close() {
        stopObserver()
    }
}