package com.example.lampcontrolapplication.presenter.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lampcontrolapplication.R
import com.example.lampcontrolapplication.data.model.BrightnessLevels
import com.example.lampcontrolapplication.data.model.LampSetParameterState
import com.example.lampcontrolapplication.data.model.LampState
import com.example.lampcontrolapplication.data.state.DataState
import com.example.lampcontrolapplication.databinding.FragmentMainBinding
import com.example.lampcontrolapplication.di.appComponent
import com.example.lampcontrolapplication.di.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main), AdapterView.OnItemSelectedListener {
    private val binding: FragmentMainBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

        binding.lampOnOrOffButton.setOnClickListener {
            viewModel.changeCurrentLampState()
        }
    }

    private fun setUpObservers() {
        viewModel.currentLampState.observe(viewLifecycleOwner) {
            when (it) {
                LampState.LAMP_ON -> binding.lampOnOrOffButton.text = "Выключить"
                else -> binding.lampOnOrOffButton.text = "Включить"
            }
        }

        viewModel.colors.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> setUpColorsSpinner(it.value)
                is DataState.Failure -> showErrorMessage(it.message)
                else -> {}
            }
        }

        viewModel.brightnessLevels.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> setUpBrightnessLevelsSeekBar(it.value)
                is DataState.Failure -> showErrorMessage(it.message)
                else -> {}
            }
        }

        viewModel.setColorResponseState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    if (it.value == LampSetParameterState.FAILURE_LAMP_OFF) {
                        viewModel.setLampStateToLampOff()
                        showErrorMessage("Лампа сейчас выключена")
                        return@observe
                    }

                    viewModel.setLampStateToLampOn()
                }

                is DataState.Failure -> showErrorMessage(it.message)
                else -> {}
            }
        }
        viewModel.setBrightnessLevelResponseState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    if (it.value == LampSetParameterState.FAILURE_LAMP_OFF) {
                        viewModel.setLampStateToLampOff()
                        showErrorMessage("Лампа сейчас выключена")
                        return@observe
                    }

                    viewModel.setLampStateToLampOn()
                }

                is DataState.Failure -> showErrorMessage(it.message)
                else -> {}
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setUpColorsSpinner(colors: List<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            colors
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorsSpinner.adapter = adapter
        binding.colorsSpinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
        viewModel.setColor(parent?.getItemAtPosition(index) as String)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun setUpBrightnessLevelsSeekBar(brightnessLevels: BrightnessLevels) {

        binding.lampBrightnessSeekBar.max =
            (brightnessLevels.maxLevel - brightnessLevels.minLevel) / brightnessLevels.levelPrecision

        viewModel.setLampBrightnessLevel(brightnessLevels.minLevel)

        binding.lampBrightnessSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    /*val brightnessLevel =
                        brightnessLevels.minLevel + progress * brightnessLevels.levelPrecision
                    viewModel.setLampBrightnessLevel(brightnessLevel)*/
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    val brightnessLevel =
                        brightnessLevels.minLevel + (seekBar?.progress
                            ?: 0) * brightnessLevels.levelPrecision
                    viewModel.setLampBrightnessLevel(brightnessLevel)
                }
            }
        )
    }
}