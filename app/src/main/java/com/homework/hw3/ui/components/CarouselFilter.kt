package com.homework.hw3.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homework.hw3.data.CatalogueItemType
import com.homework.hw3.data.FilterValue
import com.homework.hw3.ui.theme.SecondaryColor
import kotlinx.coroutines.launch

val filters: List<FilterValue> =
    listOf(
        FilterValue(CatalogueItemType.Hair, "Волосы"),
        FilterValue(CatalogueItemType.Nails, "Маникюр и педикюр"),
        FilterValue(CatalogueItemType.Soap, "Мыло"),
    )

@Composable
fun CarouselFilter(
    onFilterSelected: suspend (CatalogueItemType) -> Unit,
    selectedFilter: CatalogueItemType? = null
) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(filters) { filter ->
            FilterButton(
                filter = filter,
                isSelected = selectedFilter == filter.type,
                onFilterSelected = onFilterSelected
            )
        }
    }
}

@Composable
private fun FilterButton(
    filter: FilterValue,
    isSelected: Boolean,
    onFilterSelected: suspend (CatalogueItemType) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Button(
        onClick = {
            coroutineScope.launch {
                onFilterSelected(filter.type)
            }
        },
        border = BorderStroke(2.dp, SecondaryColor),
        contentPadding = PaddingValues(8.dp, 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.Black else Color.White,
            contentColor = if (isSelected) Color.White else Color.Black,
        ),
    ) {
        Text(
            text = filter.title,
            fontSize = 16.sp,
        )
    }
}