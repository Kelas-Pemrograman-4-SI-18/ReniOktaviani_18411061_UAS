const mongoose = require('mongoose');

const userSchema = mongoose.Schema ({

    kodemotor:{
        type: String
    },
    namamerk : {
        type: String
    },
    jenismotor : {
        type: String
    },
    warna : {
        type: String
    },
    tahunmotor : {
        type:String
    },
    hargamotor : {
        type : String
    },
    gambar: {
        type: String
    }
})

module.exports = mongoose.model('motor' , userSchema)
