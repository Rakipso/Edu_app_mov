package com.example.eduapp.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eduapp.Tab
import com.example.eduapp.ui.theme.EduBlue
import com.example.eduapp.ui.theme.EduTextMuted

@Composable
fun EduNavigationBar(
    selectedTab: Tab,
    onTabSelected: (Tab) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        NavigationBar(
            containerColor = Color.White,
            contentColor = EduBlue,
            tonalElevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Tab.entries.forEach { tab ->
                val isSelected = tab == selectedTab
                val iconToUse = if (isSelected) tab.selectedIcon else tab.icon

                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onTabSelected(tab) },
                    icon = {
                        Icon(
                            imageVector = iconToUse,
                            contentDescription = tab.label,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = tab.label,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = EduBlue,
                        unselectedIconColor = EduTextMuted,
                        selectedTextColor = EduBlue,
                        unselectedTextColor = EduTextMuted,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}