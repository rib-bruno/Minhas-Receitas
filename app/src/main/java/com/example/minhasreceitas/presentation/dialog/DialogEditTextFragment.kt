package com.example.minhasreceitas.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.minhasreceitas.databinding.FragmentDialogEditTextBinding

//utilizar na inserção das receitas

class DialogEditTextFragment : DialogFragment() {

    lateinit var binding: FragmentDialogEditTextBinding

    //informar um titulo e um placeholder
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleText = arguments?.getString(TITTLE_TEXT)
            ?: throw IllegalArgumentException("Ops, passe o título!")

        val placeHolderText = arguments?.getString(TITTLE_TEXT)
            ?: throw IllegalArgumentException("Ops, passe o place holder!")

        return activity?.let {
            //criar custom dialog aqui
            binding = FragmentDialogEditTextBinding.inflate(
                requireActivity().layoutInflater
            ).apply {
                etEditText.hint = placeHolderText
                tvTitle.text = titleText
            }
            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    //vai ate o dialog e volta de onde chamou retornando valores que a gente preencheu
                    setFragmentResult(
                        FRAGMENT_RESULT, bundleOf(
                            //retornar esse valor via bundle
                            EDIT_TEXT_VALUE to binding.etEditText.text.toString()
                        )
                    )
                }.setNegativeButton("Cancelar") { _, _ ->
                    dismiss()
                }.create()
        } ?: throw java.lang.IllegalArgumentException("A activity não poder ser null!")
    }


    companion object {
        const val TITTLE_TEXT = "TITTLE_TEXT"
        const val PLACE_HOLDER = "PLACE_HOLDER"

        //IDENTIFICAR CHAVE E VALOR
        const val FRAGMENT_RESULT = "FRAGMENT_RESULT"
        const val EDIT_TEXT_VALUE = "EDIT_TEXT_VALUE"

        //deixar a responsabilidade de chamar a tela para a própria tela que tá chamando/
        //deixar em um unico lugar a chamada dele
        fun show(
            title: String,
            placeHolder: String,
            fragmentManager: FragmentManager,
            //tag pra saber quem tá chamando
            tag: String = DialogEditTextFragment::class.simpleName.toString()
        ) {
            //instanciar o fragment pra usar onde quiser
            DialogEditTextFragment().apply {
                arguments = bundleOf(
                    TITTLE_TEXT to title,
                    PLACE_HOLDER to placeHolder
                )
            }.show(
                fragmentManager,
                tag
            )
        }


    }
}