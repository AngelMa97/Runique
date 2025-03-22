package com.angelme.auth.domain

import com.angelme.core.domain.util.DataError
import com.angelme.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}