package com.oded.food.delivery.admin.view.fragment.employee.register

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.oded.food.delivery.admin.BuildConfig
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.common.Dialog
import com.oded.food.delivery.admin.common.Logger
import com.oded.food.delivery.admin.common.impl.ICallback
import com.oded.food.delivery.admin.databinding.FragmentAddEmployeeBinding
import com.oded.food.delivery.admin.view.fragment.provider.register.AddProviderFragment
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.model.AspectRatio
import java.io.File
import java.io.IOException
import java.util.*

class AddEmployeeFragment : Fragment() {

    private var _binding : FragmentAddEmployeeBinding? = null
    private val binding get() = _binding!!

    private var pathImage: String = ""
    private  var outputFileUri: Uri? = null
    private var uriPrevious: Uri? = null


    companion object {
        private const val REQUEST_SELECT_TAKE = 0x01
        private const val REQUEST_SELECT_PICTURE = 0x02
        private const val FORMAT_PHOTO = ".jpg"
        private const val INTENT_TYPE = "image/*"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddEmployeeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf("Admin", "Reviewer", "Checkout")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.layoutRolSpinner.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.profileImage.setOnClickListener {
            options()
        }

        binding.btnAddEmployee.setOnClickListener {
            save()
        }
    }

    private fun save() {

        binding.layoutName.error = ""
        binding.layoutLastname.error = ""
        binding.layoutEmail.error = ""
        binding.layoutUUID.error = ""
//        binding.layoutEditUsername.error = ""
//        binding.layoutEditConfirmedPassword.error = ""
        binding.layoutRolSpinner.error = ""

        if (binding.editUUID.text.isNullOrEmpty()) {
            binding.layoutUUID.error = getString(R.string.uuid)
            return
        }


        if (binding.editName.text.isNullOrEmpty()) {
            binding.layoutName.error = getString(R.string.name_is_empty)
            return
        }

        if (binding.editLastname.text.isNullOrEmpty()) {
            binding.layoutLastname.error = getString(R.string.lastname_is_empty)
            return
        }

//        if (!binding.rbFemale.isChecked && !binding.rbMale.isChecked) {
//            Toast.makeText(requireContext(), getString(R.string.you_must_select_the_gender), Toast.LENGTH_SHORT).show()
//            return
//        }

        if (binding.editEmail.text.isNullOrEmpty()) {
            binding.layoutEmail.error = getString(R.string.email_is_empty)
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.editEmail.text).matches()) {
            binding.layoutEmail.error = getString(R.string.inavlid_email)
            return
        }

//        if (binding.editUsername.text.isNullOrEmpty()) {
//            binding.layoutEditUsername.error = getString(R.string.username_empty)
//            return
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(binding.editUsername.text).matches()) {
//            binding.layoutEditUsername.error = getString(R.string.inavlid_email)
//            return
//        }
//
//
//        if (binding.editPassword.text.isNullOrEmpty()) {
//            binding.layoutEditPassword.error = getString(R.string.password_empty)
//            return
//        }
//
//        if (binding.editConfirmedPassword.text.isNullOrEmpty()) {
//            binding.layoutEditConfirmedPassword.error = getString(R.string.confirmed_password_empty)
//            return
//        }
//
//        if (binding.editPassword.text.toString() != binding.editConfirmedPassword.text.toString()) {
//            binding.layoutEditConfirmedPassword.error = getString(R.string.password_not_equal)
//            return
//        }
//
//        if (binding.editPassword.text.toString().length <= 7) {
//            binding.layoutEditConfirmedPassword.error = getString(R.string.password_greater_than_7_characters)
//            binding.layoutEditPassword.error = getString(R.string.password_greater_than_7_characters)
//        }

        if (binding.autoCompleteRole.text.isNullOrEmpty()) {
            binding.layoutRolSpinner.error = getString(R.string.role_is_empty)
            return
        }

        if (!binding.rbActivated.isChecked && !binding.rbBlocked.isChecked &&
            !binding.rbDeleted.isChecked) {
            Toast.makeText(requireContext(), getString(R.string.you_must_select_a_status_type), Toast.LENGTH_SHORT).show()
            return
        }




    }

    private fun options() {

        Dialog.showSingleSelection(requireContext(), getString(R.string.selected_option), mutableListOf(getString(
            R.string.gallery), getString(R.string.take_photo)), object : ICallback {
            override fun callback(param: Any?) {
                when(param as Int) {
                    0 -> {
                        onGallery()
                    } else -> {
                    onPermissionTakePhoto()
                }
                }
            }

        }, getString(R.string.ok), getString(R.string.cancel))
    }

    private fun onGallery() {
        val intent = Intent()
        intent.type = INTENT_TYPE
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(intent, getString(R.string.select_a_photo)),
            REQUEST_SELECT_PICTURE
        )
    }

    private fun onPermissionTakePhoto() {

        Dexter.withContext(activity)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(AppPermissionListener(this@AddEmployeeFragment))
            .check()

    }

    inner class AppPermissionListener(private val activity: AddEmployeeFragment) :
        PermissionListener {

        override fun onPermissionGranted(response: PermissionGrantedResponse) {
            activity.showPermissionGranted(response.permissionName)
        }

        override fun onPermissionDenied(response: PermissionDeniedResponse) {
            activity.showPermissionDenied(response.permissionName, response.isPermanentlyDenied)
        }

        override fun onPermissionRationaleShouldBeShown(
            permission: PermissionRequest,
            token: PermissionToken
        ) {
            activity.showPermissionRationale(token)
        }

    }

    fun showPermissionGranted(permission: String) {
        onTakePhoto()
    }

    private fun onTakePhoto() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoDir = File(
            activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            UUID.randomUUID().toString() + FORMAT_PHOTO
        )

        try {
            photoDir.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            outputFileUri = FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID, photoDir)

            val resInfoList = activity?.packageManager?.
            queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            if (resInfoList != null) {
                for (resolveInfo in resInfoList) {
                    val packageName = resolveInfo.activityInfo.packageName
                    activity?.grantUriPermission(
                        packageName, outputFileUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                }
            }
        } else {
            outputFileUri = Uri.fromFile(photoDir)
        }

        pathImage = photoDir.absolutePath
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        startActivityForResult(intent, REQUEST_SELECT_TAKE)
    }

    fun showPermissionDenied(permission: String, isPermanentlyDenied: Boolean) {
        Toast.makeText(context, R.string.permission_denied, Toast.LENGTH_SHORT).show()
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun showPermissionRationale(token: PermissionToken?) {
        AlertDialog.Builder(context).setTitle(R.string.permission_rationale_title)
            .setMessage(R.string.permission_rationale_message)
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
                token?.cancelPermissionRequest()
            }
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
                token?.continuePermissionRequest()
            }
            .setOnDismissListener { token?.cancelPermissionRequest() }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        try {

            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == REQUEST_SELECT_PICTURE) {

                    uriPrevious = data!!.data
                    if (uriPrevious != null) {
                        val uriDestination = Uri.fromFile(
                            File(
                                activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                                UUID.randomUUID().toString() + FORMAT_PHOTO
                            )
                        )
                        onImageCrop(uriPrevious!!, uriDestination)


                    } else {
                        Toast.makeText(
                            context,
                            getString(R.string.select_a_photo), Toast.LENGTH_SHORT
                        ).show()
                    }

                } else if (requestCode == UCrop.REQUEST_CROP) {
                    handleCropResult(data!!)
                } else if (requestCode == REQUEST_SELECT_TAKE) {
                    uriPrevious = Uri.fromFile(File(pathImage))
                    val uriDestination = Uri.fromFile(
                        File(
                            activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                            UUID.randomUUID().toString() + FORMAT_PHOTO
                        )
                    )
                    if (uriPrevious != null) {
                        onImageCrop(uriPrevious!!, uriDestination!!)
                    } else {
                        Toast.makeText(
                            context,
                            getString(R.string.select_a_photo), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        catch (e : Exception) {
            Logger.e(e.toString())
            Toast.makeText(
                context,
                getString(R.string.error_processing_photo), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun onImageCrop(aUriSource: Uri, aUriDestination: Uri) {

        val options = UCrop.Options()
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG)
        options.setCompressionQuality(100)
        options.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.accent))

        options.setToolbarWidgetColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        options.setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.accent))

        options.setAspectRatioOptions(
            0,
            AspectRatio("1:1", 1f, 1f)
        )
        options.withMaxResultSize(1000, 1000)

        Logger.e("aUriDestination ${aUriDestination.path!!}")
        Logger.e("aUriSource ${aUriSource.path!!}")

        UCrop.of(aUriSource, aUriDestination)
            .withOptions(options)
            .start(requireContext(), this@AddEmployeeFragment)

    }

    private fun handleCropResult(@NonNull result: Intent) {
        val resultUri = UCrop.getOutput(result)
        if (resultUri != null) {
            val path = resultUri.path
            Logger.e("handleCropResult ${path!!}")
            binding.profileImage.setImageBitmap(BitmapFactory.decodeFile(path))
        } else {
            Toast.makeText(context, getString(R.string.select_a_photo), Toast.LENGTH_SHORT).show()
        }
    }
}