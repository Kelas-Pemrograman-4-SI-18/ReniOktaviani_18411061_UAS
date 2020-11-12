const motor = require('../model/Motor.js')
const response = require ('../config/response')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId


exports.InputDataMotor = (data, gambar) =>
    new Promise(async (resolve, reject)=> {

        const motorBaru = new motor({
            kodemotor: data.kodemotor,
            namamerk: data.namamerk,
            jenismotor: data.jenismotor,
            warna: data.warna,
            tahunmotor: data.tahunmotor,
            hargamotor: data.hargamotor,
            gambar: gambar

        })

        await motor.findOne({kodemotor: data.kodemotor})
            .then(motor => {
                if (motor){
                    reject(response.commonErrorMsg('Kode Motor Sudah Digunakan'))
                } else {
                    motorBaru.save()
                        .then(r=>{
                            resolve(response.commonSuccessMsg('Berhasil Menginput Data'))
                        }).catch(err =>{
                        reject(response.commonErrorMsg('Mohon Maaf Input Motor Gagal'))

                    })
                }
            }).catch(err => {
                reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server Kami'))

            })
    })

exports.lihatDataMotor = () =>
    new Promise(async (resolve, reject)=>{
        await motor.find({})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server Kami'))
            )
    })

exports.updateMotor = (id, data, gambar) =>
    new Promise(async (resolve, reject)=>{
        await motor.updateOne(
            {_id : ObjectId(id)},
            {
                $set: {
                    kodemotor : data.kodemotor,
                    namamerk : data.namamerk,
                    jenismotor : data.jenismotor,
                    warna : data.warna,
                    tahunmotor : data.tahunmotor,
                    hargamotor : data.hargamotor,
                    gambar: gambar

                }
            }
        ).then(motor =>{
            resolve(response.commonSuccessMsg('Berhasil Mengubah Data'))
        }).catch(err => {
            reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server Kami'))
        })
    })
exports.lihatDetailDataMotor = (kodemotor) =>
    new Promise(async (resolve, reject)=>{
        await motor.findOne({kodemotor: kodemotor})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server Kami'))
            )
    })

exports.deleteMotor = (_id) =>
    new Promise(async (resolve, reject)=>{
        await motor.remove({_id: ObjectId(_id)})
            .then(()=> {
                resolve(response.commonErrorMsg('Berhasil Menghapus Data'))
            }).catch(() => {
                reject(response.commonErrorMsg('Mohon maaf Terjadi Kesalahan pada server kami'))
            })
    })