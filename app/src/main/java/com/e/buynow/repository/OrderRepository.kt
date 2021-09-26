package com.e.buynow.repository

import com.e.buynow.network.NetworkInteraction
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope


class OrderRepository(val routine:CoroutineScope,
                      val network:NetworkInteraction){
}