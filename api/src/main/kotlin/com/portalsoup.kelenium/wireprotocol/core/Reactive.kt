package com.portalsoup.kelenium.wireprotocol.core

import kotlin.reflect.KProperty

class Reactive<T>(initialValue: T, val onChange: (newValue: T, oldValue: T) -> Unit) {
    private var propValue = initialValue
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return propValue
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val oldValue = propValue
        propValue = value
        onChange(value, oldValue)
    }
}
