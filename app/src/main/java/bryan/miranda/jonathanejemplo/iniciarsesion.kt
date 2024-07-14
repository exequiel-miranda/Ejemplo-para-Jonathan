package bryan.miranda.jonathanejemplo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bryan.miranda.jonathanejemplo.modelo.ClaseConexion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class iniciarsesion : AppCompatActivity() {

    companion object variablesLogin {
        lateinit var valorRolUsuario: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_iniciarsesion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtcorreoiniciar = findViewById<EditText>(R.id.txtcorreoiniciar)
        val txtcontrainiciar = findViewById<EditText>(R.id.txtcontrasenainicio)
        val btnrecuperarcontra = findViewById<TextView>(R.id.btnrecuperarcontra)
        val btninicarsesion = findViewById<Button>(R.id.btniniciarsesionhome)
        val btnVolver = findViewById<ImageButton>(R.id.btnVolverIS)

        GlobalScope.launch(Dispatchers.IO) {

            val objConexion = ClaseConexion().cadenaConexion()
            val resulSet = objConexion?.prepareStatement("SELECT rol FROM tbUsuariosOne WHERE correo_usuario = ? ")!!
            resulSet.setString(1, txtcorreoiniciar.text.toString())

            val resultado = resulSet.executeQuery()

            if (resultado.next()) {
                valorRolUsuario = resultado.getString("rol")
                println("este es el uuid traido desde el if $valorRolUsuario")
            }
        }

        btninicarsesion.setOnClickListener {
            val pantallaprincipal = Intent(this, MainActivity::class.java)
            startActivity(pantallaprincipal)
        }

    }
}