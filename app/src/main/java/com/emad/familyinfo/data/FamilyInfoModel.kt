package com.emad.familyinfo.data

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emad.familyinfo.ui.theme.FieldEnum
import java.io.Serializable

@Entity(tableName = "family_table")
class FamilyInfoModel : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String? = null
    var nid: String? = null
    var postal: String? = null
    var wifeName: String? = null
    var boyCount: String? = null
    var girlCount: String? = null
    var supportedCount: String? = null
    var housing: String? = null
    var job: String? = null
    var degree: String? = null
    var financialActivity: String? = null
    var expertise: String? = null
    var insurance: String? = null
    var isargari: String? = null
    var disease: String? = null
    var phoneNumber: String? = null
    var homeNumber: String? = null
    var emsNumber: String? = null
    var address: String? = null
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