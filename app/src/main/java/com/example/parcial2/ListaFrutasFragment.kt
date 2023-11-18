package com.example.parcial2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial2.databinding.FragmentListaFrutasBinding

class ListaFrutasFragment : Fragment() {

    private var _binding: FragmentListaFrutasBinding? = null
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

        val frutasAdapter = FrutasAdapter()
        binding.recyclerViewFruits.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFruits.adapter = frutasAdapter

        viewModel.frutas.observe(viewLifecycleOwner, { frutas ->
            frutas?.let {
                frutasAdapter.actualizarDatos(it)
            }
        })

        frutasAdapter.setOnItemClickListener { fruta ->
            // Navegar al detalle de la fruta pasando el ID de la fruta
            val bundle = bundleOf("frutaId" to fruta.id.toString())
            findNavController().navigate(R.id.action_listaFrutasFragment_to_detalleFrutasFragment, bundle)
        }


        viewModel.fetchFruits()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}