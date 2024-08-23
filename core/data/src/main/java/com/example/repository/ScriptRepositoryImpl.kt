package com.example.repository

import com.example.domain.model.ApiResult
import com.example.domain.repository.ScriptRepository
import com.example.model.ScriptData
import com.example.model.toDomain
import com.example.source.local.ScriptDataSource
import com.example.util.safeFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScriptRepositoryImpl @Inject constructor(
    private val scriptDataSource: ScriptDataSource
): ScriptRepository {

    override suspend fun getScript(name: String): Flow<ApiResult<ScriptData>> =
        safeFlow {
            scriptDataSource.getScriptData(name).toDomain()
        }
}