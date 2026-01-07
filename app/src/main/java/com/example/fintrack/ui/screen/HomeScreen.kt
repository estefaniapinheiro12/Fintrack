package com.example.fintrack.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.R
import com.example.fintrack.ui.theme.BackgroundDark
import com.example.fintrack.ui.theme.BackgroundLight
import com.example.fintrack.ui.theme.ExpenseDark
import com.example.fintrack.ui.theme.ExpenseLight
import com.example.fintrack.ui.theme.FintrackTheme
import com.example.fintrack.ui.theme.IncomeDark
import com.example.fintrack.ui.theme.IncomeLight
import com.example.fintrack.ui.theme.ManropeFamily
import com.example.fintrack.ui.theme.PrimaryDark
import com.example.fintrack.ui.theme.PrimaryLight
import com.example.fintrack.ui.theme.SecondaryDark
import com.example.fintrack.ui.theme.SecondaryLight
import com.example.fintrack.ui.theme.SurfaceDark
import com.example.fintrack.ui.theme.SurfaceLight
import com.example.fintrack.ui.theme.SurfaceVariantDark
import com.example.fintrack.ui.theme.TextPrimaryDark
import com.example.fintrack.ui.theme.TextPrimaryLight
import com.example.fintrack.ui.theme.TextSecondaryDark
import com.example.fintrack.ui.theme.TextSecondaryLight

