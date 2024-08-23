package com.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalScriptData(
    val editorState: LocalEditorState,
    val lastSaved: Long,
    val source: String,
    val version: String
)

@Serializable
data class LocalEditorState(
    val root: LocalState
)

@Serializable
data class LocalState(
    val children: List<LocalTextGraph>,
    val type: String,
    val version: Int
)

@Serializable
data class LocalTextGraph(
    val children: List<LocalTextItem>,
    val type: String
)

@Serializable
data class LocalTextItem(
    val speaker: String = "",
    val type: String,
    val text: String = "",
    val time: Float = 0f,
    val s: Float = 0f,
    val e: Float = 0f
)

fun LocalScriptData.toDomain() =
    ScriptData(
        editorState = editorState.toDomain(),
        lastSaved = lastSaved,
        source = source,
        version = version
    )

fun LocalEditorState.toDomain() =
    EditorState(
        root = root.toDomain()
    )

fun LocalState.toDomain() =
    State(
        children = children.map { it.toDomain() },
        type = type,
        version = version
    )

fun LocalTextGraph.toDomain() =
    TextGraph(
        children = children.map { it.toDomain() },
        type = type
    )

fun LocalTextItem.toDomain() =
    TextItem(
        speaker = speaker,
        type = type,
        text = text,
        time = time,
        start = s,
        end = e
    )