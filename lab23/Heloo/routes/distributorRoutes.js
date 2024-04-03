var express = require('express');
var router = express.Router();
const modeDistributor = require('../models/distributor')


router.get('/test', function(reg, res, next){
    res.send('respond with a resource distributor test');
});

//add data

router.post('/add', async(req, res) =>{
    try{
        const model = new modeDistributor(req.body)
        const result = await model.save(); //thêm  dl vaò đata
        if (result){
            res.json({
                "status" :200,
                "message": "Thêm thành công",
                "data": result
            })
        }else{
            res.json({
                "status" :400,
                "message": "Thêm thất bại",
                "data": result
            })
        }
        // res.send(result)
    }catch(error){
        console.log(error);

    }
})
//
router.get('/list', async(req, res) =>{
    
    try {
        const result = await modeDistributor.find({})
        // res.send(result)
        if (result){
            res.json({
                "status" :200,
                "message": "list ",
                "data": result
            })
        }else{
            res.json({
                "status" :400,
                "message": "lỗi không có dữ liệu ",
                "data": result
            })
        }    
    } catch (error) {
        console.log(error);
    }
})
//
router.get('/getbyid/:id', async(req, res) =>{
    const result = await modeDistributor.findById(req.params.id)
    try {
       if(result){
        // res.send(result)
        res.json({
            "status" :200,
            "message": "đã tìm thấy  ",
            "data": result
        })
       }else{
        res.json({
            "status" :400,
            "message": "không tìm thấy ID  ",
            "data": result
        })

       }
    } catch (error) {
        if(error.name === 'CastError'){
            res.status(404).send('Invalid ID format')
        }else{
            console.log(error);  
            res.status(500).send('Internal Server')
        }
    }
})
router.patch('/edit/:id', async(req, res) =>{
    const result = await modeDistributor.findByIdAndUpdate(req.params.id,req.body)
    try {
       if(result){
       const rs = await result.save()
        // res.send(rs)
        res.json({
            "status" :200,
            "message": "cập nhật thành công  ",
            "data": rs
        })

       }else{
        res.json({
            "status" :400,
            "message": "không tìm thấy ID  ",
            "data": result
        })

       }
    } catch (error) {
        if(error.name === 'CastError'){
            res.status(404).send('Invalid ID format')
        }else{
            console.log(error);  
            res.status(500).send('Internal Server')
        }
    }
})
router.delete('/delete/:id', async(req, res) =>{
    const result = await modeDistributor.findByIdAndDelete(req.params.id,req.body)
    try {
       if(result){
        res.json({
            "status" :200,
            "message": "Xóa thành công  ",
            "data": result
        })
       }else{
        res.json({
            "status" :400,
            "message": "Xóa thất bại  ",
            "data": []
        })

       }
    } catch (error) {
        if(error.name === 'CastError'){
            res.status(404).send('Invalid ID format')
        }else{
            console.log(error);  
            res.status(500).send('Internal Server')
        }
    }
})
// router.get('/get-list-ditributor', async(req, res) =>{
//     try {
//         //lấy danh sách theo thứ tự distributors mới nhất
//        if(data){
//         res.json({//trả về danh sách
//             "status" :200,
//             "message": "Thành công  ",
//             "data": data
//         })
//        }else{
//         //nếu thêm không thành công result null , thông báo khôn thành công
//         res.json({
//             "status" :400,
//             "message": "lỗi , không thành công  ",
//             "data": []
//         })

//        }
//     } catch (error) {
//             console.log(error);  
     
//     }
// });








module.exports = router;