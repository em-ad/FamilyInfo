package com.emad.familyinfo.data

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emad.familyinfo.ui.theme.FieldEnum
import com.opencsv.bean.CsvBindByName
import java.io.Serializable

@Entity(tableName = "family_table")
class FamilyInfoModel : Serializable {

    @CsvBindByName
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @CsvBindByName

    var name: String? = null

    @CsvBindByName

    var nid: String? = null

    @CsvBindByName

    var postal: String? = null

    @CsvBindByName

    var wifeName: String? = null

    @CsvBindByName

    var boyCount: String? = null

    @CsvBindByName

    var girlCount: String? = null

    @CsvBindByName

    var supportedCount: String? = null

    @CsvBindByName

    var housing: String? = null

    @CsvBindByName

    var job: String? = null

    @CsvBindByName

    var degree: String? = null

    @CsvBindByName

    var financialActivity: String? = null

    @CsvBindByName

    var expertise: String? = null

    @CsvBindByName

    var insurance: String? = null

    @CsvBindByName

    var isargari: String? = null

    @CsvBindByName

    var disease: String? = null

    @CsvBindByName

    var phoneNumber: String? = null

    @CsvBindByName

    var homeNumber: String? = null

    @CsvBindByName

    var emsNumber: String? = null

    @CsvBindByName

    var address: String? = null

    @CsvBindByName

    var description: String? = null

    fun changeAttribute(fieldEnum: FieldEnum, it: String) {
        when (fieldEnum) {
            FieldEnum.HH_NAME -> {
                this.name = it
            }
            FieldEnum.WIFE_NAME -> {
                this.wifeName = it
            }
            FieldEnum.BOY_COUNT -> {
                this.boyCount = it
            }
            FieldEnum.GIRL_COUNT -> {
                this.girlCount = it
            }
            FieldEnum.SUPPORT_COUNT -> {
                this.supportedCount = it
            }
            FieldEnum.NATIONAL_CODE -> {
                this.nid = it
            }
            FieldEnum.POSTAL_CODE -> {
                this.postal = it
            }
            FieldEnum.HOUSING_TYPE -> {
                this.housing = it
            }
            FieldEnum.DEGREE -> {
                this.degree = it
            }
            FieldEnum.JOB -> {
                this.job = it
            }
            FieldEnum.FINANCIAL_ACT -> {
                this.financialActivity = it
            }
            FieldEnum.EXPERTISE_TYPE -> {
                this.expertise = it
            }
            FieldEnum.INSURANCE_TYPE -> {
                this.insurance = it
            }
            FieldEnum.ISARGARI -> {
                this.isargari = it
            }
            FieldEnum.RARE_DISEASE -> {
                this.disease = it
            }
            FieldEnum.PHONE_NUMBER_HOME -> {
                this.homeNumber = it
            }
            FieldEnum.PHONE_NUMBER_MOBILE -> {
                this.phoneNumber = it
            }
            FieldEnum.PHONE_NUMBER_EMS -> {
                this.emsNumber = it
            }
            FieldEnum.ADDRESS -> {
                this.address = it
            }
            FieldEnum.EXTRA_INFO -> {
                this.description = it
            }
        }
    }

    fun hasNull(): Boolean {
        for (i in this.javaClass.declaredFields.indices) {
            if (this.javaClass.declaredFields[i].get(this) == null)
                return true
        }
        return false
    }

    fun getValues(): ArrayList<String> {
        var res = ArrayList<String>()
        for (i in this.javaClass.declaredFields.indices) {
            res.add(this.javaClass.declaredFields[i].get(this).toString())
        }
        return res
    }

}