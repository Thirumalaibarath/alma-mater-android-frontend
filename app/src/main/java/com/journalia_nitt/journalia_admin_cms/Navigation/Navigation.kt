package com.journalia_nitt.journalia_admin_cms.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.journalia.AdminComponents
import com.example.journalia.AdminDetailsPage
import com.example.journalia.Navigation.Page
import com.example.journalia.Navigation.Screens
import com.example.journalia.Student.CommunityPage
import com.journalia_nitt.journalia_admin_cms.LoginPage
import com.journalia_nitt.journalia_admin_cms.R
import com.journalia_nitt.journalia_admin_cms.SpalshScreen

import com.example.journalia.Student.PostCreation
import com.example.journalia.Student.ProfilePage
import com.example.journalia.Student.BookmarkPage
import com.example.journalia.Student.CalenderPage
import com.example.journalia.Student.Deadline
import com.example.journalia.Student.PDFWebViewScreen
import com.example.journalia.Student.Screens.ClubPage
import com.example.journalia.Student.Screens.ClubSubPage
import com.example.journalia.Student.Screens.FestDirectory
import com.example.journalia.Student.Screens.LandingPage
import com.example.journalia.Student.Screens.clubCommunityPage
import com.example.journalia.Student.Responses.UserFetchClass
import com.example.journalia.Student.ViewPost
import com.example.journalia.WebView.WebView
import com.example.journalia.WebView.Webmail
import com.example.journalia.WebView.buildUrl
import com.google.gson.Gson
import com.journalia_nitt.journalia_admin_cms.AlumniPageContent
import com.journalia_nitt.journalia_admin_cms.CommunityScreen

val urbanist = FontFamily(Font(R.font.urbanist))
val webmailURL = "https://students.nitt.edu/horde/login.php"

@Composable
fun MyApp(innerPaddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.SplashScreen.route) {
            SpalshScreen(innerPaddingValues,navController)
        }
        composable(Screens.LoginPage.route) {
            LoginPage(navController=navController)
        }
        composable(Screens.AuthPage.route) {
            WebView(navController = navController, url = buildUrl())
        }
        composable(Screens.HomePage.route) {
            LandingPage(navController,innerPaddingValues)
        }
        composable(Screens.CalenderPage.route) {
            Page(
                innerPadding = innerPaddingValues ,
                currentPage = {
                    CalenderPage(navController = navController)
                },
                navController = navController,
                searchBar = false,
                heading = "CALENDAR"
            )
        }
        composable(Screens.AdminPage.route) {
            Page(
                innerPadding = innerPaddingValues ,
                currentPage = {
                    AdminComponents(navController)
                },
                navController = navController,
                searchBar = false,
                heading = "ADMIN BOARD"
            )
        }
        composable(Screens.PostCreationPage.route) {
            Page(
                innerPadding = innerPaddingValues ,
                currentPage = {
                    PostCreation(navController)
                },
                navController = navController,
                searchBar = false,
                heading = "CREATE A POST"
            )
        }
        composable(Screens.BookMarkPage.route) {
            Page(
                innerPadding = innerPaddingValues ,
                currentPage = {
                    BookmarkPage()
                },
                navController = navController,
                searchBar = false,
                heading = "BOOKMARKS"
            )
        }
        composable(Screens.ProfilePage.route) {
            Page(
                innerPadding = innerPaddingValues ,
                currentPage = {
                    ProfilePage(navController)
                },
                navController = navController,
                searchBar = false,
                heading = "PROFILE"
            )
        }
        composable(
            route = Screens.AdminDetailsPage.route,
            arguments = listOf(
                navArgument("item") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            val itemJson = navBackStackEntry.arguments?.getString("item")
            val item = if (itemJson != null) {
                Gson().fromJson(itemJson, Deadline::class.java)
            } else {
                null
            }

            if (item != null) {
                Page(
                    innerPadding = innerPaddingValues ,
                    currentPage = {
                        AdminDetailsPage(item,navController)
                    },
                    navController = navController,
                    searchBar = false,
                    heading = "CIRCULAR"
                )
            } else {
                Text("Error: Item not found")
            }
        }

        composable(Screens.FestDirectory.route) {
            Page(
                innerPaddingValues,
                currentPage={
                    FestDirectory(navController)
                },
                navController = navController,
                searchBar = false,
                heading = "FEST DIRECTORY"
            )
        }
        composable(Screens.ClubDirectory.route) {
            Page(
                innerPaddingValues,
                currentPage = {
                    ClubSubPage(navController)
                },
                navController = navController,
                searchBar = false,
                heading = "CLUB DIRECTORY"
            )
        }
        composable(Screens.ClubPage.route) {
            Page(
                innerPaddingValues,
                currentPage = {
                    ClubPage(innerPaddingValues,navController)
                },
                navController = navController,
                searchBar = false,
                heading = "Explore"
            )
        }
        composable(Screens.CommunityPage.route) {
            Page(
                innerPaddingValues,
                currentPage = {
                    CommunityPage(innerPaddingValues,navController)
                },
                navController = navController,
                searchBar = false,
                heading = "PUBLIC COMMUNITY"
            )
        }
        composable(Screens.Webmail.route) {
            Page(
                innerPaddingValues,
                currentPage = {
                    Webmail(webmailURL)
                },
                navController = navController,
                searchBar = false,
                heading = ""
            )
        }
        composable(Screens.ClubCommunityPage.route) {
            Page(
                innerPaddingValues,
                currentPage = {
                    clubCommunityPage(innerPaddingValues,navController)
                },
                navController = navController,
                searchBar = false,
                heading = "FEST COMMUNITY"
            )
        }
        composable(
            route= Screens.ViewPost.route,
            arguments = listOf(
                navArgument("item") {
                    type = NavType.StringType
                }
            )
        ) {navBackStackEntry ->

            val itemJson1 = navBackStackEntry.arguments?.getString("item")
            val item = if (itemJson1 != null) {
                Gson().fromJson(itemJson1, UserFetchClass::class.java)
            } else {
                null
            }

            if (item != null) {
                Page(
                    innerPaddingValues,
                    currentPage = {
                        ViewPost(navController,item)
                    },
                    navController = navController,
                    searchBar = false,
                    heading = "VIEW POST"
                )
            } else {
                Text("Error: Item not found")
            }
        }
        composable(Screens.AlumniCommunityScreen.route) {
            Page(
                innerPaddingValues,
                currentPage = {
                    CommunityScreen(innerPaddingValues,navController)
                },
                navController = navController,
                searchBar = false,
                heading = "ALUMNI COMMUNITY"
            )
        }
        composable(Screens.PdfWebViewPage.route) {
            Page(
                innerPaddingValues,
                currentPage = {
                    PDFWebViewScreen()
                },
                navController = navController,
                searchBar = false,
                heading = "PDF VIEW SCREEN"
            )
        }
        composable(Screens.AlumniContentPage.route) {
            val showBottomSheet = remember { mutableStateOf(false) }
            Page(
                innerPaddingValues,
                currentPage = {
                    AlumniPageContent(innerPaddingValues,showBottomSheet,navController)
                },
                navController = navController,
                searchBar = false,
                heading = "POST SCREEN"
            )
        }
    }
}