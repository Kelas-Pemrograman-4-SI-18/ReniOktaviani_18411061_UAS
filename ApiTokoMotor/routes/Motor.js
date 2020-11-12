const multer = require('multer'), fs = require('fs'), router = require('express').Router(),
    motor = require('../controller/Motor');
var storage = multer.diskStorage({
    filename: function (req, file, cb) {
        let ext = file.originalname.substring(
            file.originalname.lastIndexOf("."),
            file.originalname.length
        )
        cb(null, Date.now() + ext);
    },
    destination: function (req, file, cb) {
        cb(null, './gambar')
    }
})

var upload = multer({storage: storage}).single("gambar")

router.post("/input", upload, (req, res) => {
    motor.InputDataMotor(req.body, req.file.filename)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/datamotor", (req, res) => {
    motor.lihatDataMotor()
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/datamotor/:id",  (req, res) =>{
    motor.lihatDetailDataMotor(req.params.id)
        .then((result)=> res.json(result))
        .catch((err)=>res.json(err))
})

router.delete("/hapus/:id",(req, res)=> {
    motor.deleteMotor(req.params.id)
        .then((result)=> res.json(result))
        .catch((err)=>res.json(err))
})

router.put("/ubah/:id", upload,(req, res)=>{
    let fileName;
    if (req.body.gambar){
        fileName = req.body.gambar;
    }else {
        fileName = req.file.filename;
    }
    motor.updateMotor(req.params.id, req.body, fileName)
        .then((result)=> res.json(result))
        .catch((err)=> res.json(err))
})

module.exports = router