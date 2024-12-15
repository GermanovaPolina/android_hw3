package com.homework.hw3.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.homework.hw3.R
import com.homework.hw3.data.CardType

@Composable
fun AddToCartButton(
    id: String,
    quantity: Int,
    addToCard: (id: String, delta: Int) -> Unit,
    type: CardType,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        if (quantity != 0 || type == CardType.Cart) {
            QuantitySelector(quantity) { delta -> addToCard(id, delta) }
        } else {
            Button(
                onClick = { addToCard(id, 1) },
            ) {
                Text("Add to Cart")
            }
        }
    }

}

@Composable
private fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onQuantityChange(-1) },
            enabled = quantity != 0,
        ) {
            Icon(
                Icons.Filled.Remove,
                contentDescription = "Decrease Quantity"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$quantity")
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { onQuantityChange(1) }) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Increase Quantity"
            )
        }
    }
}