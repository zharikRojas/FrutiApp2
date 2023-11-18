package com.example.parcial2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.parcial2.databinding.FragmentDetalleFrutasBinding


class DetalleFrutasFragment : Fragment() {

    private lateinit var viewModel: DetalleFrutasViewModel
    private var _binding: FragmentDetalleFrutasBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleFrutasBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetalleFrutasViewModel::class.java)

        val frutaIdString = arguments?.getString("frutaId")
        val frutaId = frutaIdString?.toIntOrNull()

        viewModel.nutricion.observe(viewLifecycleOwner, Observer {nutricion ->
            if (nutricion != null){
                actualizarUIUser(nutricion)
            }
        })

        if (frutaId != null && frutaId != -1){

           viewModel.fetchNutricion(frutaId)
        }
    }


    private fun actualizarUIUser(nutricion: NutricionModel?) {
        // Actualiza la interfaz de usuario con los detalles de la nutrición
        // (por ejemplo, actualiza textViews, imágenes, etc.).

        // Ejemplo de cómo puedes acceder a los valores de la nutrición
        val calories = nutricion?.calorias ?: "N/A"
        val fat = nutricion?.grasa ?: "N/A"
        val sugar = nutricion?.azucar ?: "N/A"
        val carbohydrates = nutricion?.carbohidratos ?: "N/A"
        val protein = nutricion?.proteina ?: "N/A"

        Log.e("Detalles fruta", "Cal: $calories, Fat: $fat, sugar: $sugar, carbo: $carbohydrates, prote: $protein")
        // Ahora puedes usar estos valores para actualizar tu interfaz de usuario
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}