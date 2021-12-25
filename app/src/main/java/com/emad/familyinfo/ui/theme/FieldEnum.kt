package com.emad.familyinfo.ui.theme

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.*

enum class FieldEnum(val text: String,val options: Array<String>?, val keyboardType: KeyboardType) {
    HH_NAME("نام سرپرست خانوار", null, KeyboardType.Text),
    WIFE_NAME("نام همسر(ها)", null, KeyboardType.Text),
    BOY_COUNT("تعداد پسر", null, KeyboardType.Number),
    GIRL_COUNT("تعداد دختر", null, KeyboardType.Number),
    SUPPORT_COUNT("تعداد افراد تحت تکفل", null, KeyboardType.Number),
    NATIONAL_CODE("کد ملی", null, KeyboardType.Phone),
    POSTAL_CODE("کد پستی", null, KeyboardType.Number),
    HOUSING_TYPE("نوع تملک خانه", housingTypes, KeyboardType.Text),
    DEGREE("مدرک تحصیلی", null, KeyboardType.Text),
    JOB("شغل", null, KeyboardType.Text),
    FINANCIAL_ACT("فعالیت اقتصادی خاص و ویژه", null, KeyboardType.Text),
    EXPERTISE_TYPE("نوع تخصص", null, KeyboardType.Text),
    INSURANCE_TYPE("نوع بیمه درمانی", null, KeyboardType.Text),
    ISARGARI("نوع ایثارگری", isargariTypes, KeyboardType.Text),
    RARE_DISEASE("بیماری خاص در خانواده", null, KeyboardType.Text),
    PHONE_NUMBER_HOME("شماره تماس ثابت", null, KeyboardType.Phone),
    PHONE_NUMBER_MOBILE("شماره تماس همراه", null, KeyboardType.Phone),
    PHONE_NUMBER_EMS("شماره تماس اضطراری", null, KeyboardType.Phone),
    ADDRESS("آدرس محل سکونت", null, KeyboardType.Text),
    EXTRA_INFO("اطلاعات تکمیلی و ضروری", null, KeyboardType.Text),

}

val housingTypes: Array<String> = arrayOf("شخصی", "استیجاری", "سازمانی")
val isargariTypes: Array<String> = arrayOf("شهید", "جانباز", "رزمنده", "هیچکدام")