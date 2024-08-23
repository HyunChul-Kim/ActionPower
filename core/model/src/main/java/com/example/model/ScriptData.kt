package com.example.model

data class ScriptData(
    val editorState: EditorState,
    val lastSaved: Long,
    val source: String,
    val version: String
) {
    companion object {
        fun empty() =
            ScriptData(
                editorState =
                    EditorState(
                        root = State.empty()
                    ),
                lastSaved = 0L,
                source = "",
                version = ""
            )
    }
}

data class EditorState(
    val root: State
)

data class State(
    val children: List<TextGraph>,
    val type: String,
    val version: Int
) {
    companion object {
        fun empty() =
            State(
                children = emptyList(),
                type = "",
                version = 0
            )
    }
}

data class TextGraph(
    val children: List<TextItem>,
    val type: String
)

data class TextItem(
    val speaker: String = "",
    val type: String,
    val text: String = "",
    val time: Float,
    val start: Float,
    val end: Float
)
