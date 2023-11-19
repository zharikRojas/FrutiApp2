package com.example.parcial2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial2.databinding.FragmentListaFrutasBinding

class ListaFrutasFragment : Fragment() {

    private var _binding: FragmentListaFrutasBinding? = null
    private lateinit var spinnerFilter: Spinner
    private lateinit var editTextSearch: EditText
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this).get(FrutasListaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaFrutasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerFilter = binding.nutrientSpinner
        val frutasAdapter = FrutasAdapter()
        val filterAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter_options,
            android.R.layout.simple_spinner_item
        )
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFilter.adapter = filterAdapter


        editTextSearch = binding.editTextSearch
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar nada aquí
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText.isEmpty()){
                    viewModel.fetchFruits("Calorias")
                }else{
                    viewModel.filterFruitsByName(searchText)
                    viewModel.filteredFrutas.observe(viewLifecycleOwner, { frutas ->
                        frutas?.let {
                            frutasAdapter.actualizarDatos(it)
                            binding.txtTotal.text="Total Resultados: "+frutasAdapter.itemCount.toString()
                        }
                    })
                }

            }

            override fun afterTextChanged(s: Editable?) {


            }
        })



        binding.recyclerViewFruits.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFruits.adapter = frutasAdapter

        viewModel.frutas.observe(viewLifecycleOwner, { frutas ->
            frutas?.let {
                frutasAdapter.actualizarDatos(it)
                binding.txtTotal.text="Total Resultados: "+frutasAdapter.itemCount.toString()
            }
        })




        frutasAdapter.setOnItemClickListener { fruta ->
            // Navegar al detalle de la fruta pasando el ID de la fruta
            //val bundle = bundleOf("frutaId" to fruta)
            val bundle2 = Bundle()
            val obj : FrutasModel= fruta
            bundle2.putSerializable("fruta", obj)
            Log.d("FRUTAID", bundle2.toString())
            findNavController().navigate(R.id.action_listaFrutasFragment_to_detalleFrutasFragment,bundle2)

        }

         spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedFilter =
                    resources.getStringArray(R.array.filter_options)[position]
                Log.d("SelectedFilter", selectedFilter)

                // Llama a la función de ViewModel para cargar frutas basadas en el filtro seleccionado
                viewModel.fetchFruits(selectedFilter)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Opcional: lógica para manejar cuando no se selecciona nada
            }
        }
        viewModel.fetchFruits("calorias")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}