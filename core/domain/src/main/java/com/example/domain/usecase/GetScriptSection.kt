package com.example.domain.usecase

import com.example.model.SectionScript
import com.example.model.TextItem
import javax.inject.Inject

class GetScriptSection @Inject constructor() {

    /**
     * sectionTime: seconds
     * start: seconds
     */
    operator fun invoke(
        items: List<TextItem>,
        sectionTime: Int,
        start: Int
    ) = makeSectionMap(items, sectionTime, start)

    private fun makeSectionMap(
        items: List<TextItem>,
        sectionTime: Int,
        start: Int
    ): List<SectionScript> {
        val unit = start / sectionTime
        val startTime = unit * sectionTime
        val endTime = startTime + sectionTime
        return items.map { item ->
            SectionScript(
                startTime = item.start.toDouble(),
                text = item.text,
                isSelected = item.start.toInt() in startTime until endTime
            )
        }
    }
}