var express = require('express');
var router = express.Router();
const modelUser = require('../models/user');
const JWT = require('jsonwebtoken');
const SECRECT_KEY ="mophan";


router.get('/test', function(reg, res, next){
    res.send('respond with a resource login test');
});
router.post('/checkLogin', async(req, res)=>{
    try {
        const {username, password} = req.body;
        const user = await modelUser.findOne({username, password});
        console.log(user);
        if(user){
            const token = JWT.sign({id: user.id}, SECRECT_KEY, {expiresIn:'1h'});
            const refreshToken = JWT.sign({id: user.id}, SECRECT_KEY, {expiresIn:'1d'});
            res.json({
                "status": 200,
                "message": "Login successfuly",
                "data": user,
                "token": token,
                "refreshToken": refreshToken,
            })
        }else{
            res.json({
                "status": 400,
                "message": "Login failed",
                "data": [],
            })
        }
    } catch (error) {
        console.log(error)
    }
})

module.exports = router;