package com.e.buynow.repository

import android.util.Log
import com.e.buynow.network.NetworkInteraction
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


open class ProductRepository @Inject constructor(override val remote:
                                                 NetworkInteraction):Repository(remote){


}