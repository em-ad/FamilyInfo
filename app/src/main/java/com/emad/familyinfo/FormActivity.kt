package com.emad.familyinfo

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_DOCUMENTS
import android.os.Handler
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.emad.familyinfo.data.FamilyInfoModel
import com.emad.familyinfo.data.RepositoryManagerLocal
import com.emad.familyinfo.ui.theme.*
import com.emad.familyinfo.ui.theme.FieldEnum.*
import com.github.doyaaaaaken.kotlincsv.client.KotlinCsvExperimental
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import com.google.gson.Gson
import java.io.File
import java.io.InputStreamReader
import android.content.Intent
import android.net.Uri
import android.widget.Toast.LENGTH_LONG
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import java.net.URLConnection
import android.content.pm.PackageManager
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.opencsv.bean.StatefulBeanToCsv
import com.opencsv.bean.StatefulBeanToCsvBuilder
import java.io.FileWriter
import java.io.Writer
import com.opencsv.CSVWriter


class FormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FamilyInfoTheme {
                Surface(color = Color.Black) {
                    val selectedHousing = remember { mutableStateOf("") }
                    val selectedIsargari = remember { mutableStateOf("") }
                    val dialogOpen = remember { mutableStateOf(false) }
                    val dialogEnum = remember { mutableStateOf(HOUSING_TYPE) }
                    val validFields = remember { mutableStateOf(0) }
                    val formModel = remember { mutableStateOf(FamilyInfoModel()) }
                    var showDialog by remember { mutableStateOf(false) }

                    if (showDialog) {
                        Dialog(
                            onDismissRequest = { showDialog = false },
                            DialogProperties(
                                dismissOnBackPress = false,
                                dismissOnClickOutside = false
                            )
                        ) {
                            Box(
                                contentAlignment = Center,
                                modifier = Modifier
                                    .size(150.dp)
                                    .background(White, shape = RoundedCornerShape(8.dp))
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    if (dialogOpen.value) {
                        showDialog(dialogEnum.value,
                            onDismiss = { dialogOpen.value = false },
                            onItemChosen = {
                                when (dialogEnum.value) {
                                    HOUSING_TYPE -> {
                                        selectedHousing.value = it
                                        formModel.value.changeAttribute(FieldEnum.HOUSING_TYPE, it)
                                    }
                                    ISARGARI -> {
                                        selectedIsargari.value = it
                                        formModel.value.changeAttribute(FieldEnum.ISARGARI, it)
                                    }
                                }
                            })
                    }
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Button(
                            onClick = {
                                if (!formModel.value.hasNull())
                                    insertFormIntoDb(formModel.value)
                                else {
                                    Toast.makeText(
                                        this@FormActivity,
                                        "فرم فعلی کامل نیست و در گزارش نخواهد بود!",
                                        LENGTH_LONG
                                    ).show()
                                }
                                showDialog = true
                                Handler().postDelayed({
                                    shareFile()
                                    showDialog = false
                                }, 1500)
                            },
                            Modifier
                                .background(
                                    color = Color.Yellow,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "دریافت گزارش اکسل",
                                fontFamily = myFontBold,
                                color = Color.Black,
                                fontSize = 17.sp,
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )
                        }
                        TextInputField2(HH_NAME, formModel)
                        TextInputField2(WIFE_NAME, formModel)
                        TextInputField2(BOY_COUNT, formModel)
                        TextInputField2(GIRL_COUNT, formModel)
                        TextInputField2(SUPPORT_COUNT, formModel)
                        TextInputField2(NATIONAL_CODE, formModel)
                        TextInputField2(POSTAL_CODE, formModel)
                        if (selectedHousing.value != "")
                            SelectionField(selectedHousing.value, HOUSING_TYPE) {
                                dialogOpen.value = true
                                dialogEnum.value = HOUSING_TYPE
                                selectedHousing.value = ""
                                validFields.value--
                            }
                        else SelectionField(null, HOUSING_TYPE) {
                            dialogOpen.value = true
                            dialogEnum.value = HOUSING_TYPE
                            validFields.value++
                        }
                        TextInputField2(DEGREE, formModel)
                        TextInputField2(JOB, formModel)
                        TextInputField2(FINANCIAL_ACT, formModel)
                        TextInputField2(EXPERTISE_TYPE, formModel)
                        TextInputField2(INSURANCE_TYPE, formModel)
                        if (selectedIsargari.value != "")
                            SelectionField(selectedIsargari.value, ISARGARI) {
                                dialogOpen.value = true
                                dialogEnum.value = ISARGARI
                                selectedIsargari.value = ""
                                validFields.value--
                            }
                        else SelectionField(null, ISARGARI) {
                            dialogOpen.value = true
                            dialogEnum.value = ISARGARI
                            validFields.value++
                        }
                        TextInputField2(RARE_DISEASE, formModel)
                        TextInputField2(PHONE_NUMBER_HOME, formModel)
                        TextInputField2(PHONE_NUMBER_MOBILE, formModel)
                        TextInputField2(PHONE_NUMBER_EMS, formModel)
                        TextInputField2(ADDRESS, formModel)
                        TextInputField2(EXTRA_INFO, formModel)
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Button(
                            onClick = {
                                if (!formModel.value.hasNull()) {
                                    insertFormIntoDb(formModel.value)
                                    showDialog = true
                                    Handler().postDelayed({
                                        showDialog = false
                                        recreate()
                                    }, 1000)
                                } else {
                                    Toast.makeText(
                                        this@FormActivity,
                                        "لطفا همه موارد را تکمیل کنید!",
                                        LENGTH_SHORT
                                    ).show()
                                }
                            },
                            Modifier
                                .background(
                                    color = Color.Yellow,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "ذخیره و ادامه",
                                fontFamily = myFont,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )
                        }
                    }
                }
            }
        }
        checkPermissions()
    }

    private fun checkPermissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )

        var result: Int
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(this, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), 100)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            checkPermissions()
        }
    }

    private fun insertFormIntoDb(value: FamilyInfoModel) {
        RepositoryManagerLocal.newInstance(this@FormActivity).database.familyInfoDao().insert(value)
    }

    private fun shareFile() {

        val file = File(getExternalFilesDir(null).toString() + "/report.csv")

        RepositoryManagerLocal.newInstance(this@FormActivity).database.familyInfoDao()
            .fetchAllData().observe(this, Observer {
                if (it.size == 0) {
                    Toast.makeText(
                        this@FormActivity,
                        "گزارشی در دستگاه ذخیره نشده است!",
                        LENGTH_SHORT
                    ).show()
                    return@Observer
                }

                val keys = ArrayList<String>()
                for (i in it[0].javaClass.declaredFields.indices) {
                    keys.add(it[0].javaClass.declaredFields[i].name)
                }

                val writer: Writer = FileWriter(file)
                val beanToCsv: StatefulBeanToCsv<FamilyInfoModel> =
                    StatefulBeanToCsvBuilder<FamilyInfoModel>(writer).build()
                for (item in it) {
                    beanToCsv.write(item)
                }
                writer.close()


                val intentShareFile = Intent(Intent.ACTION_SEND)
                intentShareFile.type = URLConnection.guessContentTypeFromName(file.name)
                intentShareFile.putExtra(
                    Intent.EXTRA_STREAM,
                    FileProvider.getUriForFile(this, "com.emad.familyinfo", file)
                )

                startActivity(
                    this,
                    Intent.createChooser(intentShareFile, "به اشتراک گذاری گزارش"),
                    null
                )
            })
    }
}

