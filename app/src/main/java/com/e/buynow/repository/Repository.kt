package com.e.buynow.repository

import com.e.buynow.network.NetworkInteraction
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

abstract class Repository(protected open val remote:NetworkInteraction) {

    lateinit var coroutineScope:CoroutineScope


}