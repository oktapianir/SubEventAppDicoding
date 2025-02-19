//package com.okta.subeventappdicoding.fragment
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.appcompat.app.AppCompatDelegate
//import androidx.fragment.app.Fragment
//import com.okta.subeventappdicoding.R
//import com.okta.subeventappdicoding.databinding.FragmentSettingBinding
//
//class SettingFragment : Fragment() {
//    private var _binding: FragmentSettingBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var sharedPreferences: SharedPreferences
//
////    override fun onCreateView(
////        inflater: LayoutInflater, container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View {
////        _binding = FragmentSettingBinding.inflate(inflater, container, false)
////        return binding.root
////    }
//override fun onCreateView(
//    inflater: LayoutInflater, container: ViewGroup?,
//    savedInstanceState: Bundle?
//): View? {
//    // Inflate the layout for this fragment
//    _binding = FragmentSettingBinding.inflate(inflater, container, false)
//    // Initialize SharedPreferences
//    sharedPreferences = requireContext().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
//    // Load saved theme preference
//    val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
//    // Set the switch state based on the saved preference
//    binding.switchTheme.isChecked = isDarkMode
//    // Apply the theme based on the saved preference
//    applyTheme(isDarkMode)
//    return binding.root
//}
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        sharedPreferences = requireContext().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
//        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
//
//        binding.switchTheme.isChecked = isDarkMode
//        applyTheme(isDarkMode)
//
//        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
//            sharedPreferences.edit().apply {
//                putBoolean("dark_mode", isChecked)
//                apply()
//            }
//            Log.d("ThemePreference", "Dark mode saved: $isChecked")  // Tambahkan log untuk memeriksa
//
//            applyTheme(isChecked)
//        }
//
//    }
//
//    private fun applyTheme(isDarkMode: Boolean) {
//        AppCompatDelegate.setDefaultNightMode(
//            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
//            else AppCompatDelegate.MODE_NIGHT_NO
//        )
//
//        // Menyesuaikan warna elemen UI
//        val textColor = if (isDarkMode) {
//            resources.getColor(R.color.white, requireContext().theme) // Warna putih untuk mode gelap
//        } else {
//            resources.getColor(R.color.black, requireContext().theme) // Warna hitam untuk mode terang
//        }
//
//        binding.switchTheme.setTextColor(textColor) // Ganti warna teks pada switch
//
//        }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}


//
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.appcompat.app.AppCompatDelegate
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import com.okta.subeventappdicoding.R
////import com.okta.subeventappdicoding.ReminderStatus
////import com.okta.subeventappdicoding.SettingViewModel
//import com.okta.subeventappdicoding.databinding.FragmentSettingBinding
//
//
//class SettingFragment : Fragment() {
//    private var _binding: FragmentSettingBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var sharedPreferences: SharedPreferences
////    private lateinit var viewModel: SettingViewModel // Deklarasikan ViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentSettingBinding.inflate(inflater, container, false)
//
//        // Inisialisasi SharedPreferences
//        sharedPreferences = requireContext().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
//
//        // Ambil preferensi mode gelap yang tersimpan
//        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
//
//        // Setel status switch berdasarkan preferensi
//        binding.switchTheme.isChecked = isDarkMode
//
//        // Terapkan tema berdasarkan preferensi
//        applyTheme(isDarkMode)
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Inisialisasi ViewModel
////        viewModel = ViewModelProvider(this)[SettingViewModel::class.java]
//
//        // Atur switch untuk mode gelap
//        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
//            sharedPreferences.edit().apply {
//                putBoolean("dark_mode", isChecked)
//                apply()
//            }
//            Log.d("ThemePreference", "Dark mode saved: $isChecked")  // Tambahkan log untuk debug
//            applyTheme(isChecked)
//        }
//
////        setupReminderSwitch()
////        observeReminderStatus()
//    }
//
////    private fun setupReminderSwitch() {
////        binding.switchReminder.setOnCheckedChangeListener { _, isChecked ->
////            if (isChecked) {
//////                viewModel.getActiveEvent()
////            } else {
////                viewModel.cancelReminder()
////            }
////        }
////    }
////
////    private fun observeReminderStatus() {
////        viewModel.reminderStatus.observe(viewLifecycleOwner) { status ->
////            when (status) {
////                is ReminderStatus.Success -> {
////                    Toast.makeText(
////                        requireContext(),
////                        "Reminder set for: ${status.event.name}",
////                        Toast.LENGTH_SHORT
////                    ).show()
////                }
////                is ReminderStatus.Error -> {
////                    binding.switchReminder.isChecked = false
////                    Toast.makeText(
////                        requireContext(),
////                        "Failed to set reminder: ${status.message}",
////                        Toast.LENGTH_SHORT
////                    ).show()
////                }
////                is ReminderStatus.Canceled -> {
////                    Toast.makeText(
////                        requireContext(),
////                        "Reminder canceled",
////                        Toast.LENGTH_SHORT
////                    ).show()
////                  }
////            }
////        }
////    }
//
//    private fun applyTheme(isDarkMode: Boolean) {
//        AppCompatDelegate.setDefaultNightMode(
//            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
//            else AppCompatDelegate.MODE_NIGHT_NO
//        )
//
//        // Sesuaikan warna elemen UI
//        val textColor = ContextCompat.getColor(
//            requireContext(),
//            if (isDarkMode) R.color.white else R.color.black
//        )
//
//        binding.switchTheme.setTextColor(textColor) // Ubah warna teks switch
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
//

