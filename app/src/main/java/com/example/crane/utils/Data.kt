package com.example.crane.utils

import android.content.Context
import com.example.crane.ui.items.CraneInfoUi
import com.example.crane.ui.items.CraneTypeUi
import com.example.domain.entities.*
import com.google.gson.Gson
import timber.log.Timber


fun getCraneInfoResponseFromAssetFile(context: Context): CraneInfoResponse {
    val gson = Gson()
    val assetManager = context.assets
    val inputStream = assetManager.open("CraneInfo")
    val data = inputStream.bufferedReader().use { it.readText() }
    inputStream.close()
    return gson.fromJson(data, CraneInfoResponse::class.java)
}

fun getCraneMechInfoResponseFromAssetFile(context: Context, id: Int): CraneMechInfo? {
    val gson = Gson()
    val assetManager = context.assets
    val inputStream = assetManager.open("CraneMechPartInfo")
    val data = inputStream.bufferedReader().use { it.readText() }
    inputStream.close()
    gson.fromJson(data, CraneMechInfoResponse::class.java).list.forEach {
        if (it.id == id)
            return it
    }
    return null
}

fun getCraneElInfoResponseFromAssetFile(context: Context, id: Int): CraneElInfo? {
    val gson = Gson()
    val assetManager = context.assets
    val inputStream = assetManager.open("CraneElPartInfo")
    val data = inputStream.bufferedReader().use { it.readText() }
    inputStream.close()
    gson.fromJson(data, CraneElInfoResponse::class.java).list.forEach {
        if (it.id == id)
            return it
    }
    return null
}


fun getCraneConstrInfoResponseFromAssetFile(context: Context): CraneConstrInfo? {
    val gson = Gson()
    val assetManager = context.assets
    val inputStream = assetManager.open("CraneConstrPartInfo")
    val data = inputStream.bufferedReader().use { it.readText() }
    inputStream.close()
    return gson.fromJson(data, CraneConstrInfo::class.java)
}

fun prepareInfoPdfData(
    list: List<CraneInfoUi>,
    firstName: String,
    secondName: String,
    lastName: String,
    jobPosition: String
): String {
    var data = ""
    data += "<h6>ФИО: $firstName $secondName $lastName</h6> <br>"
    data += "<h6>Должность: $jobPosition</h6> <br>"
    list.forEachIndexed { _, craneInfoUi ->
        if (craneInfoUi.subQuestions.isNotEmpty()) {
            var temp = ""
            craneInfoUi.subQuestions.forEach {
                if (!it.answer.isNullOrEmpty())
                    temp += "<h6>          ${it.question}: ${it.answer} </h6><br>"
            }
            if (temp.isNotEmpty()) {
                data += "<h6>${craneInfoUi.question}</h6>" + "<br>"
                data += temp
            }
        } else {
            if (!craneInfoUi.answer.isNullOrEmpty())
                data += "<h6>${craneInfoUi.question} ${craneInfoUi.answer}</h6> <br>"
        }
    }
    Timber.i("prepareInfoPdfData $data")

    return data

}

fun prepareCraneStatusPdfData(list: List<CraneTypeUi>): String {
    var data = ""
    list.forEachIndexed { i, craneTypeUi ->
        data += "<h6>${craneTypeUi.name}</h6>" + " <br>"
        with(craneTypeUi.value) {
            if (!cranePartsUiConstr.isNullOrEmpty()) {
                cranePartsUiConstr!!.forEachIndexed { index, cranePartsUi ->
                    data += "<h6>     ${cranePartsUi.name}</h6>" + "<br>"
                    cranePartsUi.pieces.forEachIndexed { index, cranePartPiecesUi ->
                        data += "<h6>          ${cranePartPiecesUi.name}: ${if (cranePartPiecesUi.value.satisfactory!!) "Удовлетворительно" else "Неудовлетворительно"} </h6><br>"
                        if (!cranePartPiecesUi.comment.isNullOrEmpty())
                            data += "               Комментарий: ${cranePartPiecesUi.comment} <br>"
                    }
                }
            }
            if (!cranePartsUiEl.isNullOrEmpty()) {
                cranePartsUiEl!!.forEachIndexed { index, cranePartsUi ->
                    data += "<h6>     ${cranePartsUi.name}</h6>" + "<br>"
                    cranePartsUi.pieces.forEachIndexed { index, cranePartPiecesUi ->
                        data += "<h6>          ${cranePartPiecesUi.name}: ${if (cranePartPiecesUi.value.satisfactory!!) "Удовлетворительно" else "Неудовлетворительно"}</h6> <br>"
                        if (!cranePartPiecesUi.comment.isNullOrEmpty())
                            data += "<h6>               Комментарий: ${cranePartPiecesUi.comment}</h6> <br>"
                    }
                }

            }
            if (!cranePartsUiMech.isNullOrEmpty()) {
                cranePartsUiMech!!.forEachIndexed { index, cranePartsUi ->
                    data += "<h6>     ${cranePartsUi.name}</h6>" + "<br>"
                    cranePartsUi.pieces.forEachIndexed { index, cranePartPiecesUi ->
                        data += "<h6>          ${cranePartPiecesUi.name}: ${if (cranePartPiecesUi.value.satisfactory!!) "Удовлетворительно" else "Неудовлетворительно"}</h6> <br>"
                        if (!cranePartPiecesUi.comment.isNullOrEmpty())
                            data += "<h6>               Комментарий: ${cranePartPiecesUi.comment}</h6> <br>"
                    }
                }
            }

        }
    }
    return data
}
