package com.e.buynow.view_model

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.LiveData


abstract class BaseViewModel(app: Application): AndroidViewModel(app)  {

//    fun <T, K, R> LiveData<T>.combineWith(
//        liveData: LiveData<K>,
//        block: (T?, K?) -> R
//    ): LiveData<R> {
//        val result = MediatorLiveData<R>()
//        result.addSource(this) {
//            result.value = block(this.value, liveData.value)
//        }
//        result.addSource(liveData) {
//            result.value = block(this.value, liveData.value)
//        }
//        return result
//    }

    fun <A, B> zip2LiveData(a: LiveData<A>, b: LiveData<B>): LiveData<Pair<A, B>> {
        return MediatorLiveData<Pair<A, B>>().apply {
            var lastA: A? = null
            var lastB: B? = null

            fun update() {
                val localLastA = lastA
                val localLastB = lastB
                if (localLastA != null && localLastB != null)
                    this.value = Pair(localLastA, localLastB)
            }

            addSource(a) {
                lastA = it
                update()
            }
            addSource(b) {
                lastB = it
                update()
            }
        }
    }

    fun <A, B, C> zip3LiveData(a: LiveData<A>, b: LiveData<B>, c:LiveData<C>): LiveData<Triple<A, B, C>> {
        return MediatorLiveData<Triple<A, B, C>>().apply {
            var lastA: A? = null
            var lastB: B? = null
            var lastC: C? = null

            fun update() {
                val localLastA = lastA
                val localLastB = lastB
                val localLastC = lastC
                if (localLastA != null && localLastB != null && localLastC !=null )
                    this.value = Triple(localLastA, localLastB, localLastC)
            }

            addSource(a) {
                lastA = it
                update()
            }
            addSource(b) {
                lastB = it
                update()
            }

            addSource(c){
                lastC = it
                update()
            }
        }
    }
}