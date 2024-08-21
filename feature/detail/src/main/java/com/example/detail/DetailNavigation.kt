package com.example.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val DRINK_ID_ARG = "drinkId"
const val DETAIL_BASE_ROUTE = "detail_route"
const val DETAIL_ROUTE = "$DETAIL_BASE_ROUTE?$DRINK_ID_ARG={$DRINK_ID_ARG}"

fun NavController.navigateToDetail(drinkId: String, navOptions: NavOptions? = null) {
    navigate(
        "${DETAIL_BASE_ROUTE}?${DRINK_ID_ARG}=$drinkId",
        navOptions
    )
}

fun NavGraphBuilder.detailScreen() {
    composable(
        route = DETAIL_ROUTE,
        arguments = listOf(
            navArgument(DRINK_ID_ARG) {
                defaultValue = ""
                nullable = false
                type = NavType.StringType
            },
        ),
    ) {
        DetailRoute()
    }
}