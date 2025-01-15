package project.bettergymapp.ui.screen.splash

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import project.bettergymapp.MainActivity
import project.bettergymapp.data.service.AccountService
import project.bettergymapp.data.viewmodel.AppViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
  private val accountService: AccountService
) : AppViewModel() {

  fun onAppStart(openAndPopUp: (String) -> Unit) {
    if (accountService.hasUser()) openAndPopUp("home")
    else openAndPopUp("sign in")
  }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            SplashViewModel(MainActivity.accountService)
        }
        }
    }
}