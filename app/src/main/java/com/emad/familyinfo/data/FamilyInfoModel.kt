package com.emad.familyinfo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
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

}