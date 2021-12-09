package com.emad.familyinfo.ui.theme

enum class FieldEnum(val text: String,val options: Array<String>?) {
    HH_NAME("نام سرپرست خانوار", null),
    WIFE_NAME("نام همسر(ها)", null),
    BOY_COUNT("تعداد پسر", null),
    GIRL_COUNT("تعداد دختر", null),
    SUPPORT_COUNT("تعداد افراد تحت تکفل", null),
    NATIONAL_CODE("کد ملی", null),
    POSTAL_CODE("کد پستی", null),
    HOUSING_TYPE("نوع تملک خانه", housingTypes),
    DEGREE("مدرک تحصیلی", null),
    JOB("شغل", null),
    FINANCIAL_ACT("فعالیت اقتصادی خاص و ویژه", null),
    EXPERTISE_TYPE("فعالیت اقتصادی خاص و ویژه", null),
    INSURANCE_TYPE("فعالیت اقتصادی خاص و ویژه", null),
    ISARGARI("نوع ایثارگری", isargariTypes),
    RARE_DISEASE("بیماری خاص در خانواده", null),
    PHONE_NUMBER_HOME("شماره تماس ثابت", null),
    PHONE_NUMBER_MOBILE("شماره تماس همراه", null),
    PHONE_NUMBER_EMS("شماره تماس اضطراری", null),
    ADDRESS("آدرس محل سکونت", null),
    EXTRA_INFO("اطلاعات تکمیلی و ضروری", null),

}

val housingTypes: Array<String> = arrayOf("شخصی", "استیجاری", "سازمانی")
val isargariTypes: Array<String> = arrayOf("شهید", "جانباز", "رزمنده")