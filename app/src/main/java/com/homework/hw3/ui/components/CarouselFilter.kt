package com.homework.hw3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.homework.hw3.data.CatalogueItemType

@Composable
fun CarouselFilter(
    onFilterSelected: (CatalogueItemType) -> Unit,
    selectedFilter: CatalogueItemType? = null
) {
    val filters = listOf(CatalogueItemType.Hair, CatalogueItemType.Nails, CatalogueItemType.Soap)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(filters) { filter ->
            FilterButton(
                filter = filter,
                isSelected = selectedFilter == filter,
                onFilterSelected = onFilterSelected
            )
        }
    }
}

@Composable
private fun FilterButton(
    filter: CatalogueItemType,
    isSelected: Boolean,
    onFilterSelected: (CatalogueItemType) -> Unit
) {
    Button(
        onClick = { onFilterSelected(filter) },
        modifier = Modifier.background(if (isSelected) Color.Black else Color.White)
    ) {
        Text(text = filter.toString(), style = MaterialTheme.typography.labelLarge)
    }
}