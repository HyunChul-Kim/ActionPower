package com.example.domain.repository

import com.example.domain.model.ApiResult
import com.example.model.ScriptData
import kotlinx.coroutines.flow.Flow

interface ScriptRepository {
    suspend fun getScript(name: String): Flow<ApiResult<ScriptData>>
}