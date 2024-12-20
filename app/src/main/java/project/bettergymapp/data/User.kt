package project.bettergymapp.data

data class User(
    val id: String,
    val username: String,
    val email: String,
    val routines: List<Routine>,
    val sessions: List<Session>
)
