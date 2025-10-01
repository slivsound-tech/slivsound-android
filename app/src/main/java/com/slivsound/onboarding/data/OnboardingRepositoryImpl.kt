//import com.slivsound.onboarding.domain.OnboardingRepository
//import java.util.prefs.Preferences
//
//class OnboardingRepositoryImpl(
//    private val dataStore: DataStore<Preferences>
//) : OnboardingRepository {
//
//    private val ONBOARDING_KEY = booleanPreferencesKey("onboarding_completed")
//
//    override suspend fun setOnboardingCompleted(completed: Boolean) {
//        dataStore.edit { prefs
//            ->
//            prefs[ONBOARDING_KEY] = completed
//        }
//    }
//
//    override fun isOnboardingCompleted(): Flow<Boolean> =
//        dataStore.data.map { prefs ->
//            prefs[ONBOARDING_KEY] ?: false
//        }
//}
