package dev.filipebezerra.android.tipcalculator.util.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

// https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7#a7a8
/**
 * Sets the value to the result of a function that is called when both `LiveData`s have data
 * or when they receive updates after that.
 */
fun <T, A, B> LiveData<A>.combineAndCompute(other: LiveData<B>, onChange: (A, B) -> T): MediatorLiveData<T> {
    var source1Emitted = false
    var source2Emitted = false

    val result = MediatorLiveData<T>()

    val mergeF = {
        val source1Value = this.value
        val source2Value = other.value

        if (source1Emitted.and(source2Emitted)) {
            result.value = onChange.invoke(source1Value!!, source2Value!!)
        }
    }

    result.addSource(this) { source1Emitted = true; mergeF.invoke() }
    result.addSource(other) { source2Emitted = true; mergeF.invoke() }

    return result
}