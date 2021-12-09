package com.emad.familyinfo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import com.emad.familyinfo.ui.theme.*
import com.emad.familyinfo.ui.theme.FieldEnum.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyInfoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.Black) {
                    val selectedHousing = remember { mutableStateOf("") }
                    val selectedIsargari = remember { mutableStateOf("") }

                    val dialogOpen = remember { mutableStateOf(false) }
                    val dialogEnum = remember { mutableStateOf(HOUSING_TYPE) }

                    if (dialogOpen.value) {
                        showDialog(dialogEnum.value,
                            onDismiss = { dialogOpen.value = false },
                            onItemChosen = {
                                when (dialogEnum.value) {
                                    HOUSING_TYPE -> {
                                        selectedHousing.value = it
                                    }
                                    ISARGARI -> {
                                        selectedIsargari.value = it
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
                        TextInputField(HH_NAME)
                        TextInputField(WIFE_NAME)
                        TextInputField(BOY_COUNT)
                        TextInputField(GIRL_COUNT)
                        TextInputField(SUPPORT_COUNT)
                        TextInputField(NATIONAL_CODE)
                        TextInputField(POSTAL_CODE)
                        if (selectedHousing.value != "")
                            SelectionField(selectedHousing.value, HOUSING_TYPE) {
                                dialogOpen.value = true
                                dialogEnum.value = HOUSING_TYPE
                                selectedHousing.value = ""
                            }
                        else SelectionField(null, HOUSING_TYPE) {
                            dialogOpen.value = true
                            dialogEnum.value = HOUSING_TYPE
                        }
                        TextInputField(DEGREE)
                        TextInputField(JOB)
                        TextInputField(FINANCIAL_ACT)
                        TextInputField(EXPERTISE_TYPE)
                        TextInputField(INSURANCE_TYPE)
                        if (selectedIsargari.value != "")
                            SelectionField(selectedIsargari.value, ISARGARI) {
                                dialogOpen.value = true
                                dialogEnum.value = ISARGARI
                                selectedIsargari.value = ""
                            }
                        else SelectionField(null, ISARGARI) {
                            dialogOpen.value = true
                            dialogEnum.value = ISARGARI
                        }
                        TextInputField(RARE_DISEASE)
                        TextInputField(PHONE_NUMBER_HOME)
                        TextInputField(PHONE_NUMBER_MOBILE)
                        TextInputField(PHONE_NUMBER_EMS)
                        TextInputField(ADDRESS)
                        TextInputField(EXTRA_INFO)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TextInputField(fieldEnum: FieldEnum = HH_NAME) {
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
            onValueChange = { text = it },
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
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(
                text = fieldEnum.text,
                Modifier
                    .padding(all = 6.dp)
                    .fillMaxWidth()
                    .background(color = Color.DarkGray),
                style = myTextStyle()
            )
        },
        text = {
            Column {
                fieldEnum.options?.let { array ->
                    array.forEach { option ->
                        Text(
                            option,
                            Modifier
                                .padding(all = 6.dp)
                                .clickable {
                                    onItemChosen(option)
                                    onDismiss()
                                }
                                .fillMaxWidth()
                                .background(color = Color.Gray, shape = TopRoundedShape),
                            style = myTextStyle()
                        )
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