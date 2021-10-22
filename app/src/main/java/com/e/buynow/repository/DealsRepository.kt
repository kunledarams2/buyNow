package com.e.buynow.repository

import com.e.buynow.network.NetworkInteraction
import kotlinx.coroutines.CoroutineScope

class DealsRepository(val routine:CoroutineScope,
                      val network: NetworkInteraction)/*:ProductRepository(network)*/ {
}