package com.example.asm2_ph43396_kot

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.asm2_ph43396_kot.viewmodels.Favorite_Viewmodel
import com.example.asm2_ph43396_kot.viewmodels.Product_Viewmodel
import com.example.asm2_ph43396_kot.viewmodels.User_Viewmodel


enum class ROUTE_NAME {
    welcome,
    login,
    home,
    signup,
    detail,
    cart,
    checkout,
    success,
    order,
    addShipment,
    addPayment,
    paymentMethod,
    setting,
    selectShipment,
    myReview,
    rating
}

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = ROUTE_NAME.welcome.name) {
        composable(ROUTE_NAME.welcome.name) { WelComeScreen(navController) }
        composable(ROUTE_NAME.login.name) { LoginScreen(navController, User_Viewmodel()) }
        composable(ROUTE_NAME.home.name) { FurnitureApp(navController, Favorite_Viewmodel()) }
        composable(ROUTE_NAME.signup.name) { RegisterScreen(navController, User_Viewmodel()) }
        composable("${ROUTE_NAME.detail.name}/{prId}/{prName}/{prImg}/{prPrice}/{description}/{rate}") {
            DetailsProduct(
                navController,
                viewmodel = Product_Viewmodel(),
                id = it.arguments?.getString("prId").toString(),
                name = it.arguments?.getString("prName").toString(),
                image = it.arguments?.getString("prImg").toString(),
                rate = it.arguments?.getString("rate").toString(),
                price = it.arguments?.getString("prPrice").toString(),
                description = it.arguments?.getString("description").toString()
            )
        }
        composable(ROUTE_NAME.cart.name) { SmallTopAppCart(navController) }
        composable(ROUTE_NAME.checkout.name) { SmallTopAppBarPayment(navController) }
        composable(ROUTE_NAME.success.name) { FinalScreen(navController) }
        composable(ROUTE_NAME.order.name) { OrderScreenRun(navController) }
        composable(ROUTE_NAME.addShipment.name) { AddShipmentScreen(navController) }
        composable(ROUTE_NAME.addPayment.name) { AddPaymentScreen(navController) }
        composable(ROUTE_NAME.paymentMethod.name) { SelectPaymentScreen(navController) }
        composable(ROUTE_NAME.setting.name) { settingScreens(navController) }
        composable(ROUTE_NAME.selectShipment.name) { AddressScreen(navController) }
        composable(ROUTE_NAME.myReview.name) { MyReViewTopBar(navController) }
        composable(ROUTE_NAME.rating.name) { ReView(navController) }
    }
}


