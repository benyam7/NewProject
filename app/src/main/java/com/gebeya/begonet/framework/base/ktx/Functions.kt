/*
 * Copyright (c) 2020. Independent News & Media Plc.
 */

package com.addisfortune.printversion.base.ktx

typealias Supplier<T> = () -> T

interface Consumer<T> {

    fun accept(t: T)
}