package com.v.accounting.repository

import kotlinx.coroutines.*

/**
 * Author:v
 * Time:2021/6/10
 */
abstract class BaseRepository {

    suspend fun launchOnIOScope(block: suspend CoroutineScope.() -> Unit) =
        withContext(Dispatchers.IO) { block }
}