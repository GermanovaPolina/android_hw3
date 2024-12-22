package com.homework.hw3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.homework.hw3.data.CardType
import com.homework.hw3.data.PaymentMethod
import com.homework.hw3.data.PaymentMethodId
import com.homework.hw3.ui.theme.SecondaryColor
import com.homework.hw3.utils.CartManager
import com.homework.hw3.utils.PaymentMethodManager

@Composable
fun PaymentMethodCard(
    paymentMethod: PaymentMethod,
    modifier: Modifier = Modifier,
    setPaymentMethodActive: (PaymentMethodId) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {
            setPaymentMethodActive(paymentMethod.id)
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp).fillMaxWidth()
        ) {
            RadioButton(
                selected = paymentMethod.isActive,
                onClick = {
                    setPaymentMethodActive(paymentMethod.id)
                }
            )
            Image(
                painter = rememberAsyncImagePainter(paymentMethod.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
//                    .fillMaxWidth()
////                    .he
                    .width(48.dp)
                    .height(44.dp)
            )
            Text(
                text = paymentMethod.name,
                fontWeight = FontWeight.Bold,
//                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = paymentMethod.digits,
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp
            )
        }
    }
}
