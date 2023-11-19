package com.example.parcial2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        _binding = FragmentDetalleFrutasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetalleFrutasViewModel::class.java)

        Log.e("Arguments", arguments.toString())

        val frutaObj = arguments?.getSerializable("fruta")
        //val frutaId = frutaIdString?.toIntOrNull()

        Log.e("frutaidDetalleFrutas", frutaObj.toString())
        viewModel.fetchNutricion(frutaObj as FrutasModel)

        viewModel.nutricion.observe(viewLifecycleOwner, Observer { nutricion ->
            nutricion?.let {
                actualizarUIUser(it)
            }

        })
    }


    private fun actualizarUIUser(nutricion: NutricionModel) {

        Log.e("NUTRICION", nutricion.toString() )
            val calories = nutricion.calorias
            val fat = nutricion.grasa
            val sugar = nutricion.azucar
            val carbohydrates = nutricion.carbohidratos
            val protein = nutricion.proteina

            Log.e(
                "Detalles fruta",
                "Cal: $calories, Fat: $fat, sugar: $sugar, carbo: $carbohydrates, prote: $protein"
            )


            binding.textViewCalories.text = "Calorias: "+calories.toString()
            binding.textViewFat.text = "Grasa: " +fat.toString()
            binding.textViewSugar.text = "Azucar: "+sugar.toString()
            binding.textViewCarbo.text = "Carbohidratos: "+carbohydrates.toString()
            binding.textViewProtein.text = "Proteina: "+protein.toString()

    }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
