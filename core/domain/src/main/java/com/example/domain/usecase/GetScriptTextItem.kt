package com.example.domain.usecase

import com.example.domain.model.ApiResult
import com.example.domain.repository.ScriptRepository
import com.example.model.TextItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetScriptTextItem @Inject constructor(
    private val scriptRepository: ScriptRepository
) {

    operator fun invoke(
        script: String
    ): Flow<List<TextItem>> = channelFlow {
        withContext(Dispatchers.IO) {
            scriptRepository.getScript(script).collectLatest { apiResult ->
                when(apiResult) {
                    is ApiResult.Success -> {
                        send(
                            apiResult.value.editorState.root.children.flatMap {
                                it.children
                            }
                        )
                    }
                    is ApiResult.Error -> {

                    }
                    is ApiResult.Exception -> {

                    }
                }
            }
        }
    }

}