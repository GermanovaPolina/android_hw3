package com.homework.hw3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.homework.hw3.data.CardType
import com.homework.hw3.ui.theme.SecondaryColor

@Composable
fun AddToCartButton(
    id: String,
    quantity: Int,
    addToCard: (id: String, delta: Int) -> Unit,
    type: CardType,
) {
    if (quantity != 0 || type == CardType.Cart) {
        QuantitySelector(quantity, type) { delta -> addToCard(id, delta) }
    } else {
        Button(
            modifier = Modifier
                .height(39.dp),
            onClick = { addToCard(id, 1) },
            contentPadding = PaddingValues(8.dp),
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SecondaryColor,
                contentColor = Color.Black,
            ),
        ) {
            Text(
                text = "В корзину",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
            )
            Icon(
                Icons.Outlined.ShoppingCart,
                contentDescription = "Добавить в корзину",
            )
        }
    }
}

@Composable
private fun QuantitySelector(
    quantity: Int,
    type: CardType,
    onQuantityChange: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .height(39.dp)
            .background(
                if (type == CardType.Catalogue) SecondaryColor else Color.Transparent,
                RoundedCornerShape(100.dp),
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            Icons.Filled.Remove,
            contentDescription = "Decrease Quantity",
            tint = if (quantity == 0) Color.Gray else Color.Black,
            modifier = Modifier.clickable {
                onQuantityChange(if (quantity == 0) 0 else -1)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$quantity",
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            Icons.Filled.Add,
            contentDescription = "Increase Quantity",
            modifier = Modifier.clickable { onQuantityChange(1) }
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}