package com.okta.subeventappdicoding.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.okta.subeventappdicoding.R
import com.okta.subeventappdicoding.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var themeObserver: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        setupThemePreferences()
        return binding.root
    }

    private fun setupThemePreferences() {
        // Inisialisasi SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)

        // Membuat observer untuk perubahan tema
        themeObserver = SharedPreferences.OnSharedPreferenceChangeListener { prefs, key ->
            if (key == "dark_mode") {
                val isDarkMode = prefs.getBoolean(key, false)
                applyTheme(isDarkMode)
            }
        }

        // Mendaftarkan observer
        sharedPreferences.registerOnSharedPreferenceChangeListener(themeObserver)

        // Set switch state berdasarkan tema yang tersimpan
        val savedTheme = sharedPreferences.getBoolean("dark_mode", false)
        binding.switchTheme.isChecked = savedTheme
        applyTheme(savedTheme)

        // Listener untuk switch tema
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().apply {
                putBoolean("dark_mode", isChecked)
                apply()
            }
        }
    }

//    private fun applyTheme(isDarkMode: Boolean) {
//        AppCompatDelegate.setDefaultNightMode(
//            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
//            else AppCompatDelegate.MODE_NIGHT_NO
//        )
//
//        // Update UI elements based on theme
//        val textColor = ContextCompat.getColor(
//            requireContext(),
//            if (isDarkMode) R.color.white else R.color.black
//        )
//        binding.switchTheme.setTextColor(textColor)
//
//            val backgroundColorRes = if (isDarkMode) R.color.cardview_dark_background else R.color.cardview_light_background
//            val backgroundColor = ContextCompat.getColor(requireContext(), backgroundColorRes)
//
//            // Update CardView background
//            binding.settingsCardView.setCardBackgroundColor(backgroundColor)
//
//            // Update LinearLayout inside CardView
//            (binding.settingsCardView.getChildAt(0) as? LinearLayout)?.setBackgroundColor(backgroundColor)
//
//            // Update TextView colors inside settingsCardView
//            updateTextViewColors(binding.settingsCardView, textColor)
//    }

    private fun applyTheme(isDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        // Update UI elements based on theme
        val textColor = ContextCompat.getColor(
            requireContext(),
            if (isDarkMode) R.color.white else R.color.black
        )
        binding.switchTheme.setTextColor(textColor)

        // Gunakan androidx.cardview.R.color untuk warna background
        val backgroundColorRes = if (isDarkMode)
            androidx.cardview.R.color.cardview_dark_background
        else
            androidx.cardview.R.color.cardview_light_background

        val backgroundColor = ContextCompat.getColor(requireContext(), backgroundColorRes)

        // Update CardView background
        binding.settingsCardView.setCardBackgroundColor(backgroundColor)

        // Update LinearLayout inside CardView
        (binding.settingsCardView.getChildAt(0) as? LinearLayout)?.setBackgroundColor(backgroundColor)

        // Update TextView colors inside settingsCardView
        updateTextViewColors(binding.settingsCardView, textColor)
    }

    private fun updateTextViewColors(view: View, textColor: Int) {
        when (view) {
            is ViewGroup -> {
                for (i in 0 until view.childCount) {
                    updateTextViewColors(view.getChildAt(i), textColor)
                }
            }
            is TextView -> {
                if (view.id != R.id.daily_reminder_desc) {
                    view.setTextColor(textColor)
                }
            }
        }
    }

    override fun onDestroyView() {
        // Unregister observer saat fragment dihancurkan
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(themeObserver)
        super.onDestroyView()
        _binding = null
    }
}