@Preview
@Composable
fun TextInputField(
    fieldEnum: FieldEnum = HH_NAME,
    validFields: MutableState<Int> = mutableStateOf(0)
) {
    var text by remember { mutableStateOf("") }
    Box(
        Modifier
            .padding(top = 16.dp)
//            .background(color = Color.DarkGray)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(color = Color.DarkGray, shape = TopRoundedShape),
            textStyle = myTextStyle(),
            value = text,
            shape = TopRoundedShape,
            onValueChange = {
                if (it.isBlank() && text.isNotBlank())
                    validFields.value--
                else if (text.isBlank() && it.isNotBlank())
                    validFields.value++
                text = it
            },
            label = {
                Text(
                    fieldEnum.text,
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent),
                    style = myTextStyle()
                )
            },
        )
    }
}

@Composable
fun TextInputField2(fieldEnum: FieldEnum = HH_NAME, formModel: MutableState<FamilyInfoModel>) {
    var text by remember { mutableStateOf("") }
    Box(
        Modifier
            .padding(top = 16.dp)
    ) {
        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = fieldEnum.keyboardType),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight()
                .background(color = Color.DarkGray, shape = TopRoundedShape),
            textStyle = myTextStyle(),
            value = text,
            shape = TopRoundedShape,
            onValueChange = {
                text = it
                formModel.value.changeAttribute(fieldEnum, it)
            },
            label = {
                Text(
                    fieldEnum.text,
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent),
                    style = myTextStyle()
                )
            },
        )
    }
}

@Composable
fun SelectionField(
    oldSelection: String?,
    fieldEnum: FieldEnum = HOUSING_TYPE,
    clicked: () -> Unit
) {
    lateinit var initValue: String
    if (oldSelection != null)
        initValue = oldSelection
    else initValue = fieldEnum.text
    var text by remember { mutableStateOf(initValue) }
    Column(
        Modifier
            .padding(top = 16.dp)
            .background(color = Color.DarkGray, shape = TopRoundedShape)
            .clickable(enabled = true, onClick = clicked),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(color = Color.Transparent)
                .padding(all = 14.dp)
                .clickable(enabled = true, onClick = clicked),
            style = myTextStyle(),
        )
        Divider(color = Orange, thickness = 2.dp, modifier = Modifier.fillMaxWidth(0.8f))
    }
}


@Preview
@Composable
fun showDialog(
    fieldEnum: FieldEnum = HOUSING_TYPE,
    onDismiss: () -> Unit = {},
    onItemChosen: (chosen: String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        modifier = Modifier.padding(8.dp),
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .background(
                        color = Orange,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                Text(
                    text = fieldEnum.text,
                    color = Color.Black,
                    style = myTextStyle(),
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .fillMaxWidth()
                )
            }
        },
        text = {
            Column() {
                HackySpacer(space = 12.dp)
                fieldEnum.options?.let { array ->
                    array.forEach { option ->
                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                                .background(color = Color.Gray, shape = RoundedCornerShape(6.dp)),
                        ) {
                            Text(
                                option,
                                Modifier
                                    .padding(all = 6.dp)
                                    .clickable {
                                        onItemChosen(option)
                                        onDismiss()
                                    }
                                    .fillMaxWidth(),
                                style = myTextStyle()
                            )
                        }
                    }
                }
            }
        },
        buttons = {}
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FamilyInfoTheme {
        showDialog(onDismiss = { }, onItemChosen = { })
    }
}


fun myTextStyle(): TextStyle {
    return TextStyle(
        fontSize = 15.sp,
        textAlign = TextAlign.Right,
        fontFamily = myFont,
        color = Color.White
    )
}

@Composable
fun HackySpacer(space: Dp) {
    Box(
        modifier = Modifier
            .height(space)
            .fillMaxWidth()
    ) {
        Text(text = "")
    }
}