@Composable
fun HomeScreen(
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    // Definir cores baseadas no tema
    val backgroundColor = if (isDarkTheme) BackgroundDark else BackgroundLight
    val surfaceColor = if (isDarkTheme) SurfaceDark else SurfaceLight
    val surfaceVariant = if (isDarkTheme) SurfaceVariantDark else Color(0xFFF5F5F5)
    val textPrimary = if (isDarkTheme) TextPrimaryDark else TextPrimaryLight
    val textSecondary = if (isDarkTheme) TextSecondaryDark else TextSecondaryLight
    val primary = if (isDarkTheme) PrimaryDark else PrimaryLight
    val secondary = if (isDarkTheme) SecondaryDark else SecondaryLight
    val income = if (isDarkTheme) IncomeDark else IncomeLight
    val expense = if (isDarkTheme) ExpenseDark else ExpenseLight

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header com Gradiente
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(primary, secondary)
                        ),
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
                    .padding(24.dp)
            ) {
                Column {
                    // Top Bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .background(
                                    Color.White.copy(alpha = 0.15f),
                                    RoundedCornerShape(12.dp)
                                )
                        ) {
                            Icon(
                               painterResource(id = R.drawable.menu),
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .background(
                                        Color.White.copy(alpha = 0.15f),
                                        RoundedCornerShape(12.dp)
                                    )
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.notification),
                                    contentDescription = "Notificações",
                                    tint = Color.White
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // Saldo
                    Text(
                        text = "Saldo Total",
                        fontFamily = ManropeFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "R$ 2.450,00",
                        fontFamily = ManropeFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 48.sp,
                        color = Color.White,
                        letterSpacing = (-1).sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painterResource(id = R.drawable.money ),
                            contentDescription = null,
                            tint = income,
                            modifier = Modifier.size(25.dp)
                        )
                        Text(
                            text = "+R$ 350,00 este mês",
                            fontFamily = ManropeFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = income
                        )
                    }
                }
            }

            // Conteúdo
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                // Cards Receitas e Despesas
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Card Receitas
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = surfaceColor),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = if (isDarkTheme) 8.dp else 4.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            income.copy(alpha = 0.2f),
                                            RoundedCornerShape(10.dp)
                                        )
                                        .padding(8.dp)
                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.money_send),
                                        contentDescription = null,
                                        tint = income,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Text(
                                    text = "Receitas",
                                    fontFamily = ManropeFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    color = textSecondary
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "R$ 3.500",
                                fontFamily = ManropeFamily,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 24.sp,
                                color = income
                            )
                        }
                    }

                    // Card Despesas
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = surfaceColor),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = if (isDarkTheme) 8.dp else 4.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            expense.copy(alpha = 0.2f),
                                            RoundedCornerShape(10.dp)
                                        )
                                        .padding(8.dp)
                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.money_recive),
                                        contentDescription = null,
                                        tint = expense,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Text(
                                    text = "Despesas",
                                    fontFamily = ManropeFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    color = textSecondary
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "R$ 1.050",
                                fontFamily = ManropeFamily,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 24.sp,
                                color = expense
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Card Gastos por Categoria
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = surfaceColor),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = if (isDarkTheme) 8.dp else 4.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(
                            text = "Gastos por Categoria",
                            fontFamily = ManropeFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = textPrimary
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Categorias
                        val categories = listOf(
                            Triple("Alimentação", 450, Color(0xFFFF6B6B)),
                            Triple("Transporte", 280, Color(0xFFFFB800)),
                            Triple("Moradia", 1200, Color(0xFF3B82F6)),
                            Triple("Lazer", 120, Color(0xFF8B5CF6))
                        )

                        val total = categories.sumOf { it.second }

                        categories.forEach { (name, value, color) ->
                            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = name,
                                        fontFamily = ManropeFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        color = textPrimary
                                    )
                                    Text(
                                        text = "R$ $value",
                                        fontFamily = ManropeFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        color = textSecondary
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(8.dp)
                                        .background(
                                            if (isDarkTheme) surfaceVariant else Color(0xFFF0F0F0),
                                            RoundedCornerShape(8.dp)
                                        )
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(value.toFloat() / total)
                                            .height(8.dp)
                                            .background(color, RoundedCornerShape(8.dp))
                                    )
                                }
                            }
                        }

                        Divider(
                            color = if (isDarkTheme) Color.White.copy(alpha = 0.1f)
                            else Color.Black.copy(alpha = 0.1f),
                            thickness = 1.dp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total",
                                fontFamily = ManropeFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textPrimary
                            )
                            Text(
                                text = "R$ $total",
                                fontFamily = ManropeFamily,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp,
                                color = textPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Card Transações Recentes
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = surfaceColor),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = if (isDarkTheme) 8.dp else 4.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Transações Recentes",
                                fontFamily = ManropeFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = textPrimary
                            )
                            TextButton(onClick = { }) {
                                Text(
                                    text = "Ver todas",
                                    fontFamily = ManropeFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = primary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        val transactions = listOf(
                            Transaction("iFood", "Alimentação", -45.00, Color(0xFFFF6B6B)),
                            Transaction("Salário", "Receita", 3500.00, income),
                            Transaction("Energia", "Contas", -180.00, Color(0xFFFFB800)),
                            Transaction("Starbucks", "Lazer", -32.50, Color(0xFF8B5CF6)),
                            Transaction("Aluguel", "Moradia", -1200.00, Color(0xFF3B82F6))
                        )

                        transactions.forEachIndexed { index, tx ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(
                                            tx.color.copy(alpha = 0.2f),
                                            RoundedCornerShape(14.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AccountBox,
                                        contentDescription = null,
                                        tint = tx.color,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = tx.name,
                                        fontFamily = ManropeFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 15.sp,
                                        color = textPrimary
                                    )
                                    Text(
                                        text = tx.category,
                                        fontFamily = ManropeFamily,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp,
                                        color = textSecondary
                                    )
                                }

                                Text(
                                    text = "${if (tx.amount > 0) "+" else ""}R$ ${
                                        String.format("%.2f", Math.abs(tx.amount))
                                    }",
                                    fontFamily = ManropeFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = if (tx.amount > 0) income else textPrimary
                                )
                            }

                            if (index < transactions.size - 1) {
                                Divider(
                                    color = if (isDarkTheme) Color.White.copy(alpha = 0.05f)
                                    else Color.Black.copy(alpha = 0.05f)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 32.dp),
            containerColor = primary,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.add),
                    contentDescription = "Adicionar",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Nova Transação",
                    fontFamily = ManropeFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

data class Transaction(
    val name: String,
    val category: String,
    val amount: Double,
    val color: Color
)
@Preview(showBackground = true, name = "Light Mode", showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    FintrackTheme(darkTheme = false) {
       HomeScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Dark Mode",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreenPreviewDark() {
    FintrackTheme(darkTheme = true) {
        HomeScreen()
    }